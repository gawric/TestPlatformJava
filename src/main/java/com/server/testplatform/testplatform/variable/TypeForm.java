package com.server.testplatform.testplatform.variable;

import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.model.settingform.ArrLevelModel;

import java.util.List;

public class TypeForm {

    public static UserFormTypeModel getEqualsType(List<UserFormTypeModel> listType , long type_id){

            return listType.stream()
                    .filter(x->x.getType_id() == type_id)
                    .findFirst().get();
    }
}
