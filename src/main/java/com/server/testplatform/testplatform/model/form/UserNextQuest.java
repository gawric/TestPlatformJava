package com.server.testplatform.testplatform.model.form;

import com.server.testplatform.testplatform.model.settingform.SettingForm;

public class UserNextQuest {

    private UserQuestModel nextQuestModel;
    private SettingForm settingForm;
    private String nameTest;


    public UserQuestModel getNextQuestModel() {
        return nextQuestModel;
    }

    public void setNextQuestModel(UserQuestModel nextQuestModel) {
        this.nextQuestModel = nextQuestModel;
    }

    public SettingForm getSettingForm() {
        return settingForm;
    }

    public void setSettingForm(SettingForm settingForm) {
        this.settingForm = settingForm;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

}
