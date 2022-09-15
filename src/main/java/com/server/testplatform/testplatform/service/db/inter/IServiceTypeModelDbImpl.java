package com.server.testplatform.testplatform.service.db.inter;

import com.server.testplatform.testplatform.model.form.UserFormTypeModel;

public interface IServiceTypeModelDbImpl {
    void deleteTypeModel(UserFormTypeModel uftm);
    UserFormTypeModel findByTypename(String typename);
    void saveType(UserFormTypeModel uftm);
}
