package com.server.testplatform.testplatform.support;

import com.server.testplatform.testplatform.model.answerreport.AnswerTableDetail;
import com.server.testplatform.testplatform.model.answerreport.ReportItem;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;
import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateDataReportAnswer {
    public List<ReportUserAnswer> createListReport(){

        List<ReportUserAnswer> list = new ArrayList<>();


        ReportUserAnswer rua1 = new ReportUserAnswer();
        rua1.setReport_id(1l);
        List<ReportItem> reportList = new ArrayList<>();
        reportList.add(createReportItem(1 , false,1,1,"admin@hh-soft.ru"));
        reportList.add(createReportItem(2 , true,1,2,"admin@hh-soft.ru"));
        rua1.setListItemsReport(reportList);


        ReportUserAnswer rua2 = new ReportUserAnswer();

        List<ReportItem> reportList2 = new ArrayList<>();
        reportList2.add(createReportItem(1 , false,2,1,"gawric@mail.ru"));
        reportList2.add(createReportItem(2 , false,2,2,"gawric@mail.ru"));

        rua2.setListItemsReport(reportList2);
        rua2.setReport_id(2l);


        list.add(rua1);
        list.add(rua2);
        return list;
    }

    public ReportItem createReportItem(long answer_id , Boolean isAnswer , long form_id , long quest_id , String email){
        ReportItem ri = new ReportItem();
        ri.setAnswer(isAnswer);
        ri.setAnswer_id(answer_id);
        ri.setForm_id(form_id);
        ri.setQuest_id(quest_id);
        ri.setEmail(email);
        ri.setFinishdata(LocalDateTime.now());

        return ri;

    }

    public  List<UserQuestModel> getListQuest() {
        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 1 , "quest1"));
        listQuest.add(getTestModel(2 , 2 , "quest2"));

        List<AnswerQuestModel> list_answer = new ArrayList<>();
        list_answer.add(getTestAnswerModel(listQuest.get(0) , 1, "Answer1" , false));
        list_answer.add(getTestAnswerModel(listQuest.get(0) , 2, "Answer2" , true));
        listQuest.get(0).setListAnswer(list_answer);


        List<AnswerQuestModel> list_answer2 = new ArrayList<>();
        list_answer2.add(getTestAnswerModel(listQuest.get(1) , 1, "Answer1" , false));
        list_answer2.add(getTestAnswerModel(listQuest.get(1) , 2, "Answer2" , true));
        listQuest.get(1).setListAnswer(list_answer2);

        return listQuest;

    }
    public  List<UserQuestModel> getListQuest2() {
        List<UserQuestModel> listQuest = new ArrayList<>();
        listQuest.add(getTestModel(1 , 1 , "questTest2"));
        listQuest.add(getTestModel(2 , 2 , "questTest3"));

        List<AnswerQuestModel> list_answer = new ArrayList<>();
        List<AnswerQuestModel> list_answer2 = new ArrayList<>();

        list_answer.add(getTestAnswerModel(listQuest.get(0) , 1, "Answer1" , true));
        list_answer.add(getTestAnswerModel(listQuest.get(0) , 2, "Answer2" , true));

        list_answer2.add(getTestAnswerModel(listQuest.get(1) , 1, "Answer3" , false));
        list_answer2.add(getTestAnswerModel(listQuest.get(1) , 2, "Answer4" , false));

        listQuest.get(0).setListAnswer(list_answer);
        listQuest.get(1).setListAnswer(list_answer2);

        return listQuest;

    }


    public UserQuestModel getTestModel(long quest_id , Integer number_line , String nameQuest){
        UserQuestModel uqm = new UserQuestModel();
        uqm.setQuest_id(quest_id);
        uqm.setQuest(nameQuest);
        uqm.setNumber_line(number_line);
        return uqm;
    }

    public AnswerQuestModel getTestAnswerModel(UserQuestModel uqm , long answer_id , String answer , boolean isAnswer){
        AnswerQuestModel aqm = new AnswerQuestModel();
        aqm.setAnswer_id(answer_id);
        aqm.setQuest(uqm);
        aqm.setAnswer(answer);
        aqm.setCorrectAnswer(isAnswer);
        return aqm;
    }



    public List<UserFormModel> getList(){
        List<UserFormModel> list = new ArrayList<>();

        UserFormModel ufm1 = new UserFormModel();
        ufm1.setForm_id(1l);

        UserFormModel ufm2 = new UserFormModel();
        ufm2.setForm_id(2l);

        list.add(ufm1);
        list.add(ufm2);

        return list;
    }

    public List<AnswerTableDetail> getListAtdTest(){
        AnswerTableDetail s1 = new AnswerTableDetail();
        s1.setNameForm("test1");
        s1.setDetailAuth("gawric");

        AnswerTableDetail s2 = new AnswerTableDetail();
        s2.setNameForm("test2");


        AnswerTableDetail s3 = new AnswerTableDetail();
        s3.setNameForm("test3");


        AnswerTableDetail s4 = new AnswerTableDetail();
        s4.setNameForm("test4");

        AnswerTableDetail s5 = new AnswerTableDetail();
        s5.setNameForm("test5");


        AnswerTableDetail s6 = new AnswerTableDetail();
        s6.setNameForm("test6");


        AnswerTableDetail s7 = new AnswerTableDetail();
        s7.setNameForm("test7");


        AnswerTableDetail s8 = new AnswerTableDetail();
        s8.setNameForm("test8");

        AnswerTableDetail s9 = new AnswerTableDetail();
        s9.setNameForm("test9");

        AnswerTableDetail s10 = new AnswerTableDetail();
        s10.setNameForm("test10");

        AnswerTableDetail s11 = new AnswerTableDetail();
        s11.setNameForm("test11");

        AnswerTableDetail s12 = new AnswerTableDetail();
        s12.setNameForm("test12");

        AnswerTableDetail s13 = new AnswerTableDetail();
        s13.setNameForm("test13");

        AnswerTableDetail s14 = new AnswerTableDetail();
        s14.setNameForm("test14");

        AnswerTableDetail s15= new AnswerTableDetail();
        s15.setNameForm("test15");

        AnswerTableDetail s16 = new AnswerTableDetail();
        s16.setNameForm("test16");

        AnswerTableDetail s17 = new AnswerTableDetail();
        s17.setNameForm("test17");

        AnswerTableDetail s18 = new AnswerTableDetail();
        s18.setNameForm("test18");

        AnswerTableDetail s19 = new AnswerTableDetail();
        s19.setNameForm("test19");

        AnswerTableDetail s20 = new AnswerTableDetail();
        s20.setNameForm("test20");

        AnswerTableDetail s21 = new AnswerTableDetail();
        s21.setNameForm("test21");

        AnswerTableDetail s22 = new AnswerTableDetail();
        s22.setNameForm("test22");


        List<AnswerTableDetail> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        list.add(s3);
        list.add(s4);

        list.add(s5);
        list.add(s6);

        list.add(s7);
        list.add(s8);

        list.add(s9);
        list.add(s10);

        list.add(s11);
        list.add(s12);

        list.add(s13);
        list.add(s14);

        list.add(s15);
        list.add(s15);

        list.add(s16);
        list.add(s16);

        list.add(s17);
        list.add(s17);

        list.add(s18);
        list.add(s18);

        list.add(s19);
        list.add(s19);

        list.add(s20);
        list.add(s21);

        return list;
    }
}
