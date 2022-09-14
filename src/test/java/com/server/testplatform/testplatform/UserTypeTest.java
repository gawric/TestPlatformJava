package com.server.testplatform.testplatform;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.service.service.impl.AdminServiceUsers;
import com.server.testplatform.testplatform.service.service.impl.support.UsersType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTypeTest {

    UsersType ut;

    @BeforeEach
    void setUp() {
        ut = new UsersType();
    }


    @Test
    @DisplayName("Проверка модели UserFormTypeModel")
    void userSize() {
        List<UserFormTypeModel> mockUser = mock(List.class);
        when(mockUser.get(1)).thenReturn(new UserFormTypeModel());
        when(mockUser.size()).thenReturn(1);
        assertTrue(mockUser.size() == 1);
    }

    @Test
    @DisplayName("Получить все типы юзера")
    void getAllTypeUser() {
        UserModel mockUser = mock(UserModel.class);
        when(mockUser.getListtype()).thenReturn(getListModel());

        assertTrue(ut.getDataType(mockUser).size() == 1);
    }


    @Test
    @DisplayName("Проверка фильтрации FormList по его типу")
    void getAllFormToType() {
        UserModel mockUser = mock(UserModel.class);
        when(mockUser.getListtype()).thenReturn(getListModel());
        when(mockUser.getListForm()).thenReturn(getListFormModel());

        List<UserFormModel> formToType = ut.getListForm(mockUser , 23l);



        assertTrue(formToType.size() == 1);
    }

    private List<UserFormModel> getListFormModel(){
        List<UserFormModel> listForm = new ArrayList<>();
        UserFormModel ufm = new UserFormModel();
        ufm.setForm_id(1l);
        ufm.setFormname("Архитектурный тест");
        ufm.setSelectformtype(23l);

        UserFormModel ufm2 = new UserFormModel();
        ufm2.setForm_id(2l);
        ufm2.setFormname("Не Архитектурный тест");
        ufm2.setSelectformtype(99l);

        listForm.add(ufm);
        listForm.add(ufm2);

        return listForm;
    }

    private List<UserFormTypeModel> getListModel(){
        List<UserFormTypeModel> model = new ArrayList<>();
        UserFormTypeModel ufm = new UserFormTypeModel();
        ufm.setTypename("Ген.план");
        ufm.setType_id(23l);
        model.add(ufm);

        return model;
    }
}
