package ru.gasworkers.dev.api.orders.id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        public Integer id;
        public String number;
        public Master master;
        public Client client;
        public ServiceCenter serviceCenter;
        @JsonProperty("contract_type")
        public Object contractType;
        public ClientObject clientObject;
        public ArrayList<Equipment> equipments;
        public String address;
        public Long phone;
        public String status;
        @JsonProperty("payment_status")
        public Boolean paymentStatus;
        public String type;
        @JsonProperty("desired_date_interval_started_at")
        public String desiredDateIntervalStartedAt;
        @JsonProperty("desired_date_interval_ended_at")
        public String desiredDateIntervalEndedAt;
        @JsonProperty("desired_time_started")
        public String desiredTimeStarted;
        @JsonProperty("desired_time_ended")
        public String desiredTimeEnded;
        @JsonProperty("selected_date")
        public Object selectedDate;
        public Object description;
        public ArrayList<Object> photos;
        public ArrayList<Object> videos;
        @JsonProperty("created_at")
        public String createdAt;
        public ArrayList<Masters> masters;
        @JsonProperty("master_recommendation")
        public Object masterRecommendation;
        @JsonProperty("all_works")
        public Boolean allWorks;
        @JsonProperty("master_left_review")
        public Boolean masterLeftReview;
        @JsonProperty("client_left_review")
        public Boolean clientLeftReview;
        @JsonProperty("insurance_contract")
        public Object insuranceContract;
        @JsonProperty("insurance_policy")
        public Object insurancePolicy;
        @JsonProperty("maintenance_act")
        public Object maintenanceAct;
        @JsonProperty("creation_status")
        public Object creationStatus;
        @JsonProperty("offers_count")
        public Object offersCount;
        public Offer offer;
        public ArrayList<Receipt> receipts;
        @JsonProperty("repair_stage")
        public Object repairStage;
        @JsonProperty("need_sign")
        public Boolean needSign;
        @JsonProperty("sign_type")
        public Object signType;
        @JsonProperty("need_master_sign")
        public Boolean needMasterSign;
        @JsonProperty("master_sign_type")
        public Object masterSignType;
        public DistributionCompany distributionCompany;
        @JsonProperty("check_list")
        public ArrayList<Object> checkList;
        public Dispatcher dispatcher;
        @JsonProperty("conference_url")
        public String conferenceUrl;
        public Boolean online;
        public ArrayList<Object> masterVideos;
        @JsonProperty("consultation_canceled")
        public Boolean consultationCanceled;
        @JsonProperty("not_want_to_sign")
        public Boolean notWantToSign;
        @JsonProperty("not_want_to_pay")
        public Boolean notWantToPay;
        @JsonProperty("not_want_to_sign_by_client")
        public Boolean notWantToSignByClient;
        @JsonProperty("active_dispatcher")
        public Boolean activeDispatcher;
        public ArrayList<Offers> offers;
        @JsonProperty("additional_status")
        public Object additionalStatus;
        public ArrayList<History> history;
        @JsonProperty("from_maintenance")
        public Boolean fromMaintenance;
        @JsonProperty("is_insurance_case")
        public Boolean isInsuranceCase;
        @JsonProperty("repair_order_id")
        public Integer repairOrderId;
        @JsonProperty("maintenance_order_id")
        public Integer maintenanceOrderId;
        @JsonProperty("possible_offer_id")
        public Integer possibleOfferId;
        @JsonProperty("material_values")
        public List<MaterialValues> materialValues;
        public List<Actions> actions;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MaterialValues {
            public String title;
            @JsonProperty("part_number")
            public String partNumber;
            public Integer qty;
            public Double price;
            public String manufacturer;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Actions {
            public String description;
            public Integer qty;
            public Double price;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Address {
            public Integer id;
            public String full;
            public String type;
            @JsonProperty("full_with_zip")
            public String fullWithZip;
            public Object zip;
            public String country;
            @JsonProperty("country_code")
            public String countryCode;
            public String region;
            @JsonProperty("region_type")
            public String regionType;
            @JsonProperty("region_with_type")
            public Object regionWithType;
            public String area;
            @JsonProperty("area_type")
            public Object areaType;
            @JsonProperty("area_with_type")
            public Object areaWithType;
            public String city;
            @JsonProperty("city_type")
            public Object cityType;
            @JsonProperty("city_with_type")
            public Object cityWithType;
            public Object settlement;
            @JsonProperty("settlement_type")
            public Object settlementType;
            @JsonProperty("settlement_with_type")
            public Object settlementWithType;
            public Object street;
            @JsonProperty("street_type")
            public Object streetType;
            @JsonProperty("street_with_type")
            public Object streetWithType;
            public Object house;
            @JsonProperty("house_type")
            public Object houseType;
            @JsonProperty("house_with_type")
            public Object houseWithType;
            public Object block;
            public Object flat;
            @JsonProperty("capital_marker")
            public Object capitalMarker;
            public Double longitude;
            public Double latitude;
        }


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Branch {
            public Integer id;
            public String title;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Brand {
            public Integer id;
            public String title;
            @JsonProperty("is_foreign")
            public Boolean isForeign;
            @JsonProperty("is_custom")
            public Boolean isCustom;
            @JsonProperty("is_show_hidden")
            public Boolean isShowHidden;
            public String logo;
            @JsonProperty("created_at")
            public String createdAt;
            public ArrayList<Types> types;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Client {
            public Integer id;
            @JsonProperty("first_name")
            public String firstName;
            @JsonProperty("last_name")
            public String lastName;
            @JsonProperty("middle_name")
            public String middleName;
            public Long phone;
            public Object login;
            public String facetime;
            public String whatsapp;
            public Object skype;
            public Object telegram;
            public String email;
            @JsonProperty("email_verified_at")
            public String emailVerifiedAt;
            @JsonProperty("phone_verified_at")
            public String phoneVerifiedAt;
            public String gender;
            public Object specialization;
            public Object skills;
            @JsonProperty("avatar_id")
            public Integer avatarId;
            @JsonProperty("service_id")
            public Object serviceId;
            @JsonProperty("deleted_at")
            public String deletedAt;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("updated_at")
            public String updatedAt;
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
            @JsonProperty("bank_id")
            public Object bankId;
            @JsonProperty("account_number")
            public Object accountNumber;
            @JsonProperty("distribution_company_id")
            public Object distributionCompanyId;
            @JsonProperty("country_id")
            public Object countryId;
            @JsonProperty("post_address_id")
            public Object postAddressId;
            @JsonProperty("on_verification")
            public Boolean onVerification;
            @JsonProperty("verified_at")
            public String verifiedAt;
            @JsonProperty("verification_hash")
            public Object verificationHash;
            @JsonProperty("first_accept")
            public Integer firstAccept;
            @JsonProperty("company_id")
            public Integer companyId;
            @JsonProperty("employment_status")
            public String employmentStatus;
            @JsonProperty("dispatcher_company_id")
            public Integer dispatcherCompanyId;
            public String position;
            public String rating;
            @JsonProperty("accept_terms")
            public Boolean acceptTerms;
            @JsonProperty("new_messages_count")
            public Integer newMessagesCount;
            public String tz;
            @JsonProperty("push_notifications_enable")
            public Boolean pushNotificationsEnable;
            @JsonProperty("email_notifications_enable")
            public Boolean emailNotificationsEnable;
            @JsonProperty("sms_notifications_enable")
            public Boolean smsNotificationsEnable;
            @JsonProperty("second_accept")
            public Object secondAccept;
            @JsonProperty("can_consulting")
            public Boolean canConsulting;
            @JsonProperty("consultation_price")
            public Integer consultationPrice;
            @JsonProperty("push_id")
            public String pushId;
            @JsonProperty("ready_for_consultation")
            public Boolean readyForConsultation;
            @JsonProperty("active_role")
            public Object activeRole;
            @JsonProperty("is_have_contract")
            public Boolean isHaveContract;
            @JsonProperty("is_ip")
            public Boolean isIp;
            @JsonProperty("diploma_id")
            public Object diplomaId;
            public Object inn;
            @JsonProperty("living_address_id")
            public Object livingAddressId;
            @JsonProperty("work_distance")
            public Object workDistance;
            @JsonProperty("joined_at")
            public String joinedAt;
            @JsonProperty("registered_at")
            public String registeredAt;
            @JsonProperty("sc_notified_about_my_reg")
            public Boolean scNotifiedAboutMyReg;
            @JsonProperty("sc_notified_about_my_certificates")
            public Boolean scNotifiedAboutMyCertificates;
            public Integer debt;
            @JsonProperty("count_review")
            public Integer countReview;
            @JsonProperty("count_completed_orders")
            public Integer countCompletedOrders;
            @JsonProperty("count_not_read_notification")
            public Integer countNotReadNotification;
            @JsonProperty("fps_phone")
            public String fpsPhone;
            @JsonProperty("fps_bank_id")
            public Object fpsBankId;
            @JsonProperty("selected_payment_method")
            public Object selectedPaymentMethod;
            @JsonProperty("referer_id")
            public Object refererId;
            @JsonProperty("referer_code")
            public String refererCode;
            public Integer balance;
            @JsonProperty("default_referral_commission")
            public Double defaultReferralCommission;
            @JsonProperty("additional_phone")
            public Long additionalPhone;
            public ArrayList<Roles> roles;
            public Object company;
            public Object service;
            @JsonProperty("full_name")
            public String fullName;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ClientObject {
            public Integer id;
            public String title;
            @JsonProperty("active_offers_count")
            public Integer activeOffersCount;
            public ArrayList<Object> photos;
            public Address address;
            public ArrayList<Equipment> equipments;
            public Branch branch;
            public Company company;
            @JsonProperty("account_number")
            public String accountNumber;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("video_exists")
            public Boolean videoExists;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Company {
            public Integer id;
            public String title;
            @JsonProperty("short_title")
            public String shortTitle;
            public String inn;
            public String kpp;
            public String ogrn;
            public String okved;
            public String address;
            @JsonProperty("register_date")
            public String registerDate;
            @JsonProperty("is_ip")
            public Boolean isIp;
            public String site;
            public String logo;
            public String card;
            public String description;
            public Boolean specialized;
            @JsonProperty("ministry_of_energy_agreement_exist")
            public Boolean ministryOfEnergyAgreementExist;
            public String rating;
            @JsonProperty("gen_dir")
            public String genDir;
            @JsonProperty("gen_dir_np")
            public String genDirNp;
            @JsonProperty("vdgo_contract_charter")
            public String vdgoContractCharter;
            @JsonProperty("is_self_employed_owner")
            public Boolean isSelfEmployedOwner;
            @JsonProperty("first_accept")
            public Double firstAccept;
            public ArrayList<Branch> branches;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Dispatcher {
            public Long phone;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DistributionCompany {
            public Integer id;
            public String title;
            public String url;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Equipment {
            public Integer id;
            @JsonProperty("all_works")
            public Boolean allWorks;
            public Types type;
            public Brand brand;
            public Object title;
            @JsonProperty("computed_title")
            public String computedTitle;
            public Integer power;
            public ArrayList<Object> photos;
            @JsonProperty("last_photo")
            public Object lastPhoto;
            public ArrayList<Object> videos;
            public Model model;
            @JsonProperty("video_exists")
            public Boolean videoExists;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class FileCard {
            public Integer id;
            @JsonProperty("original_name")
            public String originalName;
            public Object title;
            public String path;
            public String extension;
            @JsonProperty("uploader_id")
            public Integer uploaderId;
            @JsonProperty("deleted_at")
            public String deletedAt;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("updated_at")
            public String updatedAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class FileLogo {
            public Integer id;
            @JsonProperty("original_name")
            public String originalName;
            public Object title;
            public String path;
            public String extension;
            @JsonProperty("uploader_id")
            public Integer uploaderId;
            @JsonProperty("deleted_at")
            public String deletedAt;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("updated_at")
            public String updatedAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class History {
            public Integer id;
            public String action;
            @JsonProperty("src_title")
            public String srcTitle;
            public String title;
            public String description;
            @JsonProperty("set_at")
            public String setAt;
            @JsonProperty("completed_at")
            public String completedAt;
            public Boolean completed;
            @JsonProperty("is_current")
            public Boolean isCurrent;
            @JsonProperty("sort_index")
            public Integer sortIndex;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Master {
            public Integer id;
            @JsonProperty("active_role")
            public Object activeRole;
            @JsonProperty("full_name")
            public String fullName;
            @JsonProperty("last_name")
            public String lastName;
            public String rating;
            @JsonProperty("consultation_price")
            public Double consultationPrice;
            public String skills;
            public String avatar;
            public Company company;
            @JsonProperty("employment_status")
            public String employmentStatus;
            public ArrayList<Brand> brands;
            @JsonProperty("reviews_count")
            public Integer reviewsCount;
            @JsonProperty("completed_orders_count")
            public Integer completedOrdersCount;
            @JsonProperty("created_at")
            public String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Masters {
            public Integer id;
            @JsonProperty("active_role")
            public Object activeRole;
            @JsonProperty("full_name")
            public String fullName;
            @JsonProperty("last_name")
            public String lastName;
            public String rating;
            @JsonProperty("consultation_price")
            public Double consultationPrice;
            public String skills;
            public String avatar;
            public Company company;
            @JsonProperty("employment_status")
            public String employmentStatus;
            public ArrayList<Brand> brands;
            @JsonProperty("reviews_count")
            public Integer reviewsCount;
            @JsonProperty("completed_orders_count")
            public Integer completedOrdersCount;
            @JsonProperty("created_at")
            public String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Model {
            public Integer id;
            public String title;
            public String power;
            public Brand brand;
            public Types type;
            @JsonProperty("is_custom")
            public Boolean isCustom;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Offer {
            @JsonProperty("master_equipment")
            public ArrayList<Object> masterEquipment;
            @JsonProperty("start_price")
            public Object startPrice;
            public Double price;
            @JsonProperty("price_wi")
            public Double priceWi;
            @JsonProperty("price_real")
            public Double priceReal;
            @JsonProperty("with_consultation")
            public Boolean withConsultation;
            @JsonProperty("consultation_passed")
            public Boolean consultationPassed;
            @JsonProperty("client_closed")
            public Boolean clientClosed;
            @JsonProperty("wait_from")
            public Object waitFrom;
            @JsonProperty("wait_notification")
            public Integer waitNotification;
            @JsonProperty("master_selected")
            public Boolean masterSelected;
            @JsonProperty("master_started")
            public Boolean masterStarted;
            public String status;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Offers {
            public Integer id;
            public Double price;
            @JsonProperty("master_id")
            public Object masterId;
            @JsonProperty("company_id")
            public Integer companyId;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Pivot {
            @JsonProperty("model_id")
            public Integer modelId;
            @JsonProperty("role_id")
            public Integer roleId;
            @JsonProperty("model_type")
            public String modelType;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Range {
            public Integer id;
            @JsonProperty("from_value")
            public Integer fromValue;
            @JsonProperty("to_value")
            public Integer toValue;
            @JsonProperty("domestic_price")
            public Object domesticPrice;
            @JsonProperty("foreign_price")
            public Object foreignPrice;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Receipt {
            public Integer id;
            public Double amount;
            public Boolean paid;
            public String title;
            public String stage;
            @JsonProperty("remains_to_pay")
            public Double remainsToPay;
            @JsonProperty("created_at")
            public String createdAt;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Roles {
            public Integer id;
            public String name;
            @JsonProperty("guard_name")
            public String guardName;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("updated_at")
            public String updatedAt;
            @JsonProperty("is_admin")
            public Integer isAdmin;
            public Pivot pivot;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Service {
            public Integer id;
            public String title;
            @JsonProperty("short_title")
            public String shortTitle;
            public String inn;
            public String kpp;
            public String ogrn;
            public String okved;
            public String address;
            public String site;
            @JsonProperty("is_ip")
            public Boolean isIp;
            @JsonProperty("register_date")
            public String registerDate;
            @JsonProperty("user_id")
            public Integer userId;
            @JsonProperty("created_at")
            public String createdAt;
            @JsonProperty("updated_at")
            public String updatedAt;
            public String description;
            public String logo;
            public Boolean specialized;
            public String card;
            @JsonProperty("ministry_of_energy_agreement_exist")
            public Boolean ministryOfEnergyAgreementExist;
            @JsonProperty("not_connected")
            public Boolean notConnected;
            @JsonProperty("vdgo_contract_charter")
            public String vdgoContractCharter;
            @JsonProperty("gen_dir")
            public String genDir;
            @JsonProperty("gen_dir_np")
            public String genDirNp;
            public Double lat;
            public Double lng;
            @JsonProperty("logo_id")
            public Integer logoId;
            @JsonProperty("card_id")
            public Integer cardId;
            @JsonProperty("is_self_employed_owner")
            public Boolean isSelfEmployedOwner;
            @JsonProperty("for_fake")
            public Boolean forFake;
            @JsonProperty("moneta_unit_id")
            public Integer monetaUnitId;
            @JsonProperty("moneta_account_id")
            public Integer monetaAccountId;
            @JsonProperty("moneta_account_payment_password")
            public Object monetaAccountPaymentPassword;
            @JsonProperty("moneta_bank_account_id")
            public Integer monetaBankAccountId;
            @JsonProperty("for_banner_price")
            public Boolean forBannerPrice;
            @JsonProperty("is_test_state")
            public Boolean isTestState;
            @JsonProperty("file_logo")
            public FileLogo fileLogo;
            @JsonProperty("file_card")
            public FileCard fileCard;
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ServiceCenter {
            public Integer id;
            public String title;
            @JsonProperty("first_accept")
            public Double firstAccept;
            public ArrayList<ServiceCenterMasters> masters;

            @lombok.Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class ServiceCenterMasters {
                public Integer id;
                @JsonProperty("first_name")
                public String firstName;
                @JsonProperty("last_name")
                public String lastName;
                @JsonProperty("middle_name")
                public String middleName;
                public Object phone;
                public String login;
                public Object facetime;
                public Object whatsapp;
                public Object skype;
                public Object telegram;
                public String email;
                @JsonProperty("email_verified_at")
                public String emailVerifiedAt;
                @JsonProperty("phone_verified_at")
                public String phoneVerifiedAt;
                public String gender;
                public String specialization;
                public String skills;
                @JsonProperty("avatar_id")
                public Integer avatarId;
                @JsonProperty("service_id")
                public Integer serviceId;
                @JsonProperty("deleted_at")
                public String deletedAt;
                @JsonProperty("created_at")
                public String createdAt;
                @JsonProperty("updated_at")
                public String updatedAt;
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
                @JsonProperty("bank_id")
                public Integer bank_id;
                @JsonProperty("account_number")
                public Object accountNumber;
                @JsonProperty("distribution_company_id")
                public Integer distributionCompanyId;
                @JsonProperty("country_id")
                public Integer countryId;
                @JsonProperty("post_address_id")
                public Integer postAddressId;
                @JsonProperty("on_verification")
                public Boolean onVerification;
                @JsonProperty("verified_at")
                public String verifiedAt;
                @JsonProperty("verification_hash")
                public String verificationHash;
                @JsonProperty("first_accept")
                public Integer firstAccept;
                @JsonProperty("company_id")
                public Integer companyId;
                @JsonProperty("employment_status")
                public String employmentStatus;
                @JsonProperty("dispatcher_company_id")
                public Integer dispatcherCompanyId;
                public Object position;
                public String rating;
                @JsonProperty("accept_terms")
                public Boolean acceptTerms;
                @JsonProperty("new_messages_count")
                public Integer newMessagesCount;
                public String tz;
                @JsonProperty("push_notifications_enable")
                public Boolean pushNotificationsEnable;
                @JsonProperty("email_notifications_enable")
                public Boolean emailNotificationsEnable;
                @JsonProperty("sms_notifications_enable")
                public Boolean smsNotificationsEnable;
                @JsonProperty("second_accept")
                public Object secondAccept;
                @JsonProperty("can_consulting")
                public Boolean canConsulting;
                @JsonProperty("consultation_price")
                public Integer consultationPrice;
                @JsonProperty("push_id")
                public Integer pushId;
                @JsonProperty("ready_for_consultation")
                public Boolean readyForConsultation;
                @JsonProperty("active_role")
                public Object activeRole;
                @JsonProperty("is_have_contract")
                public Boolean isHaveContract;
                @JsonProperty("is_ip")
                public Boolean isIp;
                @JsonProperty("diploma_id")
                public Integer diplomaId;
                public Object inn;
                @JsonProperty("living_address_id")
                public Integer livingAddressId;
                @JsonProperty("work_distance")
                public Object workDistance;
                @JsonProperty("joined_at")
                public String joinedAt;
                @JsonProperty("registered_at")
                public String registeredAt;
                @JsonProperty("sc_notified_about_my_reg")
                public Boolean scNotifiedAboutMyReg;
                @JsonProperty("sc_notified_about_my_certificates")
                public Boolean scNotifiedAboutMyCertificates;
                public Integer debt;
                @JsonProperty("count_review")
                public Integer countReview;
                @JsonProperty("count_completed_orders")
                public Integer countCompletedOrders;
                @JsonProperty("count_not_read_notification")
                public Integer countNotReadNotification;
                @JsonProperty("fps_phone")
                public String fpsPhone;
                @JsonProperty("fps_bank_id")
                public Integer fpsBankId;
                @JsonProperty("selected_payment_method")
                public String selectedPaymentMethod;
                @JsonProperty("referer_id")
                public Integer refererId;
                @JsonProperty("referer_code")
                public String refererCode;
                public Integer balance;
                @JsonProperty("default_referral_commission")
                public Double defaultReferralCommission;
                @JsonProperty("additional_phone")
                public Object additionalPhone;
                public ArrayList<Roles> roles;
                public Object company;
                public Service service;
                @JsonProperty("full_name")
                public String fullName;
                public String avatar;
                public ArrayList<Brand> brands;
                @JsonProperty("reviews_count")
                public Integer reviewsCount;
                @JsonProperty("completed_orders_count")
                public Integer completedOrdersCount;
            }
        }

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Types {
            public Integer id;
            @JsonProperty("sort_order")
            public Integer sortOrder;
            public String title;
            @JsonProperty("additional_parameter")
            public String additionalParameter;
            public Object note;
            @JsonProperty("power_required")
            public Boolean powerRequired;
            @JsonProperty("included_in_price_list")
            public Boolean includedInPriceList;
            @JsonProperty("is_show_hidden")
            public Boolean isShowHidden;
            public ArrayList<Range> ranges;
        }


    }


}
