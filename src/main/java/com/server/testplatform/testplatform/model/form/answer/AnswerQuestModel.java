package com.server.testplatform.testplatform.model.form.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.form.UserQuestModel;

import javax.persistence.*;


@Entity
@Table(name = "answerquest")
public class AnswerQuestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long answer_id;

    @Column
    public String answer;

    @Column(nullable = false)
    private Boolean correctAnswer;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_id"  , insertable = true )
    private UserQuestModel quest;

    @PrePersist
    public void prePersist() {
        if(correctAnswer == null)
            correctAnswer = false;
    }



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserQuestModel getQuest() {
        return quest;
    }

    public void setQuest(UserQuestModel quest) {
        this.quest = quest;
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    @Override
    public String toString(){
        return "answer_id: "+ answer_id + "\n" + "answer: " + answer + "\n" + "correctAnswer: " + correctAnswer;
    }
}
