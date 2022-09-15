package com.server.testplatform.testplatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserFormTypeModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long user_id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;

    @Column(name = "create_data")
    private LocalDateTime createdata;


    @Column(name = "last_data")
    private LocalDateTime lastenterdata;

    private boolean enabled;
    private boolean accountNonExpired;


    @Transient
    private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

    private boolean credentialsNonExpired;
    private boolean accountNonLocked;

    @OneToMany(targetEntity = UserFormModel.class , cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy="user")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserFormModel> listForm= new ArrayList();



    @OneToMany(targetEntity = UploadImageModel.class , cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy="user")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UploadImageModel> listUpload= new ArrayList();


    @JsonIgnore
    @OneToMany(targetEntity = UserFormTypeModel.class , cascade = CascadeType.PERSIST , orphanRemoval = true, fetch = FetchType.EAGER, mappedBy="user")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserFormTypeModel> listtype = new ArrayList();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public LocalDateTime getLastenterdata() {
        return lastenterdata;
    }

    public void setLastenterdata(LocalDateTime lastenterdata) {
        this.lastenterdata = lastenterdata;
    }


    public LocalDateTime getCreatedata() {
        return createdata;
    }

    public void setCreatedata(LocalDateTime createdata) {
       this.createdata = createdata;
    }

    public List<UserFormModel> getListForm() {
        return listForm;
    }

    public void setListForm(List<UserFormModel> listForm) {
        this.listForm = listForm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UploadImageModel> getListUpload() {
        return listUpload;
    }

    public void setListUpload(List<UploadImageModel> listUpload) {
        this.listUpload = listUpload;
    }

    public List<UserFormTypeModel> getListtype() {
        return listtype;
    }

    public void setListtype(List<UserFormTypeModel> listtype) {
        this.listtype = listtype;
    }

    public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
        if(grantedAuthoritiesList.size() == 0){
            grantedAuthoritiesList.add(new SimpleGrantedAuthority(getRole()));
        }
        return grantedAuthoritiesList;
    }

    public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
        this.grantedAuthoritiesList = grantedAuthoritiesList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserModel cmEquals = (UserModel) o;
        return user_id == cmEquals.getUser_id() &&
                cmEquals.getUsername().equals(username)&&

                cmEquals.getRole().equals(role);
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    @Override
    public int hashCode() {
        return  user_id.intValue() * role.hashCode() * username.hashCode();
    }

    @Override
    public String toString(){
        return "Пользователь: " + username + " роль " + role;
    }


}
