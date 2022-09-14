package com.server.testplatform.testplatform.model.settingform;

import com.server.testplatform.testplatform.model.settingform.SettingForm;
import com.server.testplatform.testplatform.variable.ConstSetting;

import java.util.List;

public class ClientSettingForm {



    private SettingForm sf;
    private long form_id;
    private final ArrLevelModel[] listLevel;
    private final ArrTypeAccess[] listTypeAccess;

    public ClientSettingForm(){
        listLevel = ConstSetting.getLevelArr();
        listTypeAccess = ConstSetting.getAccessTypeArr();
    }

    public ArrLevelModel[] getListLevel() {
        return listLevel;
    }


    public SettingForm getSf() {
        return sf;
    }

    public void setSf(SettingForm sf) {
        this.sf = sf;
    }

    public long getForm_id() {
        return form_id;
    }

    public void setForm_id(long form_id) {
        this.form_id = form_id;
    }

    public ArrTypeAccess[] getListTypeAccess() {
        return listTypeAccess;
    }

}
