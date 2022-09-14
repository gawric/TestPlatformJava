package com.server.testplatform.testplatform.service.service.inter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public interface IServiceUsersImage {
    InputStream getImage(long id , long user_id , HttpServletResponse response);
    ResponseEntity<Object> handleFileUpload(String name , MultipartFile file , long user_id);
    ResponseEntity<Object> findUploadById(long id , long user_id);
    ResponseEntity<Object> getListUploadImg(long user_id);

}
