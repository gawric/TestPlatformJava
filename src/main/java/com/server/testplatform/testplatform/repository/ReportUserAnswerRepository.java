package com.server.testplatform.testplatform.repository;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReportUserAnswerRepository extends CrudRepository<ReportUserAnswer, Long> {

    ReportUserAnswer findById(long report_id);

    @Query("select count(p) = 1 from ReportUserAnswer p where report_id = ?1")
    boolean findExistById(long report_id);
}
