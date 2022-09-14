package com.server.testplatform.testplatform.service.db.impl;


import com.server.testplatform.testplatform.model.answerreport.ReportItem;
import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import com.server.testplatform.testplatform.repository.ReportUserAnswerRepository;
import com.server.testplatform.testplatform.repository.UserRepository;
import com.server.testplatform.testplatform.service.db.inter.IServiceReportDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service("IServiceReportDbImpl")
public class ServiceReportDbImpl implements IServiceReportDbImpl {

    @Autowired
    private ReportUserAnswerRepository ruar;

    @Override
    public ReportUserAnswer saveReport(ReportUserAnswer rua) {
        try {
            return  ruar.save(rua);
        }
         catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    @Override
    public boolean deleteReport(long  report_id) {
        try {
            ruar.deleteById(report_id);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public ReportUserAnswer findById(long report_id) {
        return ruar.findById(report_id);
    }

    @Override
    public boolean findExistById(long report_id) {
        return ruar.findExistById(report_id);
    }

    @Override
    public  List<ReportItem>  findAllReportItem() {
        List<ReportUserAnswer> listPerort = getAllReportList(ruar.findAll());
        List<ReportUserAnswer> listFilterReport = filterReportEmpty(listPerort);
        return getAllReportItems( listFilterReport);
    }

    @Override
    public  List<ReportUserAnswer>  findAllReport() {
        return getAllReportList(ruar.findAll());


    }



    private List<ReportItem> getAllReportItems(List<ReportUserAnswer> listFilterReport){
        return listFilterReport
                .stream()
                .map(x -> x.getListItemsReport())
                .flatMap(pets -> pets.stream())
                .collect(Collectors.toList());
    }

    private List<ReportUserAnswer>  filterReportEmpty(List<ReportUserAnswer> listPerort ){
        return listPerort
                .stream()
                .filter(x->x.getListItemsReport().isEmpty() != true)
                .collect(Collectors.toList());
    }
    private List<ReportUserAnswer> getAllReportList(Iterable<ReportUserAnswer> iterable){
        return
                StreamSupport
                        .stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());
    }
}
