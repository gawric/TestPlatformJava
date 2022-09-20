package com.server.testplatform.testplatform.controller;

import com.server.testplatform.testplatform.model.answerreport.AnswerRequestModel;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.service.service.inter.IServiceReportAnswerUsers;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsers;
import com.server.testplatform.testplatform.variable.TestUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("test-user")
public class UserPanel {

    @Autowired
    private IServiceUsers su;


    @Autowired
    private IServiceReportAnswerUsers rau;

    //работа с базой
    @Autowired
    private IServiceUserDb serviceUserDb;


    @GetMapping(value = "/get-type")
    public ResponseEntity<Object> test() {
      return su.getAllType();
    }

    @GetMapping(value = "/get-allform-type/{type_id}")
    public ResponseEntity<Object> allform(@PathVariable("type_id") Long type_id) {
        return su.getListForm(type_id);
    }



    @GetMapping("/get-form-quest/next-quest")
    public ResponseEntity<Object> getFormAndQuest(@RequestParam Long form_id , @RequestParam int quest_page,
                                                  @RequestParam String  username ,
                                                  @RequestParam String  phone ,
                                                  @RequestParam String  email) {



        return su.getNextQuestPage(getUserId(TestUsers.getAdmin().getUsername())  , form_id , quest_page , username , phone , email);
    }

    @GetMapping("/get-form-auth/options/{form_id}")
    public ResponseEntity<Object> getAuthOptions(@PathVariable Long form_id) {
        return  su.getAuthOptions( getUserId(TestUsers.getAdmin().getUsername())  , form_id);
    }

    @GetMapping("/get-form-auth/check-user-data")
    public ResponseEntity<Object> getValidateAccessToForm(@RequestParam Long form_id ,
                                                          @RequestParam String  username ,
                                                          @RequestParam String  phone ,
                                                          @RequestParam String  email) {

        return  su.getAuthOptionsValidate(getUserId(TestUsers.getAdmin().getUsername())  , form_id , username , phone , email);
    }

    @PostMapping(path  = "/add/answer" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  addAnswerForm(@Valid @RequestBody AnswerRequestModel ufm) {
        ufm.setUser_id(1);
        return rau.addAnswerToReport(ufm);
    }

    @GetMapping("/answer/getAll")
    public ResponseEntity<Object> getAllAnswer() {
        return  rau.getAllAnswerTable(getUserId(TestUsers.getAdmin().getUsername()) );
    }

    @GetMapping(value = "/answer/getPage/{page}")
    public ResponseEntity<Object> getAnswerPages(@PathVariable("page") int page) {
        return rau.getAnswerToPageTable(getUserId(TestUsers.getAdmin().getUsername())  , page);
    }

    @GetMapping(value = "/answer/getSearchName/{name}")
    public ResponseEntity<Object> getAnswerPages(@PathVariable("name") String name) {
        return rau.getAnswerToIndexName(getUserId(TestUsers.getAdmin().getUsername()) , name);
    }


    @PostMapping(path  = "/add/reportList" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  addReportList() {
       // ufm.setUser_id(1);
        return rau.createReport();
    }

    //мы используем только 1 юзера и это юзер админ. В будущем возможно придется переделать, что-бы тесты могли создавать кучу разных юзеров
    //сейчас поставлена задача работать только с 1 юзером!
    //Всех остальных мы авторизируем только по нику или email без регистрации на сервере!
    private long getUserId(String username){
        return serviceUserDb.findByUsername(username).getUser_id();
    }


}
