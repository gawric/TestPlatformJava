package com.server.testplatform.testplatform.service.db.impl;

import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.repository.ReportUserAnswerRepository;
import com.server.testplatform.testplatform.repository.TypeModelRepository;
import com.server.testplatform.testplatform.service.db.inter.IServiceTypeModelDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IServiceTypeModelDbImpl")
public class ServiceTypeModelDbImpl implements IServiceTypeModelDbImpl {

    @Autowired
    private TypeModelRepository tmr;


    @Override
    public void deleteTypeModel(UserFormTypeModel uftm) {
        tmr.deleteByType_id(uftm.getType_id());
    }

    @Override
    public UserFormTypeModel findByType_id(long type_id) {
        return tmr.findById(type_id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserFormTypeModel findByTypename(String typename) {
        return tmr.findByTypename(typename);
    }


    @Override
    public void saveType(UserFormTypeModel uftm) {
        tmr.save(uftm);
    }
}
