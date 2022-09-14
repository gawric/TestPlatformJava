package com.server.testplatform.testplatform.service.service.impl.support;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UsersType {

    public List<UserFormTypeModel> getDataType(UserModel user){
        List<UserFormTypeModel> listType = user.getListtype();
        return listType;
    }

    public List<UserFormModel> getListForm(UserModel user , long type_id){
        List<UserFormModel> listForm = user.getListForm();

        return listForm.stream()
                .filter(x->x.getSelectformtype() == type_id)
                .collect(Collectors.toList());
    }
}
