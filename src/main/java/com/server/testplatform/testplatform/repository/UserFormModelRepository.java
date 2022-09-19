package com.server.testplatform.testplatform.repository;

import com.server.testplatform.testplatform.model.form.UserFormModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserFormModelRepository extends CrudRepository<UserFormModel, Long> {

    @Modifying
    @Query(value = "DELETE FROM userform WHERE form_id = :form_id" , nativeQuery = true)
    void deleteByForm_id(long form_id);
}
