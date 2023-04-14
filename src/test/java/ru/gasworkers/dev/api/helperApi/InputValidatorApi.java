package ru.gasworkers.dev.api.helperApi;
import ru.gasworkers.dev.model.apiModel.UserType;

public class InputValidatorApi {

    public static void validateRegistrationCode(String code, UserType userType, String email, String phone) {
        validateCode(code);
        validateUserType(userType);
        validateEmailOrPhone(email, phone);
    }

    public static void validateRegularStartRegistration(UserType userType, String email, String phone, Boolean isPhoneSend) {
        validateUserType(userType);
        validateEmailOrPhone(email, phone);
        validateIsPhoneSend(isPhoneSend);
    }

    private static void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty.");
        }
    }

    private static void validateUserType(UserType userType) {
        if (userType == null) {
            throw new IllegalArgumentException("UserType cannot be null.");
        }
    }

    private static void validateEmailOrPhone(String email, String phone) {
        if ((email == null || email.isEmpty()) && (phone == null || phone.isEmpty())) {
            throw new IllegalArgumentException("Either email or phone must be provided.");
        }
    }

    private static void validateIsPhoneSend(Boolean isPhoneSend) {
        if (isPhoneSend == null) {
            isPhoneSend = false;
        }
    }

    // Add more private validation methods for optional parameters as needed
}