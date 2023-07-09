package ru.gasworkers.dev.api.users.selfEmployed.complete.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Accessors(chain = true)
public class UserModelResponseDto {
    private Integer status;
    private String message;
    private UserModelDto data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserModelDto {

        @JsonProperty("id")
        private String userId;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("email_verified_at")
        private Integer emailVerifiedAt;
        @JsonProperty("joined_at")
        private Integer joinedAt;
        @JsonProperty("registered_at")
        private Integer registeredAt;
        @JsonProperty("type")
        private String userType;
        @JsonProperty("self_employed_certificate_expired")
        private String selfEmployedCertificateExpired;
        @JsonProperty("is_full_profile")
        private Boolean isFullProfile;
        @JsonProperty("is_half_full_profile")
        private Boolean isHalfFullProfile;
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
        private Integer createdAt;
        private Boolean companyAsDispatcher;
        @JsonProperty("service_id")
        private Integer serviceId;
        @JsonProperty("distribution_company_id")
        private Integer distributionCompanyId;
        @JsonProperty("on_verification")
        private Boolean onVerification;
        @JsonProperty("verified_at")
        private Integer verifiedAt;
        @JsonProperty("account_number")
        private String accountNumber;
        @JsonProperty("first_accept")
        private Integer firstAccept;
        @JsonProperty("second_accept")
        private Integer secondAccept;
        @JsonProperty("employment_status")
        private String employmentStatus;
        @JsonProperty("insurance_started_at")
        private Integer insuranceStartedAt;
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
        @JsonProperty("active_role")
        private String activeRole;
        @JsonProperty("consultation_price")
        private Integer consultationPrice;
        @JsonProperty("living_address_id")
        private Integer livingAddressId;
        @JsonProperty("work_distance")
        private Integer workDistance;
        @JsonProperty("fps_phone")
        private Integer fpsPhone;
        @JsonProperty("selected_payment_method")
        private String selectedPaymentMethod;
        @JsonProperty("additional_phone")
        private Integer additionalPhone;
        private ContactsDto contacts;
        private List<GuideDto> guides;
        private PassportDto passport;
        private DiplomaDto diploma;
        private SelfEmployedCertificateDto selfEmployedCertificate;
        private BankDto bank;
        private CountryDto country;
        @JsonProperty("post_address")
        private PostAddressDto postAddress;
        private List<CertifiedBrandsDto> brands;
        @JsonProperty("living_address")
        private LivingAddressDto livingAddress;
        @JsonProperty("last_review")
        private LastReviewDto lastReview;
        @JsonProperty("fps_bank")
        private FpsBankDto fpsBank;
        private List<CertificatesDto> certificates;
        private String firstName, lastName, middleName, email, login, avatar,
                specialization, skills, rating;
        private Integer phone, company, service, inn;

        @Data
        public static class CertificatesDto {
            //todo fill
        }

        @Data
        public static class FpsBankDto {
            @JsonProperty("id")
            private Integer fpsBankId;
            @JsonProperty("identifier")
            private String fpsBankIdentifier;
            @JsonProperty("title")
            private String fpsBankTitle;
            @JsonProperty("logo")
            private String fpsBankLogo;
            @JsonProperty("package")
            private String fpsBankPackage;
        }

        @Data
        public static class LastReviewDto {
            @JsonProperty("id")
            private Integer lastReviewId;
            @JsonProperty("text")
            private String lastReviewText;
            @JsonProperty("creator")
            private CreatorDto creator;
            @JsonProperty("value")
            private Integer lastReviewValue;
            @JsonProperty("created_at")
            private String lastReviewCreatedAt;
            @JsonProperty("order")
            private OrderDto order;

            @Data
            public static class CreatorDto {
                @JsonProperty("full_name")
                private String creatorFullName;
                @JsonProperty("rating")
                private String creatorRating;
                @JsonProperty("count_reviews")
                private Integer creatorCountReviews;
            }

            @Data
            public static class OrderDto {
                @JsonProperty("id")
                private Integer orderId;
                @JsonProperty("type")
                private String orderType;
                @JsonProperty("equipments")
                private List<OrderEquipmentsDto> orderEquipments;

                @Data
                public static class OrderEquipmentsDto {
                    @JsonProperty("id")
                    private Integer equipmentsId;
                    @JsonProperty("title")
                    private String equipmentsTitle;
                }
            }
        }

        @Data
        public static class LivingAddressDto {
            @JsonProperty("id")
            private Integer livingAddressId;
            @JsonProperty("full")
            private String livingAddressFull;
            @JsonProperty("type")
            private String livingAddressType;
            @JsonProperty("full_with_zip")
            private String livingAddressFullWithZip;
            @JsonProperty("zip")
            private String livingAddressZip;
            @JsonProperty("country")
            private String livingAddressCountry;
            @JsonProperty("country_code")
            private String livingAddressCountryCode;
            @JsonProperty("region")
            private String livingAddressRegion;
            @JsonProperty("region_type")
            private String livingAddressRegionType;
            @JsonProperty("region_with_type")
            private String livingAddressRegionWithType;
            @JsonProperty("area")
            private String livingAddressArea;
            @JsonProperty("area_type")
            private String livingAddressAreaType;
            @JsonProperty("area_with_type")
            private String livingAddressAreaWithType;
            @JsonProperty("city")
            private String livingAddressCity;
            @JsonProperty("city_type")
            private String livingAddressCityType;
            @JsonProperty("city_with_type")
            private String livingAddressCityWithType;
            @JsonProperty("settlement")
            private String livingAddressSettlement;
            @JsonProperty("settlement_type")
            private String livingAddressSettlementType;
            @JsonProperty("settlement_with_type")
            private String livingAddressSettlementWithType;
            @JsonProperty("street")
            private String livingAddressStreet;
            @JsonProperty("street_type")
            private String livingAddressStreetType;
            @JsonProperty("street_with_type")
            private String livingAddressStreetWithType;
            @JsonProperty("house")
            private String livingAddressHouse;
            @JsonProperty("house_type")
            private String livingAddressHouseType;
            @JsonProperty("house_with_type")
            private String livingAddressHouseWithType;
            @JsonProperty("block")
            private String livingAddressBlock;
            @JsonProperty("flat")
            private String livingAddressFlat;
            @JsonProperty("capital_marker")
            private String livingAddressCapitalMarker;
            @JsonProperty("longitude")
            private Double livingAddressLongitude;
            @JsonProperty("latitude")
            private Double livingAddressLatitude;
        }

        @Data
        public static class CertifiedBrandsDto {
            @JsonProperty("id")
            private Integer brandId;
            @JsonProperty("title")
            private String brandTitle;
            @JsonProperty("is_foreign")
            private Boolean brandIsForeign;
            @JsonProperty("is_custom")
            private Boolean brandIsCustom;
            @JsonProperty("is_show_hidden")
            private Boolean brandIsShowHidden;
            @JsonProperty("logo")
            private String brandLogo;
            @JsonProperty("created_at")
            private Integer brandCreatedAt;

        }

        @Data
        public static class PostAddressDto {
            @JsonProperty("id")
            private Integer postAddressId;
            @JsonProperty("full")
            private String postAddressFull;
            @JsonProperty("type")
            private String postAddressType;
            @JsonProperty("full_with_zip")
            private String postAddressFullWithZip;
            @JsonProperty("zip")
            private String postAddressZip;
            @JsonProperty("country")
            private String postAddressCountry;
            @JsonProperty("country_code")
            private String postAddressCountryCode;
            @JsonProperty("region")
            private String postAddressRegion;
            @JsonProperty("region_type")
            private String postAddressRegionType;
            @JsonProperty("region_with_type")
            private String postAddressRegionWithType;
            @JsonProperty("area")
            private String postAddressArea;
            @JsonProperty("area_type")
            private String postAddressAreaType;
            @JsonProperty("area_with_type")
            private String postAddressAreaWithType;
            @JsonProperty("city")
            private String postAddressCity;
            @JsonProperty("city_type")
            private String postAddressCityType;
            @JsonProperty("city_with_type")
            private String postAddressCityWithType;
            @JsonProperty("settlement")
            private String postAddressSettlement;
            @JsonProperty("settlement_type")
            private String postAddressSettlementType;
            @JsonProperty("settlement_with_type")
            private String postAddressSettlementWithType;
            @JsonProperty("street")
            private String postAddressStreet;
            @JsonProperty("street_type")
            private String postAddressStreetType;
            @JsonProperty("street_with_type")
            private String postAddressStreetWithType;
            @JsonProperty("house")
            private String postAddressHouse;
            @JsonProperty("house_type")
            private String postAddressHouseType;
            @JsonProperty("house_with_type")
            private String postAddressHouseWithType;
            @JsonProperty("block")
            private String postAddressBlock;
            @JsonProperty("flat")
            private String postAddressFlat;
            @JsonProperty("capital_marker")
            private String postAddressCapitalMarker;
            @JsonProperty("longitude")
            private Double postAddressLongitude;
            @JsonProperty("latitude")
            private Double postAddressLatitude;

        }

        @Data
        public static class CountryDto {
            @JsonProperty("id")
            private Integer countryId;
            @JsonProperty("title")
            private String countryTitle;

        }

        @Data
        public static class BankDto {
            @JsonProperty("id")
            private Integer bankId;
            @JsonProperty("title")
            private String bankTitle;
            @JsonProperty("inn")
            private String bankInn;
            @JsonProperty("kpp")
            private String bankKpp;
            @JsonProperty("bik")
            private String bankBik;
            @JsonProperty("correspondent_account")
            private String bankCorrespondentAccount;

        }

        @Data
        public static class SelfEmployedCertificateDto {
            @JsonProperty("id")
            private Integer selfEmployedCertificateId;
            @JsonProperty("original_name")
            private String selfEmployedCertificateOriginalName;
            @JsonProperty("title")
            private String selfEmployedCertificateTitle;
            @JsonProperty("path")
            private String selfEmployedCertificatePath;
            @JsonProperty("url")
            private String selfEmployedCertificateUrl;
            @JsonProperty("user_id")
            private Integer selfEmployedCertificateUserId;
            @JsonProperty("user")
            private String selfEmployedCertificateUser;
            @JsonProperty("extension")
            private String selfEmployedCertificateExtension;
            @JsonProperty("preview")
            private String selfEmployedCertificatePreview;
            @JsonProperty("pivot")
            private PivotDto selfEmployedCertificatePivot;

            @Data
            public static class PivotDto {
                @JsonProperty("user_id")
                private Integer selfEmployedCertificatePivotUserId;
                @JsonProperty("certificate_id")
                private Integer selfEmployedCertificatePivotCertificateId;
                @JsonProperty("expired_date")
                private String selfEmployedCertificatePivotExpiredDate;
            }

        }

        @Data
        public static class PassportDto {
            @JsonProperty("id")
            private Integer passportId;
            @JsonProperty("series")
            private String passportSeries;
            @JsonProperty("number")
            private String passportNumber;
            @JsonProperty("issued_date")
            private Integer passportIssuedDate;
            @JsonProperty("issued_by")
            private String passportIssuedBy;
            @JsonProperty("registration_address")
            private String passportRegistrationAddress;
            @JsonProperty("created_at")
            private Integer passportCreatedAt;

        }

        @Data
        public static class DiplomaDto {
            @JsonProperty("id")
            private Integer diplomaId;
            @JsonProperty("original_name")
            private String diplomaOriginalName;
            @JsonProperty("title")
            private String diplomaTitle;
            @JsonProperty("path")
            private String diplomaPath;
            @JsonProperty("url")
            private String diplomaUrl;
            @JsonProperty("user_id")
            private Integer diplomaUserId;
            @JsonProperty("user")
            private String diplomaUser;
            @JsonProperty("extension")
            private String diplomaExtension;
            @JsonProperty("preview")
            private String diplomaPreview;
            @JsonProperty("pivot")
            private String diplomaPivot;
        }


        @Data
        public static class ContactsDto {
            @JsonProperty("facetime")
            private String contactsFacetime;
            @JsonProperty("whatsapp")
            private String contactsWhatsapp;
            @JsonProperty("skype")
            private String contactsSkype;
            @JsonProperty("telegram")
            private String contactsTelegram;
        }

        @Data
        public static class GuideDto {
            @JsonProperty("id")
            private Integer guideId;
            @JsonProperty("type")
            private String guideType;
            @JsonProperty("need_start")
            private Boolean guideNeedStart;
            @JsonProperty("finished")
            private Boolean guideFinished;
            @JsonProperty("screen")
            private Integer guideScreen;
        }
    }
}
