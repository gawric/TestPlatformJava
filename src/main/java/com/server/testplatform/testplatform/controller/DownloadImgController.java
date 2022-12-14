package com.server.testplatform.testplatform.controller;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.service.service.inter.IAdminServiceUser;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsersImage;
//import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("download-users")
public class DownloadImgController {

    @Autowired
    private IServiceUsersImage  sui;

    @Autowired
    private AdminServiceUsers su;

    //работа с базой
    @Autowired
    private IServiceUserDb serviceUserDb;

    //пока оставлю открытым
    //возможно в будущем нужно будет сделать проверку по юзеру
    @GetMapping(value = "/get-image")
    @ResponseBody
    public void getImageWithMediaType(@RequestParam Long upload_id , HttpServletResponse response) throws IOException {
        IOUtils.copy(sui.getImage(upload_id , response) , response.getOutputStream());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/get-list-image")
    public ResponseEntity<Object> getListImage(Principal principal) throws IOException {
        return  sui.getListUploadImg(getUserId(principal.getName()));
    }

    private long getUserId(String username){
        return serviceUserDb.findByUsername(username).getUser_id();
    }


}
