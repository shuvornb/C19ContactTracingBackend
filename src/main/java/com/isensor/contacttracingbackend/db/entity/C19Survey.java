package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_survey")
public class C19Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "survey_completion_time")
    public Long surveyCompletionTime;

    @Column(name = "response")
    public String response;

    public C19Survey() {
    }

    public C19Survey(Long id, Long userId, Long surveyCompletionTime, String response) {
        this.id = id;
        this.userId = userId;
        this.surveyCompletionTime = surveyCompletionTime;
        this.response = response;
    }

    @Override
    public String toString() {
        return "C19Survey{" +
                "id=" + id +
                ", userId=" + userId +
                ", surveyCompletionTime=" + surveyCompletionTime +
                ", response='" + response + '\'' +
                '}';
    }
}

