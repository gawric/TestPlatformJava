package com.server.testplatform.testplatform.controller;

import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.service.service.inter.IAdminServiceUser;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsersImage;
import com.server.testplatform.testplatform.variable.ErrorMessage;
import com.server.testplatform.testplatform.variable.TestUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("upload-users")
public class UploadImgController {

    //@Autowired
    //private ServiceUsers su;

    @Autowired
    private IServiceUsersImage isui;


    @PostMapping(value="/img")
    public ResponseEntity<Object> handleFileUpload(@RequestBody  MultipartFile file){
        System.out.println("");
        return isui.handleFileUpload(file.getOriginalFilename() , file , 1);

    }

}