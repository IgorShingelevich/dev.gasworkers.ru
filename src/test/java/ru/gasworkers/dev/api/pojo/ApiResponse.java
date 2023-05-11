package ru.gasworkers.dev.api.pojo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApiResponse {

    private UserApi data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserApi {

        private Integer id;
        private String firstName;
        private String lastName;
        private String middleName;

        @JsonProperty("full_name")
        private String fullName;

        private String email;
        private String login;
        private Long emailVerifiedAt;
        private Long joinedAt;
        private Long registeredAt;
        private Long phone;
        private Contacts contacts;
        private String type;
        private Passport passport;
        private String avatar;
        private String diploma;
        private String selfEmployedCertificate;
        private String selfEmployedCertificateExpired;
        private Boolean isFullProfile;
        private Boolean isHalfFullProfile;
        private String specialization;
        private String skills;
        private Integer countObjects;
        private Integer countOrders;
        private Boolean requisitesFilled;
        private Boolean bankDetailsFilled;
        private Boolean rateFilled;
        private Boolean priceListFilled;
        private Boolean distributionCompanyAgreementFilled;
        private Boolean equipmentsFilled;
        private Boolean contactsFilled;
        private Boolean profileCompleted;
        private Long createdAt;
        private String company;
        private String service;
        private String companyAsDispatcher;
        private Integer serviceId;
        private Integer distributionCompanyId;
        private Boolean onVerification;
        private Long verifiedAt;
        private String bank;
        private String accountNumber;
        private Integer firstAccept;
        private Integer secondAccept;
        private String country;
        private String postAddress;
        private String employmentStatus;
        private List<String> brands;
        private List<String> certificates;
        private Long insuranceStartedAt;
        private Double rating;
        private Integer reviewsCount;
        private Integer completedOrdersCount;
        private Boolean acceptTerms;
        private Integer newMessagesCount;
        private Integer userNotificationsCount;
        private Boolean pushNotificationsEnable;
        private Boolean emailNotificationsEnable;
        private Boolean smsNotificationsEnable;
        private Boolean readyForConsultation;
        private Boolean canConsulting;
        private Boolean isHaveContract;
        private Boolean isIp;
        private String inn;
        private String activeRole;
        private Integer consultationPrice;
        private Integer livingAddressId;
        private String livingAddress;
        private Integer workDistance;
        private List<Guide> guides;
        private Review lastReview;
    }

    @Data
    public static class Contacts {
        private String facetime;
        private String whatsapp;
        private String skype;
        private String telegram;
    }

    @Data
    public static class Passport {
        private Integer id;
        private String series;
        private String number;
        private Long issuedDate;
        private String issuedBy;
        private String registrationAddress;
        private Long createdAt;
    }

    @Data
    public static class Guide {
        private Integer id;
        private String type;
        private Boolean needStart;
        private Boolean finished;
        private Integer screen;
    }

    @Data
    public static class Review {
        private Integer id;
        private String text;
        private ReviewCreator creator;
        private Integer value;
        private Long createdAt;
        private Order order;

        @Data
        public static class ReviewCreator {
            private String fullName;
            private Double rating;
            private Integer countReviews;
        }

        @Data
        public static class Order {
            private Integer id;
            private String type;
            private List<Equipment> equipments;
        }

        @Data
        public static class Equipment {
            private Integer id;
            private String title;
        }
    }
}

