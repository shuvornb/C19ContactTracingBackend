package com.isensor.contacttracingbackend.controller.web;

import com.isensor.contacttracingbackend.communication.request.SignupRequest;
import com.isensor.contacttracingbackend.communication.request.Symptom;
import com.isensor.contacttracingbackend.communication.web.*;
import com.isensor.contacttracingbackend.db.entity.C19SymptomReporting;
import com.isensor.contacttracingbackend.db.entity.C19TestCenter;
import com.isensor.contacttracingbackend.db.entity.C19TreatmentLog;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.service.RegisterService;
import com.isensor.contacttracingbackend.service.web.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private WebService webService;

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    private final Logger log = LoggerFactory.getLogger(WebController.class);

    @RequestMapping(value = "/test-center/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        model.addAttribute("loginData", new WebLoginRequest());
        log.info("GET Login API Invoked from Web Application");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute WebLoginRequest request, Model model) {
        log.info("POST Login API Invoked from Web Application");
        Long id = webService.loginTestCenter(request);
        if(id != null) {
            model.addAttribute("dashboardURL", "0; url=/dashboard/"+id);
            return "login_response";
        }
        else return "login";
    }

    @RequestMapping(value = "/dashboard/{testCenterId}", method = RequestMethod.GET)
    public String dashboardGet(@PathVariable(value = "testCenterId") Long testCenterId, Model model) {
        log.info("GET dashboard API Invoked from Web Application");
        log.info("Test center id: {}", testCenterId);
        C19TestCenter testCenterDetails = webService.getTestCenterDetails(testCenterId);
        model.addAttribute("testCenterDetails", testCenterDetails);
        List<SelfReportInfo> selfReportList = webService.getSelfReportList(testCenterId);
        model.addAttribute("selfReportList", selfReportList);
        List<ActivePatientInfo> activePatientList = webService.getActivePatientList(testCenterId);
        model.addAttribute("activePatientList", activePatientList);
        return "dashboard";
    }

    @RequestMapping(value = "/details/{reportId}", method = RequestMethod.GET)
    public String detailsGet(@PathVariable(value = "reportId") Long reportId, Model model) {
        log.info("GET Details API Invoked from Web Application");
        C19SymptomReporting report = webService.getASingleReport(reportId);
        model.addAttribute("symptoms", webService.convertStringToList(report.symptoms));
        PatientInfo patientInfo = webService.getPatientInfo(report.userId);
        model.addAttribute("patientInfo", patientInfo);
        model.addAttribute("testURL", "/test/" + report.testCenterId + "/" + patientInfo.patientId + "/" + reportId);
        return "details";
    }

    @RequestMapping(value = "/test/{testCenterId}/{userId}/{reportId}", method = RequestMethod.GET)
    public String testGet(@PathVariable(value = "testCenterId") Long testCenterId, @PathVariable(value = "userId") Long userId, @PathVariable(value = "reportId") Long reportId, Model model) {
        log.info("GET Test API Invoked from Web Application");
        log.info("testCenterId: {} & userId: {} & reportId: {}", testCenterId, userId, reportId);
        webService.initiateTest(testCenterId, userId, reportId);
        model.addAttribute("dashboardURL", "0; url=/dashboard/"+testCenterId);
        return "login_response";
    }

    @RequestMapping(value = "/register/user", method = RequestMethod.GET)
    public String registerUserGet(Model model) {
        model.addAttribute("registrationData", new SignupRequest());
        log.info("GET register user API Invoked from Web Application");
        return "register";
    }

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public String registerUserPost(@ModelAttribute SignupRequest request, Model model) {
        log.info("POST register user API Invoked from Web Application");
        log.info("RegistrationData: {}", request.toString());
        registerService.signup(request);
        return "registration_response";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String userLoginGet(Model model) {
        model.addAttribute("loginData", new WebLoginRequest());
        log.info("GET Login API Invoked from Web Application");
        return "login";
    }

    @RequestMapping(value = "/test-results/{treatmentLogId}", method = RequestMethod.GET)
    public String testResultsGet(@PathVariable(value = "treatmentLogId") Long treatmentLogId, Model model) {
        model.addAttribute("testResultsData", new UpdateTestResultsRequest());
        model.addAttribute("treatmentLogId", treatmentLogId);
        log.info("Test Results GET API Invoked from Web Application");
        log.info("Treatment Log Id: {}",treatmentLogId);
        return "test_results";
    }


    @RequestMapping(value = "/test-results", method = RequestMethod.POST)
    public String testResultsPost(@ModelAttribute UpdateTestResultsRequest request, Model model) {
        log.info("POST register user API Invoked from Web Application");
        log.info("TestResultData: {}", request.toString());
        C19TreatmentLog treatmentLog = webService.updateTestResults(request);
        model.addAttribute("dashboardURL", "2; url=/dashboard/"+treatmentLog.testCenterId);
        return "test_result_response";
    }
}
