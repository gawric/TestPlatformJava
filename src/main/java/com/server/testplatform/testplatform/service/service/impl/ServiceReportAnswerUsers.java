package com.server.testplatform.testplatform.service.service.impl;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.answerreport.AnswerRequestModel;
import com.server.testplatform.testplatform.model.answerreport.AnswerTableDetail;
import com.server.testplatform.testplatform.model.answerreport.ReportItem;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.service.db.inter.IServiceReportDbImpl;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import com.server.testplatform.testplatform.service.service.impl.support.ReportAnswer;
import com.server.testplatform.testplatform.service.service.inter.IServiceReportAnswerUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("IServiceReportAnswerUsers")
public class ServiceReportAnswerUsers implements IServiceReportAnswerUsers {

    //работа с базой
    @Autowired
    private IServiceReportDbImpl serviceReportDb;


    @Autowired
    private IServiceUserDb serviceUserDb;


    @Autowired
    private ReportAnswer ra;


    public ResponseEntity<Object> addAnswerToReport(AnswerRequestModel arm) {
         try
         {
                if(serviceReportDb.findExistById(arm.getReport_id())){
                   ReportUserAnswer rua =  serviceReportDb.findById(arm.getReport_id());
                   ReportItem ri =  createReportItem(arm , rua);

                   if(rua.getListItemsReport() == null) rua.setListItemsReport(createReportItemList());
                   rua.getListItemsReport().add(ri);
                   serviceReportDb.saveReport(rua);
                }


                 arm.setStatus("OK");
         }
         catch (Exception ex){
           System.out.println(ex.toString());
           arm.setStatus("ERROR: критическая ошибка!");
           return new ResponseEntity<>(arm  , HttpStatus.OK);
         }

        return new ResponseEntity<>(arm  , HttpStatus.OK);
    }

    private List<ReportItem> createReportItemList(){
        return new ArrayList<>();
    }

    @Override
    public ResponseEntity<Object> createReport() {
        ReportUserAnswer ra = createReportAnswerModel();
        return new ResponseEntity<>(serviceReportDb.saveReport(ra)  , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllAnswerTable(long user_id) {
        try
        {
            UserModel um = serviceUserDb.findById(user_id);
            if(um != null){

                List<ReportUserAnswer> listItemsReport = serviceReportDb.findAllReport();
                List<UserFormModel> listUfm = um.getListForm();
                List<AnswerTableDetail> listAnswerTable = ra.createAnswerReportList(listUfm , listItemsReport);
               return new ResponseEntity<>(listAnswerTable , HttpStatus.OK);
            }

        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return new ResponseEntity<>("{}"  , HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAnswerToPageTable(long user_id , int page) {
        try
        {
            UserModel um = serviceUserDb.findById(user_id);
            if(um != null){

                List<ReportUserAnswer> listItemsReport = serviceReportDb.findAllReport();
                List<ReportUserAnswer> listItemsReportClear = clearSize0( listItemsReport);
                List<UserFormModel> listUfm = um.getListForm();
                List<AnswerTableDetail> listAnswerTable = ra.getPagesAnswerReport(page , listUfm , listItemsReportClear);
                return new ResponseEntity<>(listAnswerTable , HttpStatus.OK);
            }

        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return new ResponseEntity<>("{}"  , HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<ReportUserAnswer> clearSize0(List<ReportUserAnswer> listItemsReport){
        return listItemsReport
                .stream()
                .filter(x->x.getListItemsReport() != null)
                .filter(f->f.getListItemsReport().size() > 0)
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<Object> getAnswerToIndexName(long user_id , String  name) {
        try
        {
            UserModel um = serviceUserDb.findById(user_id);
            if(um != null){

                List<ReportUserAnswer> listItemsReport = serviceReportDb.findAllReport();
                List<UserFormModel> listUfm = um.getListForm();
                List<AnswerTableDetail> listAnswerTable = ra.equalsNameToList(name , listUfm , listItemsReport);
                return new ResponseEntity<>(listAnswerTable , HttpStatus.OK);
            }

        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return new ResponseEntity<>("{}"  , HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ReportUserAnswer createReportAnswerModel(){
        ReportUserAnswer rua = new ReportUserAnswer();
        return rua;
    }
    private ReportItem createReportItem(AnswerRequestModel arm , ReportUserAnswer rua){
        ReportItem ri = new ReportItem();
        ri.setAnswer(arm.isAnswer());
        ri.setAnswer_id(arm.getAnswer_id());
        ri.setEmail(arm.getEmail());
        ri.setQuest_id(arm.getQuest_id());
        ri.setFinishdata(LocalDateTime.now());
        ri.setPhone(arm.getPhone());
        ri.setUsername(arm.getUsername());
        ri.setForm_id(arm.getForm_id());
        ri.setReportAnswer(rua);
        return ri;
    }

   // @Override
   // public ResponseEntity<Object> addAnswer(AnswerRequestModel arm) {

       // try{
           // UserModel user = serviceUserDb.findById(arm.getUser_id());
           // UserFormModel ufm = getUfm(arm , user);

            //UserQuestModel uqm =  auf.searchQuestModel(ufm.getListQuest() , arm.getQuest_id());
            //List<AnswerQuestModel> currentanswer =  auf.changeAnswerStatus(uqm.getListAnswer() ,  arm.getAnswer_id(), arm.isAnswer());

            //serviceUserDb.saveUser(user);
           // arm.setStatus("OK");
            //return new ResponseEntity<>(arm  , HttpStatus.OK);
       // }catch (Exception ex){
        //    arm.setStatus("ERROR: критическая ошибка!");
        //    return new ResponseEntity<>(arm  , HttpStatus.OK);
       // }

    //}
}
