package ru.gasworkers.dev.api.orders.info.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInfoResponseDto {


    private Integer status;
    private String message;
    private Data data;

    public static OrdersInfoResponseDto publishedOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrdersInfoResponseDto expectedResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBGRepairOrdersInfoRandomClient.json"), OrdersInfoResponseDto.class);

        expectedResponse.getData().setId(orderId);
        expectedResponse.getData().setNumber(orderNumber);
        expectedResponse.getData().setClientObject(ClientObject.builder().id(clientObjectId).build());
        expectedResponse.getData().setEquipments(new ArrayList<Data.Equipment>() {{
            add(Data.Equipment.builder().id(equipmentsId).build());
        }});

        return expectedResponse;
    }

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private Integer id;
        private String full;
        private ArrayList<Double> coordinates;
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
    }

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String address;
        private Integer id;
        private String number;
        private String status;
        private String type;
        @JsonProperty("service_center")
        private ServiceCenter serviceCenter;
        private Object master;
        private Object receipt;
        private ArrayList<Receipt> receipts;
        private ArrayList<Object> codes;
        private String stage;
        private Object offer;
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
        private ArrayList<Equipment> equipments;
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
        public static class Receipt {
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
        public static class Equipment {
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
    }
}




