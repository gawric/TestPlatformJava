package com.server.testplatform.testplatform.model.answerreport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.UserModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportitem")
public class ReportItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long item_id;


    @Column
    private String username;

    @Column
    private String phone;

    @Column
    private String email;


    @Column
    private long form_id;


    @Column
    private long quest_id;


    @Column
    private long answer_id;

    @Column
    private boolean answer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id"  , insertable = true )
    private ReportUserAnswer reportAnswer;


    @Column(name = "finishdata")
    private LocalDateTime finishdata;

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getForm_id() {
        return form_id;
    }

    public void setForm_id(long form_id) {
        this.form_id = form_id;
    }

    public long getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(long quest_id) {
        this.quest_id = quest_id;
    }

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public LocalDateTime getFinishdata() {
        return finishdata;
    }

    public void setFinishdata(LocalDateTime finishdata) {
        this.finishdata = finishdata;
    }

    public ReportUserAnswer getReportAnswer() {
        return reportAnswer;
    }

    public void setReportAnswer(ReportUserAnswer reportAnswer) {
        this.reportAnswer = reportAnswer;
    }

}
