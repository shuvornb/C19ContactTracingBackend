package com.isensor.contacttracingbackend.communication.web;

public class PatientInfo {
    public Long patientId;
    public String name;
    public String phoneNumber;
    public String address;
    public String gender;
    public Integer age;

    public PatientInfo() {
    }

    public PatientInfo(Long patientId, String name, String phoneNumber, String address, String gender, Integer age) {
        this.patientId = patientId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
