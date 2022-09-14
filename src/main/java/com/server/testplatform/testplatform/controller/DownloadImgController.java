package com.server.testplatform.testplatform.controller;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.service.service.inter.IAdminServiceUser;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsersImage;
//import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("download-users")
public class DownloadImgController {

    @Autowired
    private IServiceUsersImage  sui;

    @Autowired
    private AdminServiceUsers su;


    @GetMapping(value = "/get-image")
    @ResponseBody
    public void getImageWithMediaType(@RequestParam Long upload_id , HttpServletResponse response) throws IOException {
       // IOUtils.copy(sui.getImage(upload_id , 1 , response) , response.getOutputStream());
    }

    @GetMapping(value = "/get-list-image")
    public ResponseEntity<Object> getListImage() throws IOException {
        return  sui.getListUploadImg(1);
    }


}
