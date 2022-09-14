package com.server.testplatform.testplatform.service.service.inter;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import org.springframework.http.ResponseEntity;

public interface IAdminServiceUser {
    ResponseEntity<Object> findByIdTWeb(long id);
    ResponseEntity<Object> addUserWeb(UserModel newUser);
    ResponseEntity<Object> updUserWeb(UserModel newUser);
    ResponseEntity<Object> delUserWeb(long id);
    ResponseEntity<Object> selfUpdUserWeb(String authUsername , UserModel newUser);
    ResponseEntity<Object> findByForm(long user_id , long form_id);
    ResponseEntity<Object> addSettingForm(long user_id , long form_model , SettingForm sf);
    ResponseEntity<Object> findBySettingForm(long user_id , long form_model);
    //true blocked
    //false unblocked
    ResponseEntity<Object> blockUserWeb(long userId , boolean isBlock);


}
