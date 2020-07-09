package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19SymptomReporting;

import java.util.List;

public class FetchAssessmentReportsResponse extends BasicRestResponse{
    public int assessmentReportCount;
    public List<C19SymptomReporting> assessmentReportList;

    public FetchAssessmentReportsResponse() {
    }

    public FetchAssessmentReportsResponse(int assessmentReportCount, List<C19SymptomReporting> assessmentReportList) {
        this.assessmentReportCount = assessmentReportCount;
        this.assessmentReportList = assessmentReportList;
    }

    public FetchAssessmentReportsResponse(String message, int assessmentReportCount, List<C19SymptomReporting> assessmentReportList) {
        super(message);
        this.assessmentReportCount = assessmentReportCount;
        this.assessmentReportList = assessmentReportList;
    }

    @Override
    public String toString() {
        return "FetchAssessmentReportsResponse{" +
                "assessmentReportCount=" + assessmentReportCount +
                ", assessmentReportList=" + assessmentReportList +
                ", message='" + message + '\'' +
                '}';
    }
}
