package com.server.testplatform.testplatform.service.service.inter;

import com.server.testplatform.testplatform.model.answerreport.AnswerRequestModel;
import org.springframework.http.ResponseEntity;

public interface IServiceReportAnswerUsers {
    ResponseEntity<Object> addAnswerToReport(AnswerRequestModel arm);
    ResponseEntity<Object> createReport();
    ResponseEntity<Object> getAllAnswerTable(long user_id);
    ResponseEntity<Object> getAnswerToPageTable(long user_id , int page);
    ResponseEntity<Object> getAnswerToIndexName(long user_id , String  name);
}
