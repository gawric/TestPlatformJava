package com.server.testplatform.testplatform.model.form;

public class PostDelModel {
    private long form_id;
    private String status;

    public PostDelModel(long form_id , String status){
        this.form_id = form_id;
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public long getForm_id() {
        return form_id;
    }

    public void setForm_id(long form_id) {
        this.form_id = form_id;
    }
}
