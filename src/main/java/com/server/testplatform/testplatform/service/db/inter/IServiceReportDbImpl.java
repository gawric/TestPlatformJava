package com.server.testplatform.testplatform.service.db.inter;


import com.server.testplatform.testplatform.model.answerreport.ReportItem;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;

import java.util.List;

public interface IServiceReportDbImpl {
    ReportUserAnswer saveReport(ReportUserAnswer rua);
    boolean deleteReport(long  id);
    ReportUserAnswer findById(long id);
    boolean findExistById(long id);
    List<ReportItem>  findAllReportItem();
    List<ReportUserAnswer>  findAllReport();
}
