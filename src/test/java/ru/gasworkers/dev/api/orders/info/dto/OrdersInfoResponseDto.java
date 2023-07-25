package ru.gasworkers.dev.api.orders.info.dto;

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
        private Object client;
        private Receipt receipt;
        private ArrayList<Receipts> receipts;
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
            private Boolean shoved;
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
    }
}




