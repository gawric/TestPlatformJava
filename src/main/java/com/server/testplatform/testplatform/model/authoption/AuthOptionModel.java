package com.server.testplatform.testplatform.model.authoption;

public class AuthOptionModel {
    private int selectAdminAuthForm;
    private String answerAuth;
    private long form_id;

    public int getSelectAdminAuthForm() {
        return selectAdminAuthForm;
    }

    public void setSelectAdminAuthForm(int selectAdminAuthForm) {
        this.selectAdminAuthForm = selectAdminAuthForm;
    }

    public String getAnswerAuth() {
        return answerAuth;
    }

    public void setAnswerAuth(String answerAuth) {
        this.answerAuth = answerAuth;
    }

    public long getForm_id() {
        return form_id;
    }

    public void setForm_id(long form_id) {
        this.form_id = form_id;
    }
}
