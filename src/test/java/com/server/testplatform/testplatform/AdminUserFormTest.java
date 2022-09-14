package com.server.testplatform.testplatform;

import com.server.testplatform.testplatform.model.authoption.AuthOptionModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;
import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import com.server.testplatform.testplatform.service.service.impl.support.AdminUserForm;
import com.server.testplatform.testplatform.service.service.impl.support.AdminUserSettingForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminUserFormTest {

    private AdminUserForm af;

    @BeforeEach
    void setUp() {
        af = new AdminUserForm();
    }

    @Test
    @DisplayName("Поиск и получение UserFormModel из List-a")
    void getUfmToList() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = af.findByIdForm(list,  1);
        assertTrue(ufm!= null);
    }

    @Test
    @DisplayName("Поиск и получение модели из списка формы")
    void getUserQuest() {
        List<UserFormModel> list = getList();

        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 1));
        listQuest.add(getTestModel(2 , 2));

        list.get(0).setListQuest(listQuest);
        list.get(1).setListQuest(listQuest);

        UserFormModel ufm = af.findByIdForm(list,  1);
        UserQuestModel uqm = af.getQuest(ufm.getListQuest() , 1);

        assertTrue(uqm != null);
    }

    @Test
    @DisplayName("Получение вопроса по его странице клиента")
    void getUserQuestToPage() {
        List<UserFormModel> list = getList();

        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 1));
        listQuest.add(getTestModel(2 , 2));

        list.get(0).setListQuest(listQuest);
        list.get(1).setListQuest(listQuest);

        UserFormModel ufm = af.findByIdForm(list,  1);
        UserQuestModel uqm = af.getQuestToPage(ufm.getListQuest() , 1);

        assertTrue(uqm.getNumber_line() == 1);
    }

    @Test
    @DisplayName("Проверка сортировки по LineNumber")
    void sortedLineNumber() {
        List<UserFormModel> list = getList();

        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 2));
        listQuest.add(getTestModel(2 , 1));

        list.get(0).setListQuest(listQuest);
        list.get(1).setListQuest(listQuest);
        af.sortedToLineNumber(listQuest);


        assertTrue(listQuest.get(1).getNumber_line() == 2);
    }

    @Test
    @DisplayName("Проверка какие вариант есть что-бы получить доступ к этому тесту")
    void getAuthOptions() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        ufm.setSettingform(cretaSettingForm(ufm , 0));
        AuthOptionModel aom = af.getAuthOptionToFormid(ufm);


        assertTrue(aom.getSelectAdminAuthForm() == 0);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю username")
    void validUserAuthUsername() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 0);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("admin" , "9126340354" , "gawric@mail.ru" , sf);


        assertTrue(valid == true);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю username пустая строка")
    void validUserNoAuthUsername() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 0);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "9126340354" , "gawric@mail.ru" , sf);


        assertTrue(valid == false);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю phone пустая строка")
    void validUserNoAuthPhone() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 2);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "123" , "gawric@mail.ru" , sf);


        assertTrue(valid == false);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю phone")
    void validUserAuthPhone() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 2);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "9123456784" , "gawric@mail.ru" , sf);


        assertTrue(valid == true);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю email")
    void validUserAuthEmail() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 1);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "" , "gawric@mail.ru" , sf);


        assertTrue(valid == true);
    }

    @Test
    @DisplayName("Валидация данных полученных от юзера по полю email")
    void validUserNoAuthEmail() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 1);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "" , "gawric" , sf);


        assertTrue(valid == false);
    }


    @Test
    @DisplayName("Валидация данных полученных phone and email")
    void validUserAll() {
        List<UserFormModel> list = getList();
        UserFormModel ufm = list.get(0);
        SettingForm sf = cretaSettingForm(ufm , 3);
        ufm.setSettingform(sf);
        Boolean valid = af.isValid("" , "9123456784" , "gawric@mail.ru" , sf);


        assertTrue(valid == true);
    }

    @Test
    @DisplayName("ListAnswer находим и изменяем его значение на true!")
    void saveAnswerForm() {
        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 1));
        listQuest.add(getTestModel(2 , 2));

        List<AnswerQuestModel> list_answer = new ArrayList<>();
        list_answer.add(getTestAnswerModel(listQuest.get(0) , 1, "Test1"));
        list_answer.add(getTestAnswerModel(listQuest.get(0) , 2, "Test2"));
        listQuest.get(0).setListAnswer(list_answer);


        UserQuestModel qm =  getTestAnswerModel(listQuest, 1);
        List<AnswerQuestModel> currentanswer =  af.changeAnswerStatus(qm.getListAnswer() ,  1, true);
        qm.setListAnswer(currentanswer);


        assertTrue(qm.getListAnswer().get(0).getCorrectAnswer() == true);
    }

    private UserQuestModel getTestAnswerModel(List<UserQuestModel> listquest , long quest_id){
       return  af.searchQuestModel(listquest , quest_id);
    }

    private SettingForm cretaSettingForm(UserFormModel ufm , int typeaccess){
        SettingForm sf = new SettingForm();
        sf.setUserform(ufm);
        sf.setTimetest(1);
        sf.setIscompleted(true);
        // 0 - по имени
        sf.setTypeaccess(typeaccess);

        return sf;
    }
    private List<UserFormModel> getList(){
        List<UserFormModel> list = new ArrayList<>();

        UserFormModel ufm1 = new UserFormModel();
        ufm1.setForm_id(1l);

        UserFormModel ufm2 = new UserFormModel();
        ufm2.setForm_id(2l);

        list.add(ufm1);
        list.add(ufm2);

        return list;
    }

    private UserQuestModel getTestModel(long quest_id , Integer number_line){
        UserQuestModel uqm = new UserQuestModel();
        uqm.setQuest_id(quest_id);
        uqm.setQuest("Test quest 1");
        uqm.setNumber_line(number_line);
        return uqm;
    }

    private AnswerQuestModel getTestAnswerModel(UserQuestModel uqm , long answer_id , String answer){
        AnswerQuestModel aqm = new AnswerQuestModel();
        aqm.setAnswer_id(answer_id);
        aqm.setQuest(uqm);
        aqm.setAnswer(answer);
        aqm.setCorrectAnswer(false);
      return aqm;
    }


}
