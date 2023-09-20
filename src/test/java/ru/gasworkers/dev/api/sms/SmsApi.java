package ru.gasworkers.dev.api.sms;

import ru.gasworkers.dev.model.UserRole;
import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentNumber;
import ru.sms_activate.response.api_rent.extra.SMSActivateSMS;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

public final class SmsApi {

    public static SmsApi instance(Long phoneNumber) {
        try {
            Integer phoneNumberId = MANAGER_SMS.getRentList().getRentNumberList()
                    .stream()
                    .filter(number -> number.getNumber() == phoneNumber)
                    .map(SMSActivateRentNumber::getId)
                    .findFirst().orElse(null);

            if (phoneNumberId == null)
                throw new RuntimeException("Number " + phoneNumber + " not found");

            int initialSmsCount = MANAGER_SMS.getRentStatus(phoneNumberId).getSmsActivateSMSList().size();

            return new SmsApi(phoneNumberId, initialSmsCount);
        } catch (SMSActivateBaseException e) {
            throw new RuntimeException("During get sms ru.gasworkers.dev.api instance something went wrong");
        }
    }

    public static SmsApi instance(UserRole userRole) {
        return instance(userRole.getPhoneNumber());
    }

    private static final String API_KEY = "7424Adff2b7241e6b15e1cbdfdf25773";
    private static final SMSActivateApi MANAGER_SMS = new SMSActivateApi(API_KEY);

    private final int id;
    private final int initialSmsCount;

    private SmsApi(int id, int initialSmsCount) {
        this.id = id;
        this.initialSmsCount = initialSmsCount;
    }

    public List<SMSActivateSMS> getSmsList() {
        try {
            return MANAGER_SMS.getRentStatus(id).getSmsActivateSMSList();
        } catch (SMSActivateBaseException e) {
            throw new RuntimeException("During get sms list something went wrong");
        }
    }

    public SMSActivateSMS waitReceiveNewSms() {
        for (int i = 1; i <= 60; i++) {
            List<SMSActivateSMS> smsList = getSmsList();
            if (initialSmsCount < smsList.size() && !smsList.isEmpty())
                return smsList.get(0);
            sleep(1_000);
        }

        throw new RuntimeException("New sms not received");
    }

}
