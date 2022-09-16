package com.server.testplatform.testplatform.service.db.inter;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;

public interface IServiceUserDb {
    UserModel findByUsername(String username);
    UserModel findById(long id);
    boolean saveUser(UserModel userm);
    boolean findExistByname(String name);
    boolean deleteUser(long id);
    boolean findExistById(long id);
    UploadImageModel findUploadById(long id);
    boolean findUploadByPath(String path);
    UploadImageModel getUploadByPath(String path);
}