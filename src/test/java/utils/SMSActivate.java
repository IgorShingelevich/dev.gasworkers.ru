package utils;

//import ru.sms_activate.SMSActivateAction;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.sms_activate.*;
        import ru.sms_activate.error.base.SMSActivateBaseException;
        import ru.sms_activate.response.api_rent.SMSActivateGetRentListResponse;

        import java.math.BigDecimal;

public class SMSActivate {

public static final String
        APIKEY =  "7424Adff2b7241e6b15e1cbdfdf25773";
public static final String BASEURI = "https://api.sms-activate.org/stubs/handler_api.php";

public static final int RENTID = 8148860;
SMSActivateApi managerSMS = new SMSActivateApi(APIKEY);

    public static void main(String[] args) throws SMSActivateBaseException {
        SMSActivate smsActivate = new SMSActivate();
        System.out.println("witAPI getRentList " + smsActivate.getSMSActivateRentList());
        System.out.println("withAPI getBalance " + smsActivate.getSMSActivateAccountBalance());
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
                .queryParam("id", RENTID);
        Response responseRentStatus = httpRentStatusRequest.request(Method.GET, "");
        int statusCodeRentStatus = responseRentStatus.getStatusCode();
        System.out.println("REST-ASSURED getRentStatus StatusCode is =>  " + statusCodeRentStatus);
        String responseBodyRentStatus = responseRentStatus.getBody().asString();
        System.out.println("REST-ASSURED getRentStatus ResponseBody is =>  " + responseBodyRentStatus);


    }

    public String getSMSActivateRentList() throws SMSActivateBaseException {
        SMSActivateGetRentListResponse rentListResponse = managerSMS.getRentList();
        return rentListResponse.toString();
    }
    public BigDecimal getSMSActivateAccountBalance() throws SMSActivateBaseException {
        return managerSMS.getBalance();
    }

















}
/*TODO
*  Returns the list sms.
* */