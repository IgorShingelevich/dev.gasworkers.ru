package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static utils.SMSActivateUtil.*;

public class SmsDataTest {

@Test
    public  void verifyNewOrderNumberAllSources(){

    List<SmsData> smsContent = given()
            .when()
            .contentType(ContentType.JSON)
            .get(BASEURI + "?api_key=" + APIKEY + "&action=getRentStatus&id=" + RENTID)
             .then().log().all()
            .extract().body().jsonPath().getList("0", SmsData.class);
    int i  = 0;
    System.out.println(smsContent);
}


}
