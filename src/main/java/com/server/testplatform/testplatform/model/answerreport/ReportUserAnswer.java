package com.server.testplatform.testplatform.model.answerreport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reportuseranswer")
public class ReportUserAnswer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long report_id;



    @JsonIgnore
    @OneToMany(targetEntity = ReportItem.class , cascade = CascadeType.ALL , orphanRemoval = true ,fetch = FetchType.LAZY, mappedBy="reportAnswer")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ReportItem> listItemsReport = new ArrayList();



    public Long getReport_id() {
        return report_id;
    }

    public void setReport_id(Long report_id) {
        this.report_id = report_id;
    }


    public List<ReportItem> getListItemsReport() {
        return listItemsReport;
    }

    public void setListItemsReport(List<ReportItem> listItemsReport) {
        this.listItemsReport = listItemsReport;
    }

    @Override
    public String toString(){
        return "report_id: " + report_id + "\n"+
                "listItemsReport: size "+ listItemsReport.size();
    }


}
