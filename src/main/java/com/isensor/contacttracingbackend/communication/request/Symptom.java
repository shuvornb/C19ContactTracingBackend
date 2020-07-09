package com.isensor.contacttracingbackend.communication.request;

public class Symptom {
    public Integer serialNo;
    public String symptomName;
    public String symptomStatus;

    public Symptom() {
    }

    public Symptom(Integer serialNo, String symptomName, String symptomStatus) {
        this.serialNo = serialNo;
        this.symptomName = symptomName;
        this.symptomStatus = symptomStatus;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "serialNo=" + serialNo +
                ", symptomName='" + symptomName + '\'' +
                ", symptomStatus='" + symptomStatus + '\'' +
                '}';
    }
}
