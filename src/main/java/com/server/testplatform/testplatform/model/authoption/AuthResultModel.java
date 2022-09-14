package com.server.testplatform.testplatform.model.authoption;

public class AuthResultModel {

    private boolean isSucces;
    private String hrefSucces;
    private String otherStatus;

    public boolean isSucces() {
        return isSucces;
    }

    public void setSucces(boolean succes) {
        isSucces = succes;
    }

    public String getHrefSucces() {
        return hrefSucces;
    }

    public void setHrefSucces(String hrefSucces) {
        this.hrefSucces = hrefSucces;
    }

    public String getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(String otherStatus) {
        this.otherStatus = otherStatus;
    }
}
