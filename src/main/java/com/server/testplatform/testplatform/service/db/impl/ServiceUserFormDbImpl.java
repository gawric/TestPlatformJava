package com.server.testplatform.testplatform.service.db.impl;

import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.repository.UserFormModelRepository;
import com.server.testplatform.testplatform.service.db.inter.IServiceDelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("IServiceDelForm")
public class ServiceUserFormDbImpl implements IServiceDelForm {

    @Autowired
    private UserFormModelRepository ufmr;



    @Override
    public void deleteFormModel(UserFormModel uftm) {
        ufmr.deleteByForm_id(uftm.getForm_id());
    }
}
