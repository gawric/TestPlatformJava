package com.server.testplatform.testplatform;

import com.server.testplatform.testplatform.model.form.UserFormModel;

import com.server.testplatform.testplatform.model.settingform.SettingForm;

import com.server.testplatform.testplatform.service.service.impl.support.AdminUserSettingForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdminSettingUserTest {

    AdminUserSettingForm au;

    @BeforeEach
    void setUp() {
        au = new AdminUserSettingForm();
    }

    @Test
    @DisplayName("Тест сохранения настроек SettingForm")
    void saveSettingForm() {
        UserFormModel ufm2 = new UserFormModel();
        SettingForm sf = getSettingForm(ufm2 , 0   , 1);
        UserFormModel ufm = au.saveSettingForm(ufm2 ,sf);
        assertTrue(ufm.getSettingform() != null);
    }

    @Test
    @DisplayName("Провека на  обработку null SettingForm")
    void checkModelToEmpty() {
        SettingForm ufm = au.getSettingForm(null);
        assertTrue(ufm == null);

    }

    @Test
    @DisplayName("Провека на получение SettingForm")
    void checkModelToNoEmpty() {
        UserFormModel ufm = new UserFormModel();
        SettingForm sf2 = getSettingForm(ufm , 0   , 1);
        ufm.setSettingform(sf2);
        SettingForm sf = au.getSettingForm(ufm);
        assertTrue(sf != null);

    }




    private SettingForm getSettingForm(UserFormModel ufm , int level  , int timetest){
        SettingForm sf = new SettingForm();
        sf.setLeveltest(level);
        sf.setTimetest(timetest);
        sf.setUserform(ufm);

        return sf;
    }
}
