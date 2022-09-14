package com.server.testplatform.testplatform.model.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.UserModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userformtype")
public class UserFormTypeModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long type_id;

    private String typename;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id"  , insertable = true )
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserFormTypeModel cmEquals = (UserFormTypeModel) o;
        return type_id == cmEquals.getType_id() &&
                cmEquals.getTypename().equals(typename);
    }



    @Override
    public int hashCode() {
        return (int) type_id.intValue() * typename.hashCode();
    }


}
