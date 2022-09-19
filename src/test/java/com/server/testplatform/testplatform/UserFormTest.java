package com.server.testplatform.testplatform;

import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.service.service.impl.support.UsersType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFormTest {

    UsersType ut;

    @BeforeEach
    void setUp() {
        ut = new UsersType();
    }

    @Test
    @DisplayName("Удаление формы со всеми детьми!")
    void userSize() {

    }
}
