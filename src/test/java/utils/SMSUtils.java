package utils;

import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
//import ru.sms_activate.SMSActivateAction;
import ru.sms_activate.*;
import ru.sms_activate.error.SMSActivateUnknownException;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_activation.SMSActivateActivation;
import ru.sms_activate.response.api_activation.SMSActivateGetFullSmsResponse;
import ru.sms_activate.response.api_rent.SMSActivateGetRentListResponse;
import ru.sms_activate.response.api_rent.SMSActivateGetRentStatusResponse;

import java.math.BigDecimal;
import java.util.List;

public class SMSUtils {

String apiKey =  "7424Adff2b7241e6b15e1cbdfdf25773";
  int rentID = 8148860;
SMSActivateApi managerSMS = new SMSActivateApi(apiKey);

    public static void main(String[] args) throws SMSActivateBaseException {
        SMSUtils smsUtils = new SMSUtils();
        System.out.println("getRentList " +smsUtils.getRentList());
        System.out.println("getBalance " + smsUtils.getBalance());
    }


    public String getRentList() throws SMSActivateBaseException {
        SMSActivateGetRentListResponse rentListResponse = managerSMS.getRentList();
        return rentListResponse.toString();
    }

    public BigDecimal getBalance() throws SMSActivateBaseException {
        return managerSMS.getBalance();
    }













}
/*TODO
*  Returns the list sms.
* */