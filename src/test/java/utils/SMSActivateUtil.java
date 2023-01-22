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

public static final long clientPhone = 79288010225L;
public static final long masterPhone = 79917644241L;
public static SMSActivateApi managerSMS = new SMSActivateApi(APIKEY);
    public static String responseBodyRentStatus;


    public static void main(String[] args) throws SMSActivateBaseException {
        SMSActivateUtil smsActivateUtil = new SMSActivateUtil();
        int rentId = smsActivateUtil.getIDbyPhoneNumber(clientPhone);
        System.out.println("getLastSmsClient = " + smsActivateUtil.getLastSms(clientPhone));
        System.out.println("getLastSmsMaster = " + smsActivateUtil.getLastSms(masterPhone));
        System.out.println("witAPI getUserIdClientPhone " + smsActivateUtil.getIDbyPhoneNumber(clientPhone));
        System.out.println("witAPI getUserIdMasterPhone " + smsActivateUtil.getIDbyPhoneNumber(masterPhone));
        System.out.println("withAPI getBalance " + smsActivateUtil.getSMSActivateAccountBalance());

        RestAssured.baseURI = BASEURI;
        RequestSpecification httpBalanceRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getBalance");
        Response responseBalance = httpBalanceRequest.request(Method.GET, "");
        int statusCodeBalanceResponse = responseBalance.getStatusCode();
        System.out.println("REST-ASSURED getBalance StatusCode is =>  " + statusCodeBalanceResponse);
        String responseBodyBalance = responseBalance.getBody().asString();
        System.out.println("REST-ASSURED getBalance  =>  " + responseBodyBalance);


        RequestSpecification httpActiveActivationsRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getActiveActivations");
        Response responseActiveActivations = httpActiveActivationsRequest.request(Method.GET, "");
        int statusCodeActiveActivationsResponse = responseActiveActivations.getStatusCode();
        System.out.println("REST-ASSURED getActiveActivations StatusCode is =>  " + statusCodeActiveActivationsResponse);
        String responseBodyActiveActivations = responseActiveActivations.getBody().asString();
        System.out.println("REST-ASSURED getActiveActivations  =>  " + responseBodyActiveActivations);


        RequestSpecification httpRentStatusClientRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getRentStatus")
                .queryParam("id", smsActivateUtil.getIDbyPhoneNumber(clientPhone));
        Response responseRentClientStatus = httpRentStatusClientRequest.request(Method.GET, "");
        int statusCodeRentClientStatus = responseRentClientStatus.getStatusCode();
        System.out.println("REST-ASSURED getRentClientStatusCode  =>  " + statusCodeRentClientStatus);
        String responseBodyClientRentStatus = responseRentClientStatus.getBody().asString();
        System.out.println("REST-ASSURED getRentStatusClientPhone  =>  " + responseBodyClientRentStatus);

        RequestSpecification httpRentStatusMasterRequest = RestAssured.given()
                .queryParam("api_key", APIKEY)
                .queryParam("action", "getRentStatus")
                .queryParam("id", smsActivateUtil.getIDbyPhoneNumber(masterPhone));
        Response responseRentMasterStatus = httpRentStatusMasterRequest.request(Method.GET, "");
        int statusCodeRentMasterStatus = responseRentMasterStatus.getStatusCode();
        System.out.println("REST-ASSURED getRentMasterStatusCode =>  " + statusCodeRentMasterStatus);
        String responseBodyRentStatus = responseRentMasterStatus.getBody().asString();
        System.out.println("REST-ASSURED getRentStatusMasterPhone  =>  " + responseBodyRentStatus);

        smsActivateUtil.waitNewSMS(clientPhone, 2);
        smsActivateUtil.waitNewSMS(masterPhone, 2);
    }

    public BigDecimal getSMSActivateAccountBalance() throws SMSActivateBaseException {
        return managerSMS.getBalance();
    }

    public static Integer getIDbyPhoneNumber(long phoneNumber) throws SMSActivateBaseException {
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

    public static String getLastSms(long phoneNumber) throws SMSActivateBaseException {
        SMSActivateGetRentStatusResponse rentListResponse = managerSMS.getRentStatus(getIDbyPhoneNumber(phoneNumber));
       return  rentListResponse.getSmsActivateSMSList().get(0).getText();
    }

    public  String waitNewSMS(long phoneNumber, int timer) throws SMSActivateBaseException {

        SMSActivateGetRentStatusResponse rentListResponse = managerSMS.getRentStatus(getIDbyPhoneNumber(phoneNumber));
        int startCountSMS = rentListResponse.getCountSms();
        int currentCountSMS = startCountSMS;
        LocalTime startTime = LocalTime.now();
        LocalTime actualTime = LocalTime.now();
        while (actualTime.isBefore(startTime.plusSeconds(timer))) {
            if (currentCountSMS != startCountSMS) {
                return rentListResponse.getSmsActivateSMSList().get(0).getText();
            }
            actualTime = LocalTime.now();
            currentCountSMS = rentListResponse.getCountSms();
            System.out.println("waitNewSMS method is finished");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

















}
/*TODO
*  Returns the list sms.
* */