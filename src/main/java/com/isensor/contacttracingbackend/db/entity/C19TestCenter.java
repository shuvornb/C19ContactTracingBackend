package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_test_center")
public class C19TestCenter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "address")
    public String address;

    @Column(name = "hours")
    public String hours;

    @Column(name = "phone")
    public String phone;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;

    @Column(name = "criteria")
    public String criteria;

    @Column(name = "testing_type")
    public String testingType;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    public C19TestCenter() {
    }

    public C19TestCenter(Long id, String title, String address, String hours, String phone, Double latitude, Double longitude, String criteria, String testingType, String username, String password) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.hours = hours;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.criteria = criteria;
        this.testingType = testingType;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "C19TestCenter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", hours='" + hours + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", criteria='" + criteria + '\'' +
                ", testingType='" + testingType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

