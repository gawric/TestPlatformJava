package com.server.testplatform.testplatform.service.service.inter;


import org.springframework.http.ResponseEntity;


public interface IServiceUsers {
    ResponseEntity<Object> getAllType();
    ResponseEntity<Object> getListForm(long type_id);
    ResponseEntity<Object> getNextQuestPage(long user_id, long form_id , int quest_page , String username , String phone , String email);
    ResponseEntity<Object> getAuthOptions(long user_id, long form_id);
    ResponseEntity<Object> getAuthOptionsValidate(long user_id, long form_id , String  username , String  phone , String  email);



}
