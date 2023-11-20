package ru.gasworkers.dev.api.orders.id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersIdResponseDto {

    public Integer status;
    public String message;
    public Data data;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private Integer id;
        private String number;
        private Master master;
        private Client client;
        private ServiceCenter serviceCenter;
        @JsonProperty("contract_type")
        private Object contractType;
        private ClientObject clientObject;
        private ArrayList<Equipment> equipments;
        private String address;
        private Long phone;
        private String status;
        @JsonProperty("payment_status")
        private Boolean paymentStatus;
        private String type;
        @JsonProperty("desired_date_interval_started_at")
        private String desiredDateIntervalStartedAt;
        @JsonProperty("desired_date_interval_ended_at")
        private String desiredDateIntervalEndedAt;
        @JsonProperty("desired_time_started")
        private String desiredTimeStarted;
        @JsonProperty("desired_time_ended")
        private String desiredTimeEnded;
        @JsonProperty("selected_date")
        private String selectedDate;
        private String description;
        private ArrayList<Object> photos;
        private ArrayList<Object> videos;
        @JsonProperty("created_at")
        private String createdAt;
        private ArrayList<Masters> masters;
        @JsonProperty("master_recommendation")
        private String masterRecommendation;
        @JsonProperty("all_works")
        private Boolean allWorks;
        @JsonProperty("master_left_review")
        private Boolean masterLeftReview;
        @JsonProperty("client_left_review")
        private Boolean clientLeftReview;
        @JsonProperty("insurance_contract")
        private Object insuranceContract;
        @JsonProperty("insurance_policy")
        private Object insurancePolicy;
        @JsonProperty("maintenance_act")
        private Object maintenanceAct;
        @JsonProperty("creation_status")
        private Object creationStatus;
        @JsonProperty("offers_count")
        private Object offersCount;
        private Offer offer;
        private List<Receipts> receipts;
        @JsonProperty("repair_stage")
        private Object repairStage;
        @JsonProperty("need_sign")
        private Boolean needSign;
        @JsonProperty("sign_type")
        private Object signType;
        @JsonProperty("need_master_sign")
        private Boolean needMasterSign;
        @JsonProperty("master_sign_type")
        private Object masterSignType;
        private DistributionCompany distributionCompany;
        @JsonProperty("check_list")
        private ArrayList<Object> checkList;
        private Dispatcher dispatcher;
        @JsonProperty("conference_url")
        private String conferenceUrl;
        private Boolean online;
        private ArrayList<Object> masterVideos;
        @JsonProperty("consultation_canceled")
        private Boolean consultationCanceled;
        @JsonProperty("not_want_to_sign")
        private Boolean notWantToSign;
        @JsonProperty("not_want_to_pay")
        private Boolean notWantToPay;
        @JsonProperty("not_want_to_sign_by_client")
        private Boolean notWantToSignByClient;
        @JsonProperty("active_dispatcher")
        private Boolean activeDispatcher;
        private ArrayList<Offers> offers;
        @JsonProperty("additional_status")
        private Object additionalStatus;
        private ArrayList<History> history;
        @JsonProperty("from_maintenance")
        private Boolean fromMaintenance;
        @JsonProperty("is_insurance_case")
        private Boolean isInsuranceCase;
        @JsonProperty("repair_order_id")
        private Integer repairOrderId;
        @JsonProperty("maintenance_order_id")
        private Integer maintenanceOrderId;
        @JsonProperty("possible_offer_id")
        private Integer possibleOfferId;
        @JsonProperty("possible_offer")
        private Object possibleOffer;
        @JsonProperty("material_values")
        private List<MaterialValues> materialValues;
        private List<Actions> actions;
        private Boolean transfered;
        private Boolean tenderable;
        @JsonProperty("need_master_selecting")
        private Boolean needMasterSelecting;
        @JsonProperty("current_history_status")
        private String currentHistoryStatus;
        @JsonProperty("related_consultation_id")
        private Integer relatedConsultationId;
        @JsonProperty("related_repair_id")
        private Integer relatedRepairId;
        @JsonProperty("full_transfer")
        private Boolean fullTransfer;
        @JsonProperty("offer_dispatcher")
        private Object offerDispatcher;
        @JsonProperty("offer_master")
        private Object offerMaster;
        @JsonProperty("from_mobile")
        private Boolean fromMobile;
        @JsonProperty("with_activation")
        private Boolean withActivation;



        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MaterialValues {
            private String title;
            @JsonProperty("part_number")
            private String partNumber;
            private Integer qty;
            private Double price;
            private String manufacturer;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Actions {
            private String description;
            private Integer qty;
            private Double price;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Address {
            private Integer id;
            private String full;
            private String type;
            @JsonProperty("full_with_zip")
            private String fullWithZip;
            private Object zip;
            private String country;
            @JsonProperty("country_code")
            private String countryCode;
            private String region;
            @JsonProperty("region_type")
            private String regionType;
            @JsonProperty("region_with_type")
            private Object regionWithType;
            private String area;
            @JsonProperty("area_type")
            private Object areaType;
            @JsonProperty("area_with_type")
            private Object areaWithType;
            private String city;
            @JsonProperty("city_type")
            private Object cityType;
            @JsonProperty("city_with_type")
            private Object cityWithType;
            private Object settlement;
            @JsonProperty("settlement_type")
            private Object settlementType;
            @JsonProperty("settlement_with_type")
            private Object settlementWithType;
            private Object street;
            @JsonProperty("street_type")
            private Object streetType;
            @JsonProperty("street_with_type")
            private Object streetWithType;
            private Object house;
            @JsonProperty("house_type")
            private Object houseType;
            @JsonProperty("house_with_type")
            private Object houseWithType;
            private Object block;
            private Object flat;
            @JsonProperty("capital_marker")
            private Object capitalMarker;
            private Double longitude;
            private Double latitude;
        }


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Branch {
            private Integer id;
            private String title;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Brand {
            private Integer id;
            private String title;
            @JsonProperty("is_foreign")
            private Boolean isForeign;
            @JsonProperty("is_custom")
            private Boolean isCustom;
            @JsonProperty("is_show_hidden")
            private Boolean isShowHidden;
            private String logo;
            @JsonProperty("created_at")
            private String createdAt;
            private ArrayList<Types> types;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Client {
            private Integer id;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("middle_name")
            private String middleName;
            private Long phone;
            private Object login;
            private String facetime;
            private String whatsapp;
            private Object skype;
            private Object telegram;
            private String email;
            @JsonProperty("email_verified_at")
            private String emailVerifiedAt;
            @JsonProperty("phone_verified_at")
            private String phoneVerifiedAt;
            private String gender;
            private Object specialization;
            private Object skills;
            @JsonProperty("avatar_id")
            private Integer avatarId;
            @JsonProperty("service_id")
            private Object serviceId;
            @JsonProperty("deleted_at")
            private String deletedAt;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
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
            @JsonProperty("bank_id")
            private Object bankId;
            @JsonProperty("account_number")
            private Object accountNumber;
            @JsonProperty("distribution_company_id")
            private Object distributionCompanyId;
            @JsonProperty("country_id")
            private Object countryId;
            @JsonProperty("post_address_id")
            private Object postAddressId;
            @JsonProperty("on_verification")
            private Boolean onVerification;
            @JsonProperty("verified_at")
            private String verifiedAt;
            @JsonProperty("verification_hash")
            private Object verificationHash;
            @JsonProperty("first_accept")
            private Integer firstAccept;
            @JsonProperty("company_id")
            private Integer companyId;
            @JsonProperty("employment_status")
            private String employmentStatus;
            @JsonProperty("dispatcher_company_id")
            private Integer dispatcherCompanyId;
            private String position;
            private String rating;
            @JsonProperty("accept_terms")
            private Boolean acceptTerms;
            @JsonProperty("new_messages_count")
            private Integer newMessagesCount;
            private String tz;
            @JsonProperty("push_notifications_enable")
            private Boolean pushNotificationsEnable;
            @JsonProperty("email_notifications_enable")
            private Boolean emailNotificationsEnable;
            @JsonProperty("sms_notifications_enable")
            private Boolean smsNotificationsEnable;
            @JsonProperty("second_accept")
            private Object secondAccept;
            @JsonProperty("can_consulting")
            private Boolean canConsulting;
            @JsonProperty("consultation_price")
            private Integer consultationPrice;
            @JsonProperty("push_id")
            private String pushId;
            @JsonProperty("ready_for_consultation")
            private Boolean readyForConsultation;
            @JsonProperty("active_role")
            private Object activeRole;
            @JsonProperty("is_have_contract")
            private Boolean isHaveContract;
            @JsonProperty("is_ip")
            private Boolean isIp;
            @JsonProperty("diploma_id")
            private Object diplomaId;
            private Object inn;
            @JsonProperty("living_address_id")
            private Object livingAddressId;
            @JsonProperty("work_distance")
            private Object workDistance;
            @JsonProperty("joined_at")
            private String joinedAt;
            @JsonProperty("registered_at")
            private String registeredAt;
            @JsonProperty("sc_notified_about_my_reg")
            private Boolean scNotifiedAboutMyReg;
            @JsonProperty("sc_notified_about_my_certificates")
            private Boolean scNotifiedAboutMyCertificates;
            private Integer debt;
            @JsonProperty("count_review")
            private Integer countReview;
            @JsonProperty("count_completed_orders")
            private Integer countCompletedOrders;
            @JsonProperty("count_not_read_notification")
            private Integer countNotReadNotification;
            @JsonProperty("fps_phone")
            private String fpsPhone;
            @JsonProperty("fps_bank_id")
            private Object fpsBankId;
            @JsonProperty("selected_payment_method")
            private Object selectedPaymentMethod;
            @JsonProperty("referer_id")
            private Object refererId;
            @JsonProperty("referer_code")
            private String refererCode;
            private Integer balance;
            @JsonProperty("default_referral_commission")
            private Double defaultReferralCommission;
            @JsonProperty("additional_phone")
            private Long additionalPhone;
            private ArrayList<Roles> roles;
            private Object company;
            private Object service;
            @JsonProperty("full_name")
            private String fullName;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ClientObject {
            private Integer id;
            private String title;
            @JsonProperty("active_offers_count")
            private Integer activeOffersCount;
            private ArrayList<Object> photos;
            private Address address;
            private ArrayList<Equipment> equipments;
            private Branch branch;
            private Company company;
            @JsonProperty("account_number")
            private String accountNumber;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("video_exists")
            private Boolean videoExists;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Company {
            private Integer id;
            private String title;
            @JsonProperty("short_title")
            private String shortTitle;
            private String inn;
            private String kpp;
            private String ogrn;
            private String okved;
            private String address;
            @JsonProperty("register_date")
            private String registerDate;
            @JsonProperty("is_ip")
            private Boolean isIp;
            private String site;
            private String logo;
            private String card;
            private String description;
            private Boolean specialized;
            @JsonProperty("ministry_of_energy_agreement_exist")
            private Boolean ministryOfEnergyAgreementExist;
            private String rating;
            @JsonProperty("gen_dir")
            private String genDir;
            @JsonProperty("gen_dir_np")
            private String genDirNp;
            @JsonProperty("vdgo_contract_charter")
            private String vdgoContractCharter;
            @JsonProperty("is_self_employed_owner")
            private Boolean isSelfEmployedOwner;
            @JsonProperty("first_accept")
            private Double firstAccept;
            private ArrayList<Branch> branches;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Dispatcher {
            private Long phone;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DistributionCompany {
            private Integer id;
            private String title;
            private String url;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Equipment {
            private Integer id;
            @JsonProperty("all_works")
            private Boolean allWorks;
            private Types type;
            private Brand brand;
            private Object title;
            @JsonProperty("computed_title")
            private String computedTitle;
            private Integer power;
            private ArrayList<Object> photos;
            @JsonProperty("last_photo")
            private Object lastPhoto;
            private ArrayList<Object> videos;
            private Model model;
            @JsonProperty("video_exists")
            private Boolean videoExists;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class FileCard {
            private Integer id;
            @JsonProperty("original_name")
            private String originalName;
            private Object title;
            private String path;
            private String extension;
            @JsonProperty("uploader_id")
            private Integer uploaderId;
            @JsonProperty("deleted_at")
            private String deletedAt;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class FileLogo {
            private Integer id;
            @JsonProperty("original_name")
            private String originalName;
            private Object title;
            private String path;
            private String extension;
            @JsonProperty("uploader_id")
            private Integer uploaderId;
            @JsonProperty("deleted_at")
            private String deletedAt;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class History {
            private Integer id;
            private String action;
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
            @JsonProperty("sort_index")
            private Integer sortIndex;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Master {
            private Integer id;
            @JsonProperty("active_role")
            private Object activeRole;
            @JsonProperty("full_name")
            private String fullName;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            private String rating;
            @JsonProperty("consultation_price")
            private Double consultationPrice;
            private String skills;
            private String avatar;
            private Company company;
            @JsonProperty("employment_status")
            private String employmentStatus;
            private ArrayList<Brand> brands;
            @JsonProperty("reviews_count")
            private Integer reviewsCount;
            @JsonProperty("completed_orders_count")
            private Integer completedOrdersCount;
            @JsonProperty("created_at")
            private String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Masters {
            private Integer id;
            @JsonProperty("active_role")
            private Object activeRole;
            @JsonProperty("full_name")
            private String fullName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("first_name")
            private String firstName;
            private String rating;
            @JsonProperty("consultation_price")
            private Double consultationPrice;
            private String skills;
            private String avatar;
            private Company company;
            @JsonProperty("employment_status")
            private String employmentStatus;
            private ArrayList<Brand> brands;
            @JsonProperty("reviews_count")
            private Integer reviewsCount;
            @JsonProperty("completed_orders_count")
            private Integer completedOrdersCount;
            @JsonProperty("created_at")
            private String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Model {
            private Integer id;
            private String title;
            private String power;
            private Brand brand;
            private Types type;
            @JsonProperty("is_custom")
            private Boolean isCustom;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Offer {
            private Integer id;
            @JsonProperty("master_equipment")
            private ArrayList<Object> masterEquipment;
            @JsonProperty("start_price")
            private Object startPrice;
            private Double price;
            @JsonProperty("price_wi")
            private Double priceWi;
            @JsonProperty("price_real")
            private Double priceReal;
            @JsonProperty("with_consultation")
            private Boolean withConsultation;
            @JsonProperty("consultation_passed")
            private Boolean consultationPassed;
            @JsonProperty("client_closed")
            private Boolean clientClosed;
            @JsonProperty("wait_from")
            private Object waitFrom;
            @JsonProperty("wait_notification")
            private Integer waitNotification;
            @JsonProperty("master_selected")
            private Boolean masterSelected;
            @JsonProperty("master_started")
            private Boolean masterStarted;
            private String status;
            @JsonProperty("possible_full_repair_price")
            private Double possibleFullRepairPrice;
            @JsonProperty("possible_first_accept_price")
            private Double possibleFirstAcceptPrice;
            private SuggestServicesResponseDto.Masters.SuggestedMaster master;
            private Object company;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Offers {
            private Integer id;
            private Double price;
            @JsonProperty("master_id")
            private Object masterId;
            @JsonProperty("company_id")
            private Integer companyId;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Pivot {
            @JsonProperty("model_id")
            private Integer modelId;
            @JsonProperty("role_id")
            private Integer roleId;
            @JsonProperty("model_type")
            private String modelType;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Range {
            private Integer id;
            @JsonProperty("from_value")
            private Integer fromValue;
            @JsonProperty("to_value")
            private Integer toValue;
            @JsonProperty("domestic_price")
            private Object domesticPrice;
            @JsonProperty("foreign_price")
            private Object foreignPrice;
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
            private String stage;
            @JsonProperty("remains_to_pay")
            private Double remainsToPay;
            @JsonProperty("created_at")
            private String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Roles {
            private Integer id;
            private String name;
            @JsonProperty("guard_name")
            private String guardName;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
            @JsonProperty("is_admin")
            private Integer isAdmin;
            private Pivot pivot;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Service {
            private Integer id;
            private String title;
            @JsonProperty("short_title")
            private String shortTitle;
            private String inn;
            private String kpp;
            private String ogrn;
            private String okved;
            private String address;
            private String site;
            @JsonProperty("is_ip")
            private Boolean isIp;
            @JsonProperty("register_date")
            private String registerDate;
            @JsonProperty("user_id")
            private Integer userId;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
            private String description;
            private String logo;
            private Boolean specialized;
            private String card;
            @JsonProperty("ministry_of_energy_agreement_exist")
            private Boolean ministryOfEnergyAgreementExist;
            @JsonProperty("not_connected")
            private Boolean notConnected;
            @JsonProperty("vdgo_contract_charter")
            private String vdgoContractCharter;
            @JsonProperty("gen_dir")
            private String genDir;
            @JsonProperty("gen_dir_np")
            private String genDirNp;
            private Double lat;
            private Double lng;
            @JsonProperty("logo_id")
            private Integer logoId;
            @JsonProperty("card_id")
            private Integer cardId;
            @JsonProperty("is_self_employed_owner")
            private Boolean isSelfEmployedOwner;
            @JsonProperty("for_fake")
            private Boolean forFake;
            @JsonProperty("moneta_unit_id")
            private Integer monetaUnitId;
            @JsonProperty("moneta_account_id")
            private Integer monetaAccountId;
            @JsonProperty("moneta_account_payment_password")
            private Object monetaAccountPaymentPassword;
            @JsonProperty("moneta_bank_account_id")
            private Integer monetaBankAccountId;
            @JsonProperty("for_banner_price")
            private Boolean forBannerPrice;
            @JsonProperty("is_test_state")
            private Boolean isTestState;
            @JsonProperty("file_logo")
            private FileLogo fileLogo;
            @JsonProperty("file_card")
            private FileCard fileCard;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ServiceCenter {
            private Integer id;
            private String title;
            @JsonProperty("first_accept")
            private Double firstAccept;
            private ArrayList<ServiceCenterMasters> masters;

            @lombok.Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class ServiceCenterMasters {
                private Integer id;
                @JsonProperty("first_name")
                private String firstName;
                @JsonProperty("last_name")
                private String lastName;
                @JsonProperty("middle_name")
                private String middleName;
                private Object phone;
                private String login;
                private Object facetime;
                private Object whatsapp;
                private Object skype;
                private Object telegram;
                private String email;
                @JsonProperty("email_verified_at")
                private String emailVerifiedAt;
                @JsonProperty("phone_verified_at")
                private String phoneVerifiedAt;
                private String gender;
                private String specialization;
                private String skills;
                @JsonProperty("avatar_id")
                private Integer avatarId;
                @JsonProperty("service_id")
                private Integer serviceId;
                @JsonProperty("deleted_at")
                private String deletedAt;
                @JsonProperty("created_at")
                private String createdAt;
                @JsonProperty("updated_at")
                private String updatedAt;
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
                @JsonProperty("bank_id")
                private Integer bank_id;
                @JsonProperty("account_number")
                private Object accountNumber;
                @JsonProperty("distribution_company_id")
                private Integer distributionCompanyId;
                @JsonProperty("country_id")
                private Integer countryId;
                @JsonProperty("post_address_id")
                private Integer postAddressId;
                @JsonProperty("on_verification")
                private Boolean onVerification;
                @JsonProperty("verified_at")
                private String verifiedAt;
                @JsonProperty("verification_hash")
                private String verificationHash;
                @JsonProperty("first_accept")
                private Integer firstAccept;
                @JsonProperty("company_id")
                private Integer companyId;
                @JsonProperty("employment_status")
                private String employmentStatus;
                @JsonProperty("dispatcher_company_id")
                private Integer dispatcherCompanyId;
                private Object position;
                private String rating;
                @JsonProperty("accept_terms")
                private Boolean acceptTerms;
                @JsonProperty("new_messages_count")
                private Integer newMessagesCount;
                private String tz;
                @JsonProperty("push_notifications_enable")
                private Boolean pushNotificationsEnable;
                @JsonProperty("email_notifications_enable")
                private Boolean emailNotificationsEnable;
                @JsonProperty("sms_notifications_enable")
                private Boolean smsNotificationsEnable;
                @JsonProperty("second_accept")
                private Object secondAccept;
                @JsonProperty("can_consulting")
                private Boolean canConsulting;
                @JsonProperty("consultation_price")
                private Integer consultationPrice;
                @JsonProperty("push_id")
                private String pushId;
                @JsonProperty("ready_for_consultation")
                private Boolean readyForConsultation;
                @JsonProperty("active_role")
                private Object activeRole;
                @JsonProperty("is_have_contract")
                private Boolean isHaveContract;
                @JsonProperty("is_ip")
                private Boolean isIp;
                @JsonProperty("diploma_id")
                private Integer diplomaId;
                private Object inn;
                @JsonProperty("living_address_id")
                private Integer livingAddressId;
                @JsonProperty("work_distance")
                private Object workDistance;
                @JsonProperty("joined_at")
                private String joinedAt;
                @JsonProperty("registered_at")
                private String registeredAt;
                @JsonProperty("sc_notified_about_my_reg")
                private Boolean scNotifiedAboutMyReg;
                @JsonProperty("sc_notified_about_my_certificates")
                private Boolean scNotifiedAboutMyCertificates;
                private Integer debt;
                @JsonProperty("count_review")
                private Integer countReview;
                @JsonProperty("count_completed_orders")
                private Integer countCompletedOrders;
                @JsonProperty("count_not_read_notification")
                private Integer countNotReadNotification;
                @JsonProperty("fps_phone")
                private String fpsPhone;
                @JsonProperty("fps_bank_id")
                private Integer fpsBankId;
                @JsonProperty("selected_payment_method")
                private String selectedPaymentMethod;
                @JsonProperty("referer_id")
                private Integer refererId;
                @JsonProperty("referer_code")
                private String refererCode;
                private Integer balance;
                @JsonProperty("default_referral_commission")
                private Double defaultReferralCommission;
                @JsonProperty("additional_phone")
                private Object additionalPhone;
                private ArrayList<Roles> roles;
                private Object company;
                private Service service;
                @JsonProperty("full_name")
                private String fullName;
                private String avatar;
                private ArrayList<Brand> brands;
                @JsonProperty("reviews_count")
                private Integer reviewsCount;
                @JsonProperty("completed_orders_count")
                private Integer completedOrdersCount;
            }
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Types {
            private Integer id;
            @JsonProperty("sort_order")
            private Integer sortOrder;
            private String title;
            @JsonProperty("additional_parameter")
            private String additionalParameter;
            private Object note;
            @JsonProperty("power_required")
            private Boolean powerRequired;
            @JsonProperty("included_in_price_list")
            private Boolean includedInPriceList;
            @JsonProperty("is_show_hidden")
            private Boolean isShowHidden;
            private ArrayList<Range> ranges;
        }


    }


}
