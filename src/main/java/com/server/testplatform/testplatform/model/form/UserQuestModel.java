package com.server.testplatform.testplatform.model.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userquest")
public class UserQuestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long quest_id;

    @Column
    private String quest;

    @Column
    private Boolean selectAnswer;

    @Column
    private String image;

    @Column
    private Double image_ver;

    @Column
    private Double image_gor;

    @Column
    private String image_align;

    @Column
    private Integer number_line;


    @Column(name = "createdata")
    private LocalDateTime createdata;

    @OneToMany(targetEntity = AnswerQuestModel.class , cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy="quest")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AnswerQuestModel> listAnswer= new ArrayList();


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id"  , insertable = true )
    private UserFormModel userform;


    public UserFormModel getUserform() {
        return userform;
    }

    public void setUserform(UserFormModel userform) {
        this.userform = userform;
    }

    public Integer getNumber_line() {
        return number_line;
    }

    public void setNumber_line(Integer number_line) {
        this.number_line = number_line;
    }




    public List<AnswerQuestModel> getListAnswer() {
        return listAnswer;
     }

    public void setListAnswer(List<AnswerQuestModel> listAnswer) {
        this.listAnswer = listAnswer;
    }


    public LocalDateTime getCreatedata() {
        return createdata;
   }

    public void setCreatedata(LocalDateTime createdata) {
        this.createdata = createdata;
    }


    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public Boolean getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(Boolean selectAnswer) {
        this.selectAnswer = selectAnswer;
    }

    public String getImage() {
        return image;
    }

    public Double getImage_ver() {
        return image_ver;
    }

    public void setImage_ver(Double image_ver) {
        this.image_ver = image_ver;
    }

    public Double getImage_gor() {
        return image_gor;
    }

    public void setImage_gor(Double image_gor) {
        this.image_gor = image_gor;
    }

    public String getImage_align() {
        return image_align;
    }

    public void setImage_align(String image_align) {
        this.image_align = image_align;
    }


    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserQuestModel cmEquals = (UserQuestModel) o;
        return quest_id == cmEquals.getQuest_id() &&
                cmEquals.getQuest().equals(quest)&&
                cmEquals.getImage().equals(image);
    }


    public Long getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(Long quest_id) {
        this.quest_id = quest_id;
    }


    @Override
    public int hashCode() {
        return   quest_id.intValue() * quest.hashCode() * image.hashCode();
    }
}
