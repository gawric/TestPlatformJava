package com.server.testplatform.testplatform.service.service.impl;


import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.authoption.AuthOptionModel;
import com.server.testplatform.testplatform.model.authoption.AuthResultModel;
import com.server.testplatform.testplatform.model.form.*;
import com.server.testplatform.testplatform.model.answerreport.AnswerRequestModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import com.server.testplatform.testplatform.service.db.impl.ServiceUserFormDbImpl;
import com.server.testplatform.testplatform.service.db.inter.IServiceDelForm;
import com.server.testplatform.testplatform.service.db.inter.IServiceTypeModelDbImpl;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import com.server.testplatform.testplatform.service.service.impl.support.AdminUserForm;
import com.server.testplatform.testplatform.service.service.impl.support.UsersType;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsers;
import com.server.testplatform.testplatform.variable.TypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("IServiceUsers")
public class ServiceUsers implements IServiceUsers {

    //работа с базой
    @Autowired
    private IServiceUserDb serviceUserDb;

    @Autowired
    private UsersType ut;

    @Autowired
    private AdminUserForm auf;

    @Autowired
    private IServiceTypeModelDbImpl tm;





    @Override
    public ResponseEntity<Object> getAllType() {
       Iterable<UserModel> user = serviceUserDb.getAllUser();
       ArrayList<UserFormTypeModel> allType = new ArrayList();
       setIterableModel( user , allType);

        if(allType == null){
            return new ResponseEntity<>("{}" , HttpStatus.NO_CONTENT);
        }
         return new ResponseEntity<>(allType , HttpStatus.OK);
    }

    private void setIterableModel(Iterable<UserModel> user , ArrayList<UserFormTypeModel> allType){

        StreamSupport.stream(user.spliterator(), false)
                .map(x->ut.getDataType(x))
                .peek(f->allType.addAll(f))
                .collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<Object> getListForm(long type_id) {
        Iterable<UserModel> user = serviceUserDb.getAllUser();
        List<UserFormModel> list = ut.getListForm( user , type_id);
        UserFormTestWindow tWindows = new UserFormTestWindow();

        if(list == null){
            return new ResponseEntity<>("{}" , HttpStatus.NO_CONTENT);
        }

        if(list.size() > 0){
            long type_id2 = list.get(0).getSelectformtype();
            tWindows.setListForm(list);
            UserFormTypeModel tp = tm.findByType_id(type_id2);
            tWindows.setTypeModel(tp);
          //  tWindows.setTypeModel(TypeForm.getEqualsType(ut.getDataType(user) , type_id2));

            return new ResponseEntity<>(tWindows , HttpStatus.OK);
        }

        return new ResponseEntity<>("{}" , HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Object> getNextQuestPage(long user_id, long form_id , int quest_page , String username , String phone , String email) {
        UserModel user = serviceUserDb.findById(user_id);
        List<UserFormModel> listFormModel = user.getListForm();
        UserFormModel ufm = auf.findByIdForm(listFormModel , form_id);

        username = decodeEmpty(username);
        phone = decodeEmpty(phone);
        email = decodeEmpty(email);

        if(auf.isValid( username,  phone,  email , ufm.getSettingform())){
            if(ufm.getListQuest() == null){
                UserNextQuest unqempty =  createModel(null , ufm.getFormname() , ufm.getSettingform());
                return new ResponseEntity<>(unqempty , HttpStatus.OK);
            }

            List<UserQuestModel> listQuest = ufm.getListQuest();
            //Сортируем по номерам, кто будет первым в списке
            auf.sortedToLineNumber(listQuest);
            UserQuestModel uqm = auf.getQuestToPage(listQuest, quest_page);
            UserNextQuest unq =  createModel(uqm , ufm.getFormname() , ufm.getSettingform());

            if(uqm != null & unq != null){
                return new ResponseEntity<>(unq , HttpStatus.OK);
            }

            UserNextQuest unqempty =  createModel(null , ufm.getFormname() , ufm.getSettingform());

            return new ResponseEntity<>(unqempty , HttpStatus.OK);
        }
        else{
            UserNextQuest unqempty =  createModel(null , ufm.getFormname() , ufm.getSettingform());
            return new ResponseEntity<>(unqempty , HttpStatus.UNAUTHORIZED);
        }


    }

    private String decodeEmpty(String text){
        if(text.equals("non")){
            return "";
        }

        return text;
    }

    @Override
    public ResponseEntity<Object> getAuthOptions(long user_id, long form_id) {
        UserModel user = serviceUserDb.findById(user_id);
        List<UserFormModel> listFormModel = user.getListForm();
        UserFormModel ufm = auf.findByIdForm(listFormModel , form_id);

        AuthOptionModel aom = auf.getAuthOptionToFormid(ufm);
        return new ResponseEntity<>(aom , HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Object> getAuthOptionsValidate(long user_id, long form_id, String username, String phone, String email) {
        UserModel user = serviceUserDb.findById(user_id);
        List<UserFormModel> listFormModel = user.getListForm();
        UserFormModel ufm = auf.findByIdForm(listFormModel , form_id);

        if(ufm == null){
            return new ResponseEntity<>("{}" , HttpStatus.NO_CONTENT);
        }
        SettingForm sf = ufm.getSettingform();

        if(auf.isValid( username,  phone,  email , sf)){
            return new ResponseEntity<>(createModel(true , "ok") , HttpStatus.OK);
        }

        return new ResponseEntity<>(createModel(false , "Не прошла проверка полученных данных") , HttpStatus.OK);
    }





    private AuthResultModel createModel(boolean status , String otherTextError){
        AuthResultModel arm = new AuthResultModel();
        arm.setSucces(status);
        arm.setOtherStatus(otherTextError);
        return arm;
    }

    private UserNextQuest createModel(UserQuestModel uqm , String nameTest , SettingForm sf){
        UserNextQuest model = new UserNextQuest();
        model.setNextQuestModel(uqm);
        model.setNameTest(nameTest);
        model.setSettingForm(sf);
        return model;
    }


}
