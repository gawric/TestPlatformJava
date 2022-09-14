package com.server.testplatform.testplatform.service.service.impl.support;


import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminUserSettingForm {

    public UserFormModel saveSettingForm(UserFormModel ufm , SettingForm sf){

        if(sf != null & ufm != null){
            sf.setUserform(ufm);
            ufm.setSettingform(sf);
        }

        return ufm;
    }

    public SettingForm getSettingForm(UserFormModel ufm){
        if(ufm != null){
          return ufm.getSettingform();
        }

        return null;
    }

}
