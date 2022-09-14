package com.server.testplatform.testplatform.model.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "userform")
public class UserFormModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long form_id;

    @Column
    private String formname;

    @Column
    private String description;

    @Column
    private String email;


    @Column(name = "createdata")
    private LocalDateTime createdata;


    @Column(name = "selectformtype")
    private Long selectformtype;



    @OneToOne(mappedBy = "userform", cascade = CascadeType.ALL)
    private SettingForm settingform;



    @OneToMany(targetEntity = UserQuestModel.class , cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy="userform")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserQuestModel> listQuest = new ArrayList();


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id"  , insertable = true )
    private UserModel user;

    public LocalDateTime getCreatedata() {
        return createdata;
    }

    public void setCreatedata(LocalDateTime createdata) {
        this.createdata = createdata;
    }

    //сортируем , что-бы в будущем  из него доставать список вопросов по id листа
    public List<UserQuestModel> getListQuest() {
        Comparator<UserQuestModel> compareById = Comparator.comparing(UserQuestModel::getQuest_id);
        if(listQuest != null & compareById != null){
            if(!isNull(listQuest)){
                Collections.sort(listQuest, compareById);
            }
        }
        return listQuest;
    }

    public Boolean isNull(List<UserQuestModel>  listQuest){
        return listQuest
                .stream()
                .anyMatch(x->x.getQuest_id() == null);
    }

    public void setListQuest(List<UserQuestModel> listQuest) {
        this.listQuest = listQuest;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public Long getForm_id() {
        return form_id;
    }

    public void setForm_id(Long form_id) {
        this.form_id = form_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
   }


    public UserModel getUser() {
        return user;
    }

     public void setUser(UserModel user) {
        this.user = user;
     }

    public Long getSelectformtype() {
        return selectformtype;
    }

    public void setSelectformtype(Long selectformtype) {
        this.selectformtype = selectformtype;
    }



    public SettingForm getSettingform() {
        return settingform;
    }

    public void setSettingform(SettingForm settingform) {
        this.settingform = settingform;
    }

    //public List<UserQuestModel> getListQuest() {
     //  return listQuest;
    //}

   // public void setListQuest(List<UserQuestModel> listQuest) {
     //   this.listQuest = listQuest;
    //}

    @Override
    public boolean equals(Object o) {
       if (this == o) {
            return true;
       }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        UserFormModel cmEquals = (UserFormModel) o;

       return form_id == cmEquals.getForm_id() &&
                cmEquals.getFormname().equals(formname)&&
                cmEquals.getDescription().equals(description)&&
                cmEquals.getEmail().equals(email);
    }



    @Override
    public int hashCode() {
        return (int) form_id.intValue() * formname.hashCode() * email.hashCode() * description.hashCode();
    }



}
