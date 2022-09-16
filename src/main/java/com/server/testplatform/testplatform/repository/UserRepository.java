package com.server.testplatform.testplatform.repository;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    UserModel findById(long id);
    void deleteById(long id);

    @Query("select count(p) = 1 from UserModel p where username = ?1")
    boolean findExistByname(String name);

    @Query("select count(p) = 1 from UserModel p where id = ?1")
    boolean findExistById(long id);

    @Query("select uim from UploadImageModel uim where upload_id = ?1 and user_id = ?2")
    UploadImageModel findUploadByIdAndUserId(long upload_id , long user_id);

    @Query("select uim from UploadImageModel uim where upload_id = ?1")
    UploadImageModel findUploadById(long upload_id);

    @Query("select count(uim) = 1 from UploadImageModel uim where path = ?1")
    boolean findUploadByPath(String path);

    @Query("select uim from UploadImageModel uim where path = ?1")
    UploadImageModel getUploadByPath(String path);


}