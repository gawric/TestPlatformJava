package com.server.testplatform.testplatform.model.answerreport;

import java.time.LocalDateTime;

public class AnswerTableDetail {
    private String nameForm;
    private int numberQuestions;
    private int completeQuestions;
    private String detailAuth;
    private LocalDateTime dataFinish;
    private Boolean isResult;
    private int succesQuest;
    private int failQuest;

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions) {
        this.numberQuestions = numberQuestions;
    }

    public String getDetailAuth() {
        return detailAuth;
    }

    public void setDetailAuth(String detailAuth) {
        this.detailAuth = detailAuth;
    }

    public LocalDateTime getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(LocalDateTime dataFinish) {
        this.dataFinish = dataFinish;
    }

    public Boolean getResult() {
        return isResult;
    }

    public void setResult(Boolean result) {
        isResult = result;
    }

    public int getCompleteQuestions() {
        return completeQuestions;
    }

    public void setCompleteQuestions(int completeQuestions) {
        this.completeQuestions = completeQuestions;
    }
    public int getSuccesQuest() {
        return succesQuest;
    }

    public void setSuccesQuest(int succesQuest) {
        this.succesQuest = succesQuest;
    }

    public int getFailQuest() {
        return failQuest;
    }

    public void setFailQuest(int failQuest) {
        this.failQuest = failQuest;
    }



}
