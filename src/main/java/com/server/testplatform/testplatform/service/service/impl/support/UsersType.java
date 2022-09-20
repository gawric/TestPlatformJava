package com.server.testplatform.testplatform.service.service.impl.support;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UsersType {

    public List<UserFormTypeModel> getDataType(UserModel user){
        return user.getListtype();
    }

    public List<UserFormModel> getListForm(Iterable<UserModel> iterableUser , long type_id){

        //List<UserFormModel> listForm = user.getListForm();
        List<UserFormModel>  listForm = getAllFormAllUser(iterableUser);
        return listForm.stream()
                .filter(x->x.getSelectformtype() == type_id)
                .collect(Collectors.toList());
    }

    private List<UserFormModel> getAllFormAllUser(Iterable<UserModel> iterableUser){
        List<UserFormModel> listForm = new ArrayList<>();
        StreamSupport.stream(iterableUser.spliterator(), false)
                .map(x->listForm.addAll(x.getListForm()))
                .collect(Collectors.toList());

        return listForm;
    }
}
