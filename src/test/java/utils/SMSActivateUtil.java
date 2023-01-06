package utils;

//import ru.sms_activate.SMSActivateAction;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.sms_activate.*;
        import ru.sms_activate.error.base.SMSActivateBaseException;
        import ru.sms_activate.response.api_rent.SMSActivateGetRentListResponse;
import ru.sms_activate.response.api_rent.SMSActivateGetRentStatusResponse;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentNumber;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class SMSActivateUtil {

public static final String
        APIKEY =  "7424Adff2b7241e6b15e1cbdfdf25773";
public static final String BASEURI = "https://api.sms-activate.org/stubs/handler_api.php";

public static final long userPhone = 79033068834L;
SMSActivateApi managerSMS = new SMSActivateApi(APIKEY);
    public static String responseBodyRentStatus;


    public static void main(String[] args) throws SMSActivateBaseException {
        SMSActivateUtil smsActivateUtil = new SMSActivateUtil();
        int rentId = smsActivateUtil.getIDbyPhoneNumber(userPhone);
        System.out.println("getLastSMS = " + smsActivateUtil.getLastSMS(userPhone));
        System.out.println("witAPI getUserId " + smsActivateUtil.getIDbyPhoneNumber(userPhone));
        System.out.println("withAPI getBalance " + smsActivateUtil.getSMSActivateAccountBalance());
        RestAssured.baseURI = BASEURI;
        RequestSpecification httpBalanceRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getBalance");
        Response responseBalance = httpBalanceRequest.request(Method.GET, "");
        int statusCodeBalanceResponse = responseBalance.getStatusCode();
        System.out.println("REST-ASSURED getBalance StatusCode is =>  " + statusCodeBalanceResponse);
        String responseBodyBalance = responseBalance.getBody().asString();
        System.out.println("REST-ASSURED getBalance ResponseBody is =>  " + responseBodyBalance);


        RequestSpecification httpActiveActivationsRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getActiveActivations");
        Response responseActiveActivations = httpActiveActivationsRequest.request(Method.GET, "");
        int statusCodeActiveActivationsResponse = responseActiveActivations.getStatusCode();
        System.out.println("REST-ASSURED getActiveActivations StatusCode is =>  " + statusCodeActiveActivationsResponse);
        String responseBodyActiveActivations = responseActiveActivations.getBody().asString();
        System.out.println("REST-ASSURED getActiveActivations ResponseBody is =>  " + responseBodyActiveActivations);


        RequestSpecification httpRentStatusRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getRentStatus")
                .queryParam("id", smsActivateUtil.getIDbyPhoneNumber(userPhone));
        Response responseRentStatus = httpRentStatusRequest.request(Method.GET, "");
        int statusCodeRentStatus = responseRentStatus.getStatusCode();
        System.out.println("REST-ASSURED getRentStatus StatusCode is =>  " + statusCodeRentStatus);
        String responseBodyRentStatus = responseRentStatus.getBody().asString();
        System.out.println("REST-ASSURED getRentStatus ResponseBody is =>  " + responseBodyRentStatus);
    }
    // put responseBodyRentStatus  in a public variable and use it in the test


    public Integer getIDbyPhoneNumber(long phoneNumber) throws SMSActivateBaseException {
        SMSActivateGetRentListResponse rentListResponse = managerSMS.getRentList();
        List<SMSActivateRentNumber> listRentNumbers = rentListResponse.getRentNumberList();
        // search with the cycle for the rentID with the phone number
        for (SMSActivateRentNumber rentNumber : listRentNumbers) {
            if (rentNumber.getNumber() == phoneNumber) {
                return rentNumber.getId();
            }
        }
        return null;
    }


    public String getLastSMS(long phoneNumber) throws SMSActivateBaseException {
        SMSActivateGetRentStatusResponse rentListResponse = managerSMS.getRentStatus(getIDbyPhoneNumber(phoneNumber));
       return  rentListResponse.getSmsActivateSMSList().get(0).getText();
    }

/*    public String waitNewSMS(long phoneNumber) throws SMSActivateBaseException {
        SMSActivateGetRentStatusResponse rentListResponse = managerSMS.getRentStatus(getIDbyPhoneNumber(phoneNumber));
//        int startCountSMS = rentListResponse.getSmsActivateSMSList().size();
        int startCountSMS = rentListResponse.getCountSms();
        int currentCountSMS = startCountSMS;
        // get actual time
         LocalTime startTime = LocalTime.now();
         LocalTime actualTime = LocalTime.now();
        while (currentCountSMS == startCountSMS) {

        }


    }*/


    public BigDecimal getSMSActivateAccountBalance() throws SMSActivateBaseException {
        return managerSMS.getBalance();
    }

















}
/*TODO
*  Returns the list sms.
* */