package com.server.testplatform.testplatform.controller;

import com.server.testplatform.testplatform.model.settingform.ClientSettingForm;
import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.variable.ErrorMessage;
import com.server.testplatform.testplatform.variable.TestUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin-users")
public class AdminPanel {

    @Autowired
    private AdminServiceUsers su;

    @GetMapping(value = "/unblockUser")
    public ResponseEntity<Object> unblockUser(@RequestParam long id) {
        return su.blockUserWeb(id , false);
    }

    @GetMapping(value = "/addTestUser")
    public ResponseEntity<Object> test() {
        su.addUserWeb(TestUsers.getAdmin());
        return ErrorMessage.getResponceErrorHttpStatus("TestUsersAdd", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/addTestForm")
    public ResponseEntity<Object> testForm() {
        su.addTestForm(1);
        return ErrorMessage.getResponceErrorHttpStatus("TestUsersAdd", HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/initdata")
    public ResponseEntity<Object> initUsers() {
        System.out.println("Init Data Request!");
        return su.initDataForm(1);
    }

    @GetMapping(value = "/initdatatype")
    public ResponseEntity<Object> initType() {
        System.out.println("Init Data type!");
        return su.initDataType(1);
    }

    @PostMapping(path  = "/adddatatype" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  addDataType(@Valid @RequestBody UserFormTypeModel typeName) {
        return su.addDataType(1 ,  typeName.getTypename());
    }

    @PostMapping(path  = "/deldatatype" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  delDataType(@Valid @RequestBody UserFormTypeModel typeModel) {
        return su.delDataType(1 ,  typeModel);
    }


    @GetMapping(value = "/testtype")
    public String testtype() {
        su.addTestType(1);
        return "";
    }

    @GetMapping(value = "/get-form/{form_id}")
    @ResponseBody
    public ResponseEntity<Object> getForm(@PathVariable("form_id") Long form_id) {
        return su.findByForm(1 , form_id);
    }

    @PostMapping(path  = "/adduser" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  createUser(@Valid @RequestBody UserModel newUser) {
        return su.addUserWeb(newUser);
    }

    @PostMapping(path  = "/addformmodel" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  createForm(@Valid @RequestBody UserFormModel ufm) {
        return su.addForm(1 , ufm);
    }

    @PostMapping(path  = "/addsettingform" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  createSettingForm(@Valid @RequestBody ClientSettingForm cfm) {
        return su.addSettingForm(1 , cfm.getForm_id() , cfm.getSf());
    }

    @GetMapping(value = "/get-setting-form/{form_id}")
    @ResponseBody
    public ResponseEntity<Object> getSettingForm(@PathVariable("form_id") Long form_id) {
        return su.findBySettingForm(1 , form_id);
    }


    @GetMapping(value = "/getdata")
    public ResponseEntity<Object> getDataUsers() {
        System.out.println("getDataUsers!");
       // su.findByIdTWeb(1);

        return su.initDataForm(1);
    }
}
