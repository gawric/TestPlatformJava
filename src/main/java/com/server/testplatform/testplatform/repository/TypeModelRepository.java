package com.server.testplatform.testplatform.repository;

import com.server.testplatform.testplatform.model.answerreport.ReportUserAnswer;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TypeModelRepository extends CrudRepository<UserFormTypeModel, Long> {
    UserFormTypeModel findByTypename(String typename);
    UserFormTypeModel findById(long type_id);

    @Modifying
    @Query(value = "DELETE FROM userformtype WHERE type_id = :typeid" , nativeQuery = true)
    void deleteByType_id(long typeid);
}
