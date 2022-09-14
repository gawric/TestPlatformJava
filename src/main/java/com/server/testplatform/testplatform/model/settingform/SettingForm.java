package com.server.testplatform.testplatform.model.settingform;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;

import javax.persistence.*;

@Entity
@Table(name = "settingform")
public class SettingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long setting_id;

    private int timetest;
    private int leveltest;
    private Boolean iscompleted;

    // 0 - Доступ к тесту всем только нужно ввести ник
    // 1 - Доступ к тесту только с введенным e-mail
    // 2 - Доступ к тесту только по телефону
    // 3 - доступ к тесту по e-mail и телефону
    private int typeaccess;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private UserFormModel userform;

    public Long getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(Long setting_id) {
        this.setting_id = setting_id;
    }

    public int getTimetest() {
        return timetest;
    }

    public void setTimetest(int timetest) {
        this.timetest = timetest;
    }

    public int getLeveltest() {
        return leveltest;
    }

    public void setLeveltest(int leveltest) {
        this.leveltest = leveltest;
    }

    public UserFormModel getUserform() {
        return userform;
    }

    public void setUserform(UserFormModel userform) {
        this.userform = userform;
    }

    public Boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public int getTypeaccess() {
        return typeaccess;
    }

    public void setTypeaccess(int typeaccess) {
        this.typeaccess = typeaccess;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SettingForm cmEquals = (SettingForm) o;
        return setting_id == cmEquals.setting_id &&
                cmEquals.getTimetest() == timetest&&
                cmEquals.leveltest == leveltest;
    }


    @Override
    public int hashCode() {
        return   setting_id.intValue() * timetest * leveltest;
    }
}
