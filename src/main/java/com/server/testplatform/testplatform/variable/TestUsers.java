package com.server.testplatform.testplatform.variable;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;

import java.time.LocalDateTime;

public class TestUsers {

    public static UserModel getAdmin(){
        UserModel user = new UserModel();
        user.setLastenterdata(LocalDateTime.now());
        user.setPassword("11111111");
        user.setCreatedata(LocalDateTime.now());
        user.setEnabled(true);
        user.setUsername("admin");
        return user;
    }

    public static UserFormModel getFormModel(){
        UserFormModel ufm = new UserFormModel();
        LocalDateTime s = LocalDateTime.now();
        ufm.setCreatedata(s);
        ufm.setDescription("Привет это описание тестовой формы");
        ufm.setEmail("gawric@mail.ru");
        ufm.setFormname("Тестирование сотрудников АР");
        return ufm;
    }

    public static UserFormModel getFormModel2(){
        UserFormModel ufm = new UserFormModel();
        LocalDateTime s = LocalDateTime.now();
        ufm.setCreatedata(s);
        ufm.setDescription("Привет это описание тестовой формы");
        ufm.setEmail("gawric@mail.ru");
        ufm.setFormname("Тест для КЖ");
        return ufm;
    }

    public static UserFormTypeModel getTestType(String name){
        UserFormTypeModel uftm = new UserFormTypeModel();
        uftm.setTypename(name);
        return uftm;
    }

    public static UserFormModel getFormModel3(){
        UserFormModel ufm = new UserFormModel();
        LocalDateTime s = LocalDateTime.parse("2022-05-29T13:49:41");
        ufm.setCreatedata(s);
        ufm.setDescription("Это форма из прошлого месяцв");
        ufm.setEmail("gawric@mail.ru");
        ufm.setFormname("Форма из прошлого месяца");
        return ufm;
    }

    public static UploadImageModel getUploadImageModel(){
        UploadImageModel uim = new UploadImageModel();
        uim.setCreatedata(LocalDateTime.now());
        uim.setSize(0);
        uim.setPath("/test/test1/filename.png");
        uim.setName("filename.png");
        return uim;
    }
}
