package com.server.testplatform.testplatform.service.service.impl;

import com.server.testplatform.testplatform.exception.NoAccessEditingUserException;
import com.server.testplatform.testplatform.exception.NotFoundUserException;
import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.authoption.AuthOptionModel;
import com.server.testplatform.testplatform.model.form.PostDelModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;
import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;
import com.server.testplatform.testplatform.model.settingform.ClientSettingForm;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import com.server.testplatform.testplatform.service.db.impl.ServiceTypeModelDbImpl;
import com.server.testplatform.testplatform.service.db.inter.IServiceDelForm;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;

import com.server.testplatform.testplatform.service.service.impl.support.AdminUserForm;
import com.server.testplatform.testplatform.service.service.impl.support.AdminUserSettingForm;
import com.server.testplatform.testplatform.service.service.inter.IAdminServiceUser;

import com.server.testplatform.testplatform.variable.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import static com.server.testplatform.testplatform.variable.TestUsers.*;

@Service("IAdminServiceUser")
public class AdminServiceUsers implements IAdminServiceUser {

    //работа с базой
    @Autowired
    private IServiceUserDb serviceUserDb;


    @Autowired
    private AdminUserForm af;

    @Autowired
    private AdminUserSettingForm asf;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceTypeModelDbImpl stm;

    @Autowired
    private IServiceDelForm sdf;


