package ru.gasworkers.dev.api.orders.info.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInfoResponseDto {


    private Integer status;
    private String message;
    private Data data;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
//    @JsonIgnoreProperties(ignoreUnknown = true, value = {"clientObject"})
    public static class Data {
        private Integer id;
        private String number;
        private String status;
        private String type;
        @JsonProperty("service_center")
        private ServiceCenter serviceCenter;
        private Object master;
        private Client client;
        private Receipt receipt;
        private List<Receipts> receipts;
        private ArrayList<Object> codes;
        private String stage;
        private Offer offer;
        @JsonProperty("desired_date_interval_started_at")
        private Object desiredDateIntervalStartedAt;
        @JsonProperty("desired_date_interval_ended_at")
        private Object desiredDateIntervalEndedAt;
        @JsonProperty("desired_time_started")
        private Object desiredTimeStarted;
        @JsonProperty("desired_time_ended")
        private Object desiredTimeEnded;
        @JsonProperty("insurance_type")
        private Object insuranceType;
        @JsonProperty("repair_actions")
        private ArrayList<Object> repairActions;
        @JsonProperty("material_values")
        private ArrayList<Object> materialValues;
        @JsonProperty("need_sign")
        private Boolean needSign;
        @JsonProperty("sign_type")
        private Object signType;
        @JsonProperty("record_started_at")
        private Object recordStartedAt;
        @JsonProperty("need_master_sign")
        private Boolean needMasterSign;
        @JsonProperty("master_sign_type")
        private Object masterSignType;
        @JsonProperty("last_code_sent")
        private Object lastCodeSent;
        private ArrayList<Object> files;
        @JsonProperty("conference_url")
        private String conferenceUrl;
        @JsonProperty("consultation_canceled")
        private Boolean consultationCanceled;
        private Boolean refunded;
        private ArrayList<Equipments> equipments;
        @JsonProperty("client_object")
        private ClientObject clientObject;
        @JsonProperty("creation_status")
        private Object creationStatus;
        private Boolean online;
        private ArrayList<History> history;
        @JsonProperty("from_maintenance")
        private Boolean fromMaintenance;
        @JsonProperty("is_insurance_case")
        private Boolean isInsuranceCase;
        @JsonProperty("canceled_by_master")
        private Boolean canceledByMaster;
        @JsonProperty("canceled_by_client")
        private Boolean canceledByClient;
        @JsonProperty("money_back_available")
        private Boolean moneyBackAvailable;
        @JsonProperty("possible_offer_id")
        private Integer possibleOfferId;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Receipt {
            private Integer id;
            private Double amount;
            @JsonProperty("remains_to_pay")
            private Double remainsToPay;
            private Boolean paid;
            private String title;
            @JsonProperty("order_id")
            private Integer orderId;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
            private String stage;
            private Boolean hide;
            private Boolean refunded;
            private Boolean cash;
            @JsonProperty("is_insurance")
            private Boolean isInsurance;
            @JsonProperty("telegram_sent")
            private Boolean telegramSent;
        }


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Offer {
            private Integer id;
            @JsonProperty("order_id")
            private Integer orderId;
            @JsonProperty("company_id")
            private Integer companyId;
            private String status;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
            private Integer price;
            @JsonProperty("price_wi")
            private Integer priceWi;
            @JsonProperty("master_id")
            private Object masterId;
            @JsonProperty("start_price")
            private Object startPrice;
            @JsonProperty("price_real")
            private Integer priceReal;
            @JsonProperty("with_consultation")
            private Boolean withConsultation;
            @JsonProperty("consultation_passed")
            private Boolean consultationPassed;
            @JsonProperty("master_started")
            private Boolean masterStarted;
            @JsonProperty("wait_from")
            private Object waitFrom;
            @JsonProperty("wait_notification")
            private Integer waitNotification;
            @JsonProperty("master_selected")
            private Boolean masterSelected;
            private Boolean hide;
            @JsonProperty("client_closed")
            private Boolean clientClosed;
            private Boolean showed;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ServiceCenter {
            private Integer id;
            @JsonProperty("short_title")
            private String shortTitle;
            private String logo;
            private String rating;
            @JsonProperty("first_accept_price")
            private Double firstAcceptPrice;
            @JsonProperty("full_price")
            private Object fullPrice;
            @JsonProperty("full_price_wi")
            private Object fullPriceWi;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Receipts {
            private Integer id;
            private Double amount;
            private Boolean paid;
            private String title;
            @JsonProperty("remains_to_pay")
            private Double remainsToPay;
            @JsonProperty("created_at")
            private String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Equipments {
            @JsonProperty("id")
            private Integer id;
            private String title;
            @JsonProperty("is_show_hidden")
            private Boolean isShowHidden;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class History {
            private Integer id;
            @JsonProperty("src_title")
            private String srcTitle;
            private String title;
            private String description;
            @JsonProperty("set_at")
            private String setAt;
            @JsonProperty("completed_at")
            private String completedAt;
            private Boolean completed;
            @JsonProperty("is_current")
            private Boolean isCurrent;
        }


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ClientObject {
            private Integer id;
            private String title;
            @JsonProperty("last_photo")
            private Object lastPhoto;
            private Address address;

            @lombok.Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Address {
                private Integer id;
                private String full;
                private ArrayList<Double> coordinates;
            }
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Client {
            String gender;
            String specialization;
            String skills;
            @JsonProperty("avatar_id")
            String avatarId;
            @JsonProperty("service_id")
            Integer serviceId;
            @JsonProperty("deleted_at")
            String deletedAt;
            @JsonProperty("created_at")
            String createdAt;
            @JsonProperty("updated_at")
            String updatedAt;
            @JsonProperty("requisites_filled")
            Boolean requisitesFilled;
            @JsonProperty("bank_details_filled")
            Boolean bankDetailsFilled;
            @JsonProperty("rate_filled")
            Boolean rateFilled;
            @JsonProperty("price_list_filled")
            Boolean priceListFilled;
            @JsonProperty("distribution_company_agreement_filled")
            Boolean distributionCompanyAgreementFilled;
            @JsonProperty("equipments_filled")
            Boolean equipmentsFilled;
            @JsonProperty("contacts_filled")
            Boolean contactsFilled;
            @JsonProperty("bank_id")
            Integer bankId;
            @JsonProperty("account_number")
            Integer accountNumber;
            @JsonProperty("distribution_company_id")
            Integer distributionCompanyId;
            @JsonProperty("country_id")
            Integer countryId;
            @JsonProperty("post_address_id")
            Integer postAddressId;
            @JsonProperty("on_verification")
            Boolean onVerification;
            @JsonProperty("verified_at")
            String verifiedAt;
            @JsonProperty("verification_hash")
            String verificationHash;
            @JsonProperty("first_accept")
            Integer firstAccept;
            @JsonProperty("company_id")
            Integer companyId;
            @JsonProperty("employment_status")
            String employmentStatus;
            @JsonProperty("dispatcher_company_id")
            Integer dispatcherCompanyId;
            String position;
            String rating;
            @JsonProperty("accept_terms")
            Boolean acceptTerms;
            @JsonProperty("new_messages_count")
            Integer newMessagesCount;
            String tz;
            @JsonProperty("push_notifications_enable")
            Boolean pushNotificationsEnable;
            @JsonProperty("email_notifications_enable")
            Boolean emailNotificationsEnable;
            @JsonProperty("sms_notifications_enable")
            Boolean smsNotificationsEnable;
            @JsonProperty("second_accept")
            Integer secondAccept;
            @JsonProperty("can_consulting")
            Boolean canConsulting;
            @JsonProperty("consultation_price")
            Integer consultationPrice;
            @JsonProperty("push_id")
            Integer pushId;
            @JsonProperty("ready_for_consultation")
            Boolean readyForConsultation;
            @JsonProperty("active_role")
            String activeRole;
            @JsonProperty("is_have_contract")
            Boolean isHaveContract;
            @JsonProperty("is_ip")
            Boolean isIp;
            @JsonProperty("diploma_id")
            Integer diplomaId;
            String inn;
            @JsonProperty("living_address_id")
            Integer livingAddressId;
            @JsonProperty("work_distance")
            Integer workDistance;
            @JsonProperty("joined_at")
            String joinedAt;
            @JsonProperty("registered_at")
            String registeredAt;
            @JsonProperty("sc_notified_about_my_reg")
            Boolean scNotifiedAboutMyReg;
            @JsonProperty("sc_notified_about_my_certificates")
            Boolean scNotifiedAboutMyCertificates;
            Double debt;
            @JsonProperty("count_review")
            Integer countReview;
            @JsonProperty("count_completed_orders")
            Integer countCompletedOrders;
            @JsonProperty("count_not_read_notification")
            Integer countNotReadNotification;
            @JsonProperty("fps_phone")
            String fpsPhone;
            @JsonProperty("fps_bank_id")
            Integer fpsBankId;
            @JsonProperty("selected_payment_method")
            String selectedPaymentMethod;
            @JsonProperty("referer_id")
            Integer refererId;
            @JsonProperty("referer_code")
            String refererCode;
            Double balance;
            @JsonProperty("default_referral_commission")
            Integer defaultReferralCommission;
            @JsonProperty("additional_phone")
            String additionalPhone;
            ArrayList<OrdersIdResponseDto.Data.Roles> roles;
            @JsonProperty("company")
            Object company;
            @JsonProperty("service")
            Object service;
            @JsonProperty("full_name")
            String fullName;
            private Integer id;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("middle_name")
            private String middleName;
            private String phone;
            private String login;
            private String facetime;
            private String whatsapp;
            private String skype;
            private String telegram;
            private String email;
            @JsonProperty("email_verified_at")
            private String emailVerifiedAt;
            @JsonProperty("phone_verified_at")
            private String phoneVerifiedAt;
        }
    }
}




