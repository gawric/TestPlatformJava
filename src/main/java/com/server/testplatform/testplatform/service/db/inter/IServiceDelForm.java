package com.server.testplatform.testplatform.service.db.inter;

import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;

public interface IServiceDelForm {
    void deleteFormModel(UserFormModel uftm);
}
