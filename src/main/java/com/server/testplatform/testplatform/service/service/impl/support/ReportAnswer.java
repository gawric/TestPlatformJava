package com.server.testplatform.testplatform.service.service.impl.support;


import com.server.testplatform.testplatform.model.answerreport.AnswerTableDetail;
import com.server.testplatform.testplatform.model.answerreport.ReportItem;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;

import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;
import com.server.testplatform.testplatform.variable.ConstSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportAnswer {

    @Autowired
    private AdminUserForm af;

    public ReportAnswer(){
        af = new AdminUserForm();
    }

    public List<AnswerTableDetail> getPagesAnswerReport(int page , List<UserFormModel>listAllUserForm , List<ReportUserAnswer> listReportUserAnswer){
        List<AnswerTableDetail> allRows =  createAnswerReportList(listAllUserForm , listReportUserAnswer);
        return splitList(allRows  ,  page);
    }

    public List<AnswerTableDetail> splitList(List<AnswerTableDetail> allRows  , int page){

        List<AnswerTableDetail> sortedList;

        if(ConstSetting.getRowsToPages * page <= allRows.size()){

            if(page == 1){
               // int currentIndex = ConstSetting.getRowsToPages * page;
                sortedList = copyCurrentItems(allRows ,  ConstSetting.getRowsToPages * page);
            }
            else{
                sortedList  = getSearchPage(allRows  ,  page);
            }
        }
        else{
            sortedList  = getEhdItems( allRows  ,  page);
        }

        return sortedList;
    }

    public List<AnswerTableDetail> equalsNameToList(String name , List<UserFormModel>listAllUserForm , List<ReportUserAnswer> listReportUserAnswer){
        List<AnswerTableDetail> allRows =  createAnswerReportList(listAllUserForm , listReportUserAnswer);
        return searchName(allRows ,  name);

    }

    public List<AnswerTableDetail> searchName(List<AnswerTableDetail> allRows , String name){
        return allRows
                .stream()
                .filter(x->x.getDetailAuth() != null)
                .filter(x->x.getDetailAuth().indexOf(name) > -1)
                .collect(Collectors.toList());
    }


    private List<AnswerTableDetail> getEhdItems(List<AnswerTableDetail> allRows  , int page){
        //-1 к текущей странице т.е если было 6 страницы мы берем 5
        int ignore_rows = page - 1;

        //5 страниц умножаем сколько шт на 1 странице
        int minus1Count = ignore_rows * ConstSetting.getRowsToPages;
        int result_end_pages = isEndPages(allRows.size() ,  minus1Count);

        if(result_end_pages> 0){
          return getEndPages(result_end_pages ,  allRows);
        }
        else{
           return new ArrayList<>();
        }

    }

    private List<AnswerTableDetail> getSearchPage(List<AnswerTableDetail> allRows  , int page){

        //общее кол-во нужных элементов
        int temp = ConstSetting.getRowsToPages * page;
        //-1 к текущей странице т.е если было 6 страницы мы берем 5
        int ignore_rows = page - 1;
        //5 страниц умножаем сколько шт на 1 странице
        int ignore_rows_n = ignore_rows * ConstSetting.getRowsToPages;
        //Общее количество - Количество на -1 страницу
        int currentIndex = temp - ignore_rows_n;
        //Удаляем эти 5 страниц т.к они уже были выведены
        List<AnswerTableDetail> delRows = deleteRemove(allRows ,  ignore_rows_n);
        //остались только итемы текущей 6 страницы копируем только их если после них есть еще какие-то страницы
        return  copyCurrentItems(delRows ,  currentIndex);
    }

    private List<AnswerTableDetail> getEndPages(int result_end_pages , List<AnswerTableDetail> allRows){
        int removeindex = allRows.size() - result_end_pages;
        return  deleteRemove(allRows ,  removeindex);
    }
    private int   isEndPages(int allRowsCount , int minus1Count){
            return  allRowsCount - minus1Count;
    }

    private List<AnswerTableDetail> copyCurrentItems(List<AnswerTableDetail> allRows , int currentIndex){
        List<AnswerTableDetail> sortedList = new ArrayList<>();

        for(int i=0; i < currentIndex; i++){
            sortedList.add(allRows.get(i));
        }

        return sortedList;

    }
    private List<AnswerTableDetail> deleteRemove(List<AnswerTableDetail> allRows , int multiIndex){
        allRows.subList(0, multiIndex).clear();
        return allRows;
    }

    public List<AnswerTableDetail> createAnswerReportList(List<UserFormModel>listAllUserForm , List<ReportUserAnswer> listReportUserAnswer){
        List<AnswerTableDetail> list = new ArrayList<>();

        listReportUserAnswer.forEach(rua -> {

            AnswerTableDetail atd = new AnswerTableDetail();

            atd.setCompleteQuestions(rua.getListItemsReport().size());
            boolean[] noEqualsCountQuest = {false};
            boolean[] stopCheckAnswer = {false};
            //0 - succes
            //1 - fail
            int[] status_quest = new int[2];

            rua.getListItemsReport().forEach(ri->{

                UserFormModel sFormModel = searchForm(ri.getForm_id() , listAllUserForm);


                //System.out.println("Interesting Form id " + sFormModel.getForm_id());
                if(sFormModel != null){
                    UserQuestModel uqm = searchQuestToForm(sFormModel , ri.getQuest_id());
                    setAtdInitData( atd , sFormModel.getFormname() , sFormModel.getListQuest().size() , ri);
                    checkAnswerQuest(stopCheckAnswer , sFormModel , rua , uqm ,ri , atd , noEqualsCountQuest , status_quest);
                }
            });
            setResultQuestStatus(status_quest ,  atd);
            setResultEqualsError(noEqualsCountQuest ,  atd);
            list.add(atd);
        });
       // listReportUserAnswer.
       // searchForm(searchId , listUserForm);
        return list;
    }

    private void setResultQuestStatus(int[] status_quest , AnswerTableDetail atd){
        atd.setSuccesQuest(status_quest[0]);
        atd.setFailQuest(status_quest[1]);
    }
    private void setResultEqualsError(boolean[] noEqualsCountQuest , AnswerTableDetail atd){
        if(noEqualsCountQuest[0] == true){
            atd.setResult(false);
        }
    }
    private void checkAnswerQuest( boolean[] stopCheckAnswer  , UserFormModel sFormModel , ReportUserAnswer rua , UserQuestModel uqm  , ReportItem ri , AnswerTableDetail atd ,  boolean[] noEqualsCountQuest , int[] status_quest){
        if(isCompletFullQuest(sFormModel.getListQuest().size() ,  rua.getListItemsReport().size())){
            //if(!stopCheckAnswer[0]){
                setCancel( uqm ,  ri ,  atd , stopCheckAnswer , status_quest);
           // }
        }else{
            noEqualsCountQuest[0] = true;
        }
    }
    private boolean isCompletFullQuest(int allquest , int succesQuest){
        if(allquest == succesQuest){
            return true;
        }
        return false;
    }
    private void setCancel(UserQuestModel uqm , ReportItem ri , AnswerTableDetail atd ,  boolean[] stopCheckAnswer , int[] status_quest){
        if(!isCompleteSucces(uqm.getListAnswer(), ri.getAnswer_id() , ri.isAnswer())){
           if(!stopCheckAnswer[0]){
               stopCheckAnswer[0] = true;
               atd.setResult(false);
               status_quest[1] = status_quest[1] + 1;
               System.out.println("Мы нашли не совпадение результатов проверки: " + " Не все вопросы оказались с верными ответами");
               System.out.println("FormId: "+ ri.getForm_id() + " QuestId "+ ri.getQuest_id() + " AnswerId " + ri.getAnswer_id() + " Answer " + ri.isAnswer());
               // System.out.println("FormId: "+ ri.getForm_id() + " QuestId "+ ri.getQuest_id() + " AnswerId " + ri.getAnswer_id());
           }
           else{
               status_quest[1] = status_quest[1] + 1;
           }


        }
        else{
            if(!stopCheckAnswer[0]){
                atd.setResult(true);
                status_quest[0] = status_quest[0] + 1;
            }
            else{
                status_quest[0] = status_quest[0] + 1;
            }
        }
    }
    private boolean isCompleteSucces(List<AnswerQuestModel> listAnswer, long serachAnswerId , boolean isAnswerClient){
        Optional<AnswerQuestModel> aqm = listAnswer
                .stream()
                .filter(x->x.getAnswer_id() == serachAnswerId)
                .findFirst();

        if(aqm.isPresent()){
            AnswerQuestModel correct = aqm.get();

            //если ответ выбранный админом совпал с ответом юзера!
            if(aqm.get().getCorrectAnswer() == isAnswerClient){
                return true;
            }
            else{
                System.out.println("Нашли и сравниваем "+" QuestId "+ correct.getQuest().getQuest_id() + " AnswerId " + correct.getAnswer_id() + " Answer " + correct.getCorrectAnswer());
                return false;
            }
        }
        else{
            System.out.println("No Found Answer");
            return false;
        }


    }
    private String equalsDetailAuth(ReportItem ri){
        return Stream.of(ri.getPhone() , ri.getEmail() , ri.getUsername())
                .filter(x->x != null)
                .filter(x->x.isEmpty() != true)
                .filter(x->x.equals("non") != true)
                .findFirst()
                .get();
    }
    private void setAtdInitData(AnswerTableDetail atd , String formName , int questSize ,  ReportItem ri){
        atd.setNumberQuestions(questSize);
        atd.setNameForm(formName);
        atd.setDataFinish(ri.getFinishdata());
        atd.setDetailAuth(equalsDetailAuth(ri));
    }

    private UserQuestModel searchQuestToForm(UserFormModel sFormModel , long quesId){
        return af.getQuest(sFormModel.getListQuest() , quesId);
    }



    private UserFormModel searchForm(long searchId , List<UserFormModel> list){
        return af.findByIdForm(list , searchId);
    }


    public void test(ReportUserAnswer s){
        System.out.println(s.toString());
       // System.out.println(listAllUserForm.);
    }
}