    public ResponseEntity<Object> addSettingForm(long user_id , long form_model , SettingForm sf){
        UserModel user = serviceUserDb.findById(user_id);
        UserFormModel ufm = af.findByIdForm(user.getListForm() , form_model);
        asf.saveSettingForm(ufm , sf);
        serviceUserDb.saveUser(user);

        UserModel usersave = serviceUserDb.findById(user_id);
        UserFormModel ufmsave = af.findByIdForm(usersave.getListForm() , form_model);

        return new ResponseEntity<>(ufmsave.getSettingform(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findBySettingForm(long user_id, long form_model) {
        UserModel usersave = serviceUserDb.findById(user_id);
        UserFormModel ufmsave = af.findByIdForm(usersave.getListForm() , form_model);
        if(ufmsave != null & ufmsave.getSettingform() != null){

            return new ResponseEntity<>(createCSF(ufmsave.getSettingform() , ufmsave.getForm_id()) , HttpStatus.OK);
        }
        return new ResponseEntity<>(new ClientSettingForm(), HttpStatus.OK);
    }

    private ClientSettingForm createCSF(SettingForm sf , long form_id){
        ClientSettingForm f = new ClientSettingForm();
        f.setSf(sf);
        f.setForm_id(form_id);
        return f;
    }
    public ResponseEntity<Object> addTestForm(long user_id){
        UserModel user = serviceUserDb.findById(user_id);
        UserFormModel ufm = getFormModel3();
        ufm.setUser(user);
        List<UserFormModel> list = user.getListForm();
        if(list == null)list = new ArrayList<>();
        list.add(ufm);

        serviceUserDb.saveUser(user);

        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }

    public ResponseEntity<Object> delDataType(long user_id , UserFormTypeModel uftm){
        UserModel user = serviceUserDb.findById(user_id);


        List<UserFormTypeModel> listType = user.getListtype();

        if(!isExist(listType , uftm.getTypename())){
            removeObject(uftm);
            return new ResponseEntity<>(uftm , HttpStatus.OK);
        }


        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }


    private void  removeObject(UserFormTypeModel obj){
        stm.deleteTypeModel(obj);
    }

    public ResponseEntity<Object> addDataType(long user_id , String typeName){
        UserModel user = serviceUserDb.findById(user_id);

        UserFormTypeModel t1 =  getTestType(typeName);
        t1.setUser(user);
        List<UserFormTypeModel> listType = user.getListtype();

        if(user.getListtype() == null)listType = new ArrayList<>();

        if(isExist( listType , typeName)){
          //  listType.add(t1);
            stm.saveType(t1);
            UserFormTypeModel t1w = getTypeName(typeName);
            return new ResponseEntity<>(t1w , HttpStatus.OK);
        }


        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }

    private Boolean isExist(List<UserFormTypeModel> listType , String typeName){
        Optional<UserFormTypeModel> model = listType
                .stream()
                .filter(x->x.getTypename().equals(typeName))
                .findFirst();

        if(model.isEmpty()) return true;

        return false;
    }

    private UserFormTypeModel getTypeName(String typeName){
        return stm.findByTypename(typeName);
    }

    public ResponseEntity<Object> addTestType(long user_id){
        UserModel user = serviceUserDb.findById(user_id);

        UserFormTypeModel t1 =  getTestType("Архитектурный");
        UserFormTypeModel t2 = getTestType("Конструктивный");
        UserFormTypeModel t3 =  getTestType("Ген.план");

        List<UserFormTypeModel> listType = new ArrayList<>();

        t1.setUser(user);
        listType.add(t1);

        t2.setUser(user);
        listType.add(t2);


        t3.setUser(user);
        listType.add(t3);

        user.setListtype(listType);
        List<UserFormModel> list = user.getListForm();


        UserFormModel item = list.get(0);
        item.setSelectformtype(0l);

        UserFormModel item1 = list.get(1);
        item1.setSelectformtype(1l);

        serviceUserDb.saveUser(user);

        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }


    public ResponseEntity<Object> addForm(long user_id , UserFormModel ufm){

        UserModel user = serviceUserDb.findById(user_id);

        if(ufm.getForm_id() != null){
            return Save( user_id ,  user ,   ufm);
        }
        else
        {
            if(getFormByName(user.getListForm() , ufm.getFormname()) == null){
                return Save( user_id ,  user ,   ufm);
            }
            else{
                return new ResponseEntity<>("{}" , HttpStatus.CONFLICT);
            }

        }


    }

    private ResponseEntity<Object>  Save( long user_id  , UserModel user ,  UserFormModel ufm){
        ufm.setDescription("");
        ufm.setCreatedata(LocalDateTime.now());
        ufm.setUser(user);

        setUfmToLisqQuest(ufm);
        setMinus1QuestToNull(ufm.getListQuest());
        setListQuestToAnswer(ufm.getListQuest());
        setMinus1AnswerToNull(ufm.getListQuest());

        List<UserFormModel> list = user.getListForm();

        if(list == null)list = new ArrayList<>();
        addItem(list ,  ufm);
        serviceUserDb.saveUser(user);

        UserModel user2 = serviceUserDb.findById(user_id);
        UserFormModel updateufm = getFormByName( user2.getListForm() ,  ufm.getFormname());

        return new ResponseEntity<>(updateufm , HttpStatus.OK);
    }


    private UserFormModel getFormByName(List<UserFormModel> list , String formName){
        Optional<UserFormModel> form =  list.stream()
                .filter(x->x.getFormname().equals(formName))
                .findFirst();

        if(form.isPresent()){
            return form.get();
        }

        return null;
    }

    private void addItem(List<UserFormModel> list , UserFormModel ufm){
        if(isExist( list ,  ufm)){
            list.remove(ufm);
            list.add(ufm);
        }
        else{
            list.add(ufm);
        }
    }


   private Boolean isExist(List<UserFormModel> list , UserFormModel ufm){
       Optional<UserFormModel> item =  list
               .stream()
               .filter(x->x.getForm_id() == ufm.getForm_id())
               .findFirst();

       if(item.isPresent()){
           return true;
       }

    return false;
   }

    private void setUfmToLisqQuest(UserFormModel ufm){
        List<UserQuestModel> list = ufm.getListQuest()
                .stream()
                .peek(x->x.setUserform(ufm))
                .peek(x->x.setCreatedata(LocalDateTime.now()))
                .collect(Collectors.toList());
        ufm.setListQuest(list);

    }

    private void setListQuestToAnswer(List<UserQuestModel> listQuest){
        List<UserQuestModel> newListQuest =  listQuest
               .stream()
               .peek(x->x.getListAnswer()
                       .stream()
                       .peek(f->f.setQuest(x))
                       .collect(Collectors.toList()))
               .collect(Collectors.toList());
        System.out.println("");

    }

    private void setMinus1AnswerToNull(List<UserQuestModel> listQuest){
        listQuest
                .stream()
                .forEach(x->x.getListAnswer()
                        .stream()
                        .filter(z->z.getAnswer_id() == -1)
                        .peek(g->g.setAnswer_id(null))
                        .collect(Collectors.toList()));


        System.out.println("");
    }

    private void setMinus1QuestToNull(List<UserQuestModel> listQuest){
        listQuest
                .stream()
                 .filter(z->z.getQuest_id() == -1)
                 .peek(g->g.setQuest_id(null))
                 .collect(Collectors.toList());


        System.out.println("");
    }



    public ResponseEntity<Object> initDataForm(long user_id){
        UserModel user = serviceUserDb.findById(user_id);
        if(user != null)
        {
            List<UserFormModel> list  = user.getListForm();
            if(list != null)
            {
                return new ResponseEntity<>(user.getListForm(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }

    public ResponseEntity<Object> initDataType(long user_id){
        UserModel user = serviceUserDb.findById(user_id);
        if(user != null)
        {
            List<UserFormTypeModel> listType  = user.getListtype();

            return new ResponseEntity<>(listType, HttpStatus.OK);

        }

        return new ResponseEntity<>("{}" , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByIdTWeb(long id) {

        try
        {
            UserModel user = serviceUserDb.findById(id);
            sNull(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (NotFoundUserException ex)
        {
            ex.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Not found user exception", HttpStatus.NOT_FOUND);
        }

    }



    @Override
    public ResponseEntity<Object> updUserWeb(UserModel newUser) {
        try
        {
            //проверка что у казан id номер
            sNullId(newUser);

            UserModel user = serviceUserDb.findById(newUser.getUser_id());
            sNull(user);

            serviceUserDb.saveUser(newUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (NotFoundUserException ex)
        {
            ex.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Not found user exception", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Object> delUserWeb(long id) {
        try
        {
            //проверка что указан id номер
            if(!serviceUserDb.findExistById(id)) throw new NotFoundUserException("Id number not found");
            serviceUserDb.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundUserException e)
        {
            e.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Id number not found" , HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Object> selfUpdUserWeb(String authUsername , UserModel newUser) {
        try
        {
            UserModel userModel = serviceUserDb.findByUsername(authUsername);
            if(userModel == null) throw new NotFoundUserException("Not found user exception");
            if(!userModel.getUsername().equals(authUsername)) throw  new NoAccessEditingUserException("No access to user editing");
            if(newUser.getUser_id() != userModel.getUser_id()) throw  new NoAccessEditingUserException("No access to user editing");

            serviceUserDb.saveUser(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(NotFoundUserException ex)
        {
            ex.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Id number not found" , HttpStatus.NOT_FOUND);
        } catch (NoAccessEditingUserException e) {
            e.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("No access to user editing" , HttpStatus.FORBIDDEN);
        }

    }

    @Override
    public ResponseEntity<Object> findByForm(long user_id , long form_id) {
        UserModel userModel = serviceUserDb.findById(user_id);
        try
        {
            if(userModel == null)return new ResponseEntity<>("{}", HttpStatus.OK);
            Optional<UserFormModel>  ufm2 = findFormById( userModel ,  form_id);
            return new ResponseEntity<>(ufm2.get(), HttpStatus.OK);

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Not found user exception", HttpStatus.NOT_FOUND);
        }
    }

    private Optional<UserFormModel> findFormById(UserModel userModel , long form_id){
      return userModel
                .getListForm()
                .stream()
                .filter(c -> c.getForm_id() == form_id)
                .findFirst();
    }

    @Override
    public ResponseEntity<Object> blockUserWeb(long userId , boolean isBlock) {
        try
        {
            UserModel user = serviceUserDb.findById(userId);
            sNull(user);
            setBlock(isBlock ,  user);
            serviceUserDb.saveUser(user);
            if(isBlock)return new ResponseEntity<>("User-> username: "+ user.getUsername() +" and userid: "+user.getUser_id()+" succes blocked", HttpStatus.OK);

            return new ResponseEntity<>("User-> username: "+ user.getUsername() +" and userid: "+user.getUser_id()+" succes unblocked", HttpStatus.OK);

        }catch (NotFoundUserException ex)
        {
            ex.printStackTrace();
            return ErrorMessage.getResponceErrorHttpStatus("Not found user exception", HttpStatus.NOT_FOUND);
        }
    }



    private void setBlock(boolean isBlock , UserModel user)
    {
        if(isBlock) {
            setEnabBlock(user);
        }
        else {
            setDisabBlock(user);
        }

    }

    //медленное добавление для вебки
    public ResponseEntity<Object> addUserWeb(UserModel newUser)
    {

        // try
        // {
        //if(serviceUserDb.findExistByname(newUser.getUsername())) throw new IncorrectUserNameException("Username already in use");
        setLdT(newUser);
        setRoleUser(newUser);
        setDisabBlock(newUser);
        decodePassword(newUser);
        if(serviceUserDb.saveUser(newUser))return new ResponseEntity(HttpStatus.OK);
        // }
        // catch (IncorrectUserNameException e)
        // {
        //   e.printStackTrace();
        //   return ErrorMessage.getResponceError("Username already in use");
        // }
        //BlockingQueue<Integer> blck = new ArrayBlockingQueue(1024);

        return ErrorMessage.getResponceError("Unknown error");
    }

    @Override
    public ResponseEntity<Object> delForms(long user_id, long form_id) {
        UserModel user = serviceUserDb.findById(user_id);
        Optional<UserFormModel> ufm2 = findFormById(user , form_id);
        if(ufm2.isPresent()){
            sdf.deleteFormModel(ufm2.get());
            return new ResponseEntity<>(new PostDelModel(ufm2.get().getForm_id() , "OK"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new PostDelModel(ufm2.get().getForm_id() , "Not Found") , HttpStatus.OK);
    }


    private void decodePassword(UserModel newUser)
    {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    }
    private void sNull(UserModel user) throws NotFoundUserException {
        if(user == null) throw new NotFoundUserException("Not found user exception");
    }
    private void sNullId(UserModel user) throws NotFoundUserException {
        if(user.getUser_id() == null) throw new NotFoundUserException("id number not defined");
    }



    private void setLdT(UserModel newUser)
    {
        if(newUser != null)
        {
            newUser.setCreatedata(LocalDateTime.now());
            newUser.setLastenterdata(LocalDateTime.now());
        }

    }

    private void setRoleUser(UserModel newUser)
    {
        if(newUser != null)
            newUser.setRole("ROLE_USER");
    }

    private void setDisabBlock(UserModel newUser)
    {
        newUser.setAccountNonLocked(true);
        newUser.setEnabled(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setAccountNonExpired(true);
    }

    private void setEnabBlock(UserModel newUser)
    {
        newUser.setAccountNonLocked(false);
        newUser.setEnabled(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setAccountNonExpired(false);
    }
}