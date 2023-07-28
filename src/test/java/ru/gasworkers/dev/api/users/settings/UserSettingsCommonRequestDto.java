package ru.gasworkers.dev.api.users.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.extension.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingsCommonRequestDto {

    private String lastName;
    private String firstName;
    private String middleName;
    @JsonProperty("avatar_id")
    private String avatarId;
    private Passport passport;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("diploma_id")
    private String diplomaId;
    @JsonProperty("self_employed_certificate_id")
    private String selfEmployedCertificateId;
    private String inn;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("bank_title")
    private String bankTitle;
    @JsonProperty("bank_inn")
    private String bankInn;
    @JsonProperty("bank_kpp")
    private String bankKpp;
    @JsonProperty("bank_bik")
    private String bankBik;
    @JsonProperty("bank_correspondent_account")
    private String bankCorrespondentAccount;
    private String phone;
    @JsonProperty("selected_payment_method")
    private String selectedPaymentMethod;
    @JsonProperty("fps_phone")
    private String fpsPhone;

    public static UserSettingsCommonRequestDto defaultBGClientRequest(User user) {
        return UserSettingsCommonRequestDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .phone(user.getPhone())
                .passport(Passport.builder()
                        .series("1111")
                        .number("111111")
                        .issuedBy("овд Автотестовского района г. Автотестово")
                        .registrationAddress("Москва, Московская улица")
                        .issuedDate(user.getPassportTimestamp())
                        .build())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Passport {
        private String series;
        private String number;
        @JsonProperty("issued_by")
        private String issuedBy;
        @JsonProperty("registration_address")
        private String registrationAddress;
        @JsonProperty("issued_date")
        private Long issuedDate;
    }
}
