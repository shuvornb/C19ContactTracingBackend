package com.isensor.contacttracingbackend.communication.web;

public class SelfReportInfo {
    public Long serialNo;
    public String name;
    public String phoneNumber;
    public String reportingTime;
    public String reportURL;

    public SelfReportInfo() {
    }

    public SelfReportInfo(Long serialNo, String name, String phoneNumber, String reportingTime, String reportURL) {
        this.serialNo = serialNo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.reportingTime = reportingTime;
        this.reportURL = reportURL;
    }

    @Override
    public String toString() {
        return "SelfReportInfo{" +
                "serialNo=" + serialNo +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reportingTime='" + reportingTime + '\'' +
                ", reportURL='" + reportURL + '\'' +
                '}';
    }
}
