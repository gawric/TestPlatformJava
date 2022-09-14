package com.server.testplatform.testplatform.model.form;

import java.util.List;

public class UserFormTestWindow {
    private UserFormTypeModel typeModel;
    private List<UserFormModel> listForm;


    public UserFormTypeModel getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(UserFormTypeModel typeModel) {
        this.typeModel = typeModel;
    }

    public List<UserFormModel> getListForm() {
        return listForm;
    }

    public void setListForm(List<UserFormModel> listForm) {
        this.listForm = listForm;
    }


}
