package com.server.testplatform.testplatform;

import com.server.testplatform.testplatform.model.answerreport.AnswerTableDetail;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.service.service.impl.support.AdminUserForm;
import com.server.testplatform.testplatform.service.service.impl.support.ReportAnswer;
import com.server.testplatform.testplatform.support.CreateDataReportAnswer;
import com.server.testplatform.testplatform.variable.ConstSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportAnswerTest {

   private  ReportAnswer ra;
   private AdminUserForm af;
   private CreateDataReportAnswer cdra;

    @BeforeEach
    void setUp() {
        ra = new ReportAnswer();
        af = new AdminUserForm();
        cdra= new CreateDataReportAnswer();
    }

    //Очень большой модульный тест тестирует правильность скрипта на проверку результатов!
    @Test
    @DisplayName("Скрипт проверки результатов!")
    void testAnswer() {

        List<AnswerTableDetail> listAnswer =  GetResult();
        assertTrue(listAnswer.get(1).getResult() == false);
    }

    @Test
    @DisplayName("Скрипт проверка кол-во вопросов и ответов")
    void testCountAnswer2Equals2() {

        List<AnswerTableDetail> listAnswer =  GetResult();
        assertTrue(listAnswer.get(0).getSuccesQuest() == 2);
    }

    @Test
    @DisplayName("Проверка вывода по номеру 1 страницы (pagination)")
    void testReportAnswerPage1() {

        List<AnswerTableDetail> list = cdra.getListAtdTest();
        List<AnswerTableDetail> listSplit =  ra.splitList(list , 1);
        assertTrue(ConstSetting.getRowsToPages == listSplit.size());
    }

    @Test
    @DisplayName("Проверка вывода по номеру 2 страницы (pagination)")
    void testReportAnswerPage2() {

        List<AnswerTableDetail> list = cdra.getListAtdTest();
        List<AnswerTableDetail> listSplit = ra.splitList(list , 2);
        assertTrue(ConstSetting.getRowsToPages == listSplit.size());
    }


//    @Test
//    @DisplayName("Проверка вывода по номеру  3 страницы (pagination)")
//    void testReportAnswerPage3() {
//
//        List<AnswerTableDetail> list = cdra.getListAtdTest();
//        List<AnswerTableDetail> listSplit = ra.splitList(list , 3);
//        assertTrue(ConstSetting.getRowsToPages == listSplit.size());
//    }

    @Test
    @DisplayName("Проверка вывода по номеру  16 страницы  првоерка на недоступную страницу")
    void testReportAnswerPage16() {

        List<AnswerTableDetail> list = cdra.getListAtdTest();
        List<AnswerTableDetail> listSplit = ra.splitList(list , 16);
        assertTrue(listSplit.isEmpty() == true);
    }


    @Test
    @DisplayName("AnswerTableDetail поиск по имени из списка")
    void reportTestEqualsToName() {

        List<AnswerTableDetail> list = cdra.getListAtdTest();
        List<AnswerTableDetail> s = ra.searchName(list  , "gawric");
        assertTrue(s.isEmpty() != true);
    }



    private List<AnswerTableDetail>  GetResult(){
        List<UserFormModel> list = cdra.getList();

        //UserFormModel - модель юзера генерируем лист  и находим в листе
        UserFormModel ufm = af.findByIdForm(list,  1);
        ufm.setFormname("test form 1");

        //getListQuest - создаем List вопросов для будущей проверки на верность
        ufm.setListQuest(cdra.getListQuest());

        UserFormModel ufm2 = af.findByIdForm(list,  2);
        ufm2.setFormname("test form 2");
        ufm2.setListQuest(cdra.getListQuest2());

        //Генерирует ответы юзеров createListReport для будущей проверки!
        return  ra.createAnswerReportList(list , cdra.createListReport());
    }



}
