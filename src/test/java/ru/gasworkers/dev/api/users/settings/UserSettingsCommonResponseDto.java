package ru.gasworkers.dev.api.users.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingsCommonResponseDto {

    private Integer status;
    private String message;
    private DataDto data;

    public static UserSettingsCommonResponseDto defaultBGUserResponse(User user, CommonFieldsRepairDto commonFields) {
        return UserSettingsCommonResponseDto.builder()
                .status(0)
                .message("Профиль обновлен")
                .data(DataDto.builder()
                        .id(commonFields.getClientId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .middleName(user.getMiddleName())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .login(null)
                        .emailVerifiedAt(1690395976)
                        .joinedAt(null)
                        .registeredAt(1690395976)
                        .phone(user.getPhoneAsLong())
                        .contacts(Contacts.builder()
                                .facetime(null)
                                .whatsapp(null)
                                .skype(null)
                                .telegram(null)
                                .build())
                        .type("client")
                        .passport(Passport.builder()
                                .id(commonFields.getPassportId())
                                .series("1111")
                                .number("111111")
                                .issuedDate(Math.toIntExact(user.getPassportTimestamp()))
                                .issuedBy("овд Автотестовского района г. Автотестово")
                                .registrationAddress("Москва, Московская улица")
                                .createdAt(1690444618)
                                .build())
                        .avatar(null)
                        .diploma(null)
                        .selfEmployedCertificate(null)
                        .selfEmployedCertificateExpired("")
                        .isFullProfile(true)
                        .isHalfFullProfile(true)
                        .specialization(null)
                        .skills(null)
                        .countObjects(1)
                        .countOrders(null)
                        .requisitesFilled(false)
                        .bankDetailsFilled(false)
                        .rateFilled(false)
                        .priceListFilled(false)
                        .distributionCompanyAgreementFilled(false)
                        .equipmentsFilled(false)
                        .contactsFilled(false)
                        .profileCompleted(false)
                        .createdAt("1690395975")
                        .company(null)
                        .service(null)
                        .companyAsDispatcher(null)
                        .serviceId(null)
                        .distributionCompanyId(null)
                        .onVerification(false)
                        .verifiedAt(null)
                        .bank(null)
                        .accountNumber(null)
                        .firstAccept(0)
                        .secondAccept(null)
                        .country(null)
                        .postAddress(null)
                        .employmentStatus(null)
                        .brands(List.of())
                        .certificates(List.of())
                        .insuranceStartedAt(null)
                        .rating("5.00")
                        .reviewsCount(0)
                        .completedOrdersCount(0)
                        .acceptTerms(true)
                        .newMessagesCount(0)
                        .userNotificationsCount(0)
                        .pushNotificationsEnable(true)
                        .emailNotificationsEnable(true)
                        .smsNotificationsEnable(true)
                        .readyForConsultation(false)
                        .canConsulting(false)
                        .isHaveContract(false)
                        .isIp(false)
                        .inn(null)
                        .activeRole(null)
                        .consultationPrice(0)
                        .livingAddressId(null)
                        .livingAddress(null)
                        .workDistance(null)
                        .guides(List.of(Guide.builder()
                                .id(1774)
                                .type("through_register")
                                .needStart(true)
                                .finished(false)
                                .screen(0)
                                .build()))
                        .lastReview(null)
                        .fpsPhone(null)
                        .selectedPaymentMethod(null)
                        .fpsBank(null)
                        .additionalPhone(null)
                        .build())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contacts {
        private String facetime;
        private String whatsapp;
        private String skype;
        private String telegram;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        private Integer id;
        private String firstName;
        private String lastName;
        private String middleName;
        @JsonProperty("full_name")
        private String fullName;
        private String email;
        private String login;
        @JsonProperty("email_verified_at")
        private Integer emailVerifiedAt;
        @JsonProperty("joined_at")
        private String joinedAt;
        @JsonProperty("registered_at")
        private Integer registeredAt;
        private Long phone;
        private Contacts contacts;
        private String type;
        private Passport passport;
        private String avatar;
        private String diploma;
        private String selfEmployedCertificate;
        @JsonProperty("self_employed_certificate_expired")
        private String selfEmployedCertificateExpired;
        @JsonProperty("is_full_profile")
        private Boolean isFullProfile;
        @JsonProperty("is_half_full_profile")
        private Boolean isHalfFullProfile;
        private String specialization;
        private String skills;
        @JsonProperty("count_objects")
        private Integer countObjects;
        @JsonProperty("count_orders")
        private Integer countOrders;
        @JsonProperty("requisites_filled")
        private Boolean requisitesFilled;
        @JsonProperty("bank_details_filled")
        private Boolean bankDetailsFilled;
        @JsonProperty("rate_filled")
        private Boolean rateFilled;
        @JsonProperty("price_list_filled")
        private Boolean priceListFilled;
        @JsonProperty("distribution_company_agreement_filled")
        private Boolean distributionCompanyAgreementFilled;
        @JsonProperty("equipments_filled")
        private Boolean equipmentsFilled;
        @JsonProperty("contacts_filled")
        private Boolean contactsFilled;
        @JsonProperty("profile_completed")
        private Boolean profileCompleted;
        @JsonProperty("created_at")
        private String createdAt;
        private Object company;
        private Object service;
        private Object companyAsDispatcher;
        @JsonProperty("service_id")
        private Integer serviceId;
        @JsonProperty("distribution_company_id")
        private Integer distributionCompanyId;
        @JsonProperty("on_verification")
        private Boolean onVerification;
        @JsonProperty("verified_at")
        private String verifiedAt;
        private Object bank;
        @JsonProperty("account_number")
        private Integer accountNumber;
        @JsonProperty("first_accept")
        private Integer firstAccept;
        @JsonProperty("second_accept")
        private Integer secondAccept;
        private String country;
        @JsonProperty("post_address")
        private String postAddress;
        @JsonProperty("employment_status")
        private String employmentStatus;
        private List<Object> brands;
        private List<Object> certificates;
        @JsonProperty("insurance_started_at")
        private String insuranceStartedAt;
        private String rating;
        @JsonProperty("reviews_count")
        private Integer reviewsCount;
        @JsonProperty("completed_orders_count")
        private Integer completedOrdersCount;
        @JsonProperty("accept_terms")
        private Boolean acceptTerms;
        @JsonProperty("new_messages_count")
        private Integer newMessagesCount;
        @JsonProperty("user_notifications_count")
        private Integer userNotificationsCount;
        @JsonProperty("push_notifications_enable")
        private Boolean pushNotificationsEnable;
        @JsonProperty("email_notifications_enable")
        private Boolean emailNotificationsEnable;
        @JsonProperty("sms_notifications_enable")
        private Boolean smsNotificationsEnable;
        @JsonProperty("ready_for_consultation")
        private Boolean readyForConsultation;
        @JsonProperty("can_consulting")
        private Boolean canConsulting;
        @JsonProperty("is_have_contract")
        private Boolean isHaveContract;
        @JsonProperty("is_ip")
        private Boolean isIp;
        private Integer inn;
        @JsonProperty("active_role")
        private String activeRole;
        @JsonProperty("consultation_price")
        private Integer consultationPrice;
        @JsonProperty("living_address_id")
        private Integer livingAddressId;
        @JsonProperty("living_address")
        private String livingAddress;
        @JsonProperty("work_distance")
        private Integer workDistance;
        private List<Guide> guides;
        @JsonProperty("last_review")
        private String lastReview;
        @JsonProperty("fps_phone")
        private String fpsPhone;
        @JsonProperty("selected_payment_method")
        private String selectedPaymentMethod;
        @JsonProperty("fps_bank")
        private Object fpsBank;
        @JsonProperty("additional_phone")
        private Long additionalPhone;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Guide {
        private Integer id;
        private String type;
        @JsonProperty("need_start")
        private Boolean needStart;
        private Boolean finished;
        private Integer screen;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Passport {
        private Integer id;
        private String series;
        private String number;
        @JsonProperty("issued_date")
        private Integer issuedDate;
        @JsonProperty("issued_by")
        private String issuedBy;
        @JsonProperty("registration_address")
        private String registrationAddress;
        @JsonProperty("created_at")
        private Integer createdAt;
    }
}
