package ru.gasworkers.dev.api.auth.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    public Data data;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        public Integer id;
        public String firstName;
        public String lastName;
        public String middleName;
        @JsonProperty("full_name")
        public String fullName;
        public String email;
        public Object login;
        @JsonProperty("email_verified_at")
        public String emailVerifiedAt;
        @JsonProperty("joined_at")
        public String joinedAt;
        @JsonProperty("registered_at")
        public String registeredAt;
        public Long phone;
        public Contacts contacts;
        public String type;
        public Passport passport;
        public String avatar;
        public Object diploma;
        public Object selfEmployedCertificate;
        @JsonProperty("self_employed_certificate_expired")
        public String selfEmployedCertificateExpired;
        @JsonProperty("is_full_profile")
        public Boolean isFullProfile;
        @JsonProperty("is_half_full_profile")
        public Boolean isHalfFullProfile;
        public Object specialization;
        public String skills;
        @JsonProperty("count_objects")
        public Integer countObjects;
        @JsonProperty("count_orders")
        public Object countOrders;
        @JsonProperty("requisites_filled")
        public Boolean requisitesFilled;
        @JsonProperty("bank_details_filled")
        public Boolean bankDetailsFilled;
        @JsonProperty("rate_filled")
        public Boolean rateFilled;
        @JsonProperty("price_list_filled")
        public Boolean priceListFilled;
        @JsonProperty("distribution_company_agreement_filled")
        public Boolean distributionCompanyAgreementFilled;
        @JsonProperty("equipments_filled")
        public Boolean equipmentsFilled;
        @JsonProperty("contacts_filled")
        public Boolean contactsFilled;
        @JsonProperty("profile_completed")
        public Boolean profileCompleted;
        @JsonProperty("created_at")
        public String createdAt;
        public Object company;
        public Object service;
        public Object companyAsDispatcher;
        @JsonProperty("service_id")
        public Integer serviceId;
        @JsonProperty("distribution_company_id")
        public Object distributionCompanyId;
        @JsonProperty("on_verification")
        public Boolean onVerification;
        @JsonProperty("verified_at")
        public String verifiedAt;
        public Object bank;
        @JsonProperty("account_number")
        public Object accountNumber;
        @JsonProperty("first_accept")
        public Integer firstAccept;
        @JsonProperty("second_accept")
        public Object secondAccept;
        public Object country;
        @JsonProperty("post_address")
        public Object postAddress;
        @JsonProperty("employment_status")
        public Object employmentStatus;
        public ArrayList<Object> brands;
        public ArrayList<Object> certificates;
        @JsonProperty("insurance_started_at")
        public String insuranceStartedAt;
        public String rating;
        @JsonProperty("reviews_count")
        public Integer reviewsCount;
        @JsonProperty("completed_orders_count")
        public Integer completedOrdersCount;
        @JsonProperty("accept_terms")
        public Boolean acceptTerms;
        @JsonProperty("new_messages_count")
        public Integer newMessagesCount;
        @JsonProperty("user_notifications_count")
        public Integer userNotificationsCount;
        @JsonProperty("push_notifications_enable")
        public Boolean pushNotificationsEnable;
        @JsonProperty("email_notifications_enable")
        public Boolean emailNotificationsEnable;
        @JsonProperty("sms_notifications_enable")
        public Boolean smsNotificationsEnable;
        @JsonProperty("ready_for_consultation")
        public Boolean readyForConsultation;
        @JsonProperty("can_consulting")
        public Boolean canConsulting;
        @JsonProperty("is_have_contract")
        public Boolean isHaveContract;
        @JsonProperty("is_ip")
        public Boolean isIp;
        public Object inn;
        @JsonProperty("active_role")
        public Object activeRole;
        @JsonProperty("consultation_price")
        public Integer consultationPrice;
        @JsonProperty("living_address_id")
        public Object livingAddressId;
        @JsonProperty("living_address")
        public Object livingAddress;
        @JsonProperty("work_distance")
        public Object workDistance;
        public ArrayList<Guides> guides;
        @JsonProperty("last_review")
        public LastReview lastReview;
        @JsonProperty("fps_phone")
        public String fpsPhone;
        @JsonProperty("selected_payment_method")
        public Object selectedPaymentMethod;
        @JsonProperty("fps_bank")
        public Object fpsBank;
        @JsonProperty("additional_phone")
        public Object additionalPhone;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Contacts {
            public String facetime;
            public String whatsapp;
            public String skype;
            public String telegram;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class LastReview {
            public Integer id;
            public String text;
            public Creator creator;
            public Integer value;
            @JsonProperty("created_at")
            public String createdAt;
            public Order order;

            @lombok.Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Order {
                public Integer id;
                public String type;
                public ArrayList<Equipments> equipments;

                @lombok.Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                public static class Equipments {
                    public Integer id;
                    public String title;
                }
            }

            @lombok.Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Creator {
                @JsonProperty("full_name")
                public String fullName;
                public String rating;
                @JsonProperty("count_reviews")
                public Integer countReviews;
            }

        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Passport {
            public Integer id;
            public String series;
            public String number;
            @JsonProperty("issued_date")
            public Integer issuedDate;
            @JsonProperty("issued_by")
            public String issuedBy;
            @JsonProperty("registration_address")
            public String registrationAddress;
            @JsonProperty("created_at")
            public Integer createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Guides {
            public Integer id;
            public String type;
            @JsonProperty("need_start")
            public Boolean needStart;
            public Boolean finished;
            public Integer screen;
        }
    }
}
