package ru.gasworkers.dev.api.orders.suggestedServices.dto;

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
public class SuggestServicesResponseDto {
    private Integer status;
    private String message;
    private Data data;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        public ArrayList<Service> services;
        public ArrayList<Masters> masters;
    }

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Masters {
        private Integer id;
        private String title;
        private String logo;
        private Object price;
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
        @JsonProperty("price_wi")
        private Object priceWi;
        @JsonProperty("start_price")
        private Object startPrice;
        @JsonProperty("chat_id")
        private Object chatId;
        @JsonProperty("unread_count")
        private Object unreadCount;
        private String status;
        private ArrayList<Double> coordinates;
        private String rating;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("middle_name")
        private String middleName;
        private String avatar;
        @JsonProperty("reviews_as_target_count")
        private Integer reviewsAsTargetCount;
        @JsonProperty("master_orders_count")
        private Integer masterOrdersCount;
        private ArrayList<Brand> brands;
        @JsonProperty("offer_id")
        private Integer offerId;
        private Boolean showed;
        private SuggestedMaster master;
        @JsonProperty("count_completed_orders")
        private Integer countCompletedOrders;
        @JsonProperty("possible_first_accept_price")
        private Double possibleFirstAcceptPrice;
        @JsonProperty("possible_full_repair_price")
        private Double possibleFullRepairPrice;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SuggestedMaster {
            private Integer id;
            @JsonProperty("full_name")
            private String fullName;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("middle_name")
            private String middleName;
            @JsonProperty("count_completed_orders")
            private Integer countCompletedOrders;
            private String avatar;
            private String rating;
            @JsonProperty("reviews_as_target_count")
            private Integer reviewsAsTargetCount;
            @JsonProperty("master_orders_count")
            private Integer masterOrdersCount;
            private ArrayList<Brand> brands;
        }


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Brand {
            private Integer id;
            private String title;
            private String logo;
        }
    }

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Service {
        private Integer id;
        private String title;
        private String logo;
        private Double price;
        @JsonProperty("price_wi")
        private Double priceWi;
        @JsonProperty("price_real")
        private Double priceReal;
        private ArrayList<Double> coordinates;
        private Boolean connected;
        private String status;
        private String rating;
        @JsonProperty("first_accept")
        private Double firstAccept;
        private MasterSC master;
        private Integer index;
        @JsonProperty("offer_id")
        private Integer offerId;
        private Boolean showed;
        @JsonProperty("possible_first_accept_price")
        private Double possibleFirstAcceptPrice;
        @JsonProperty("possible_full_repair_price")
        private Double possibleFullRepairPrice;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MasterSC {
            private Integer id;
            @JsonProperty("full_name")
            private String fullName;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("middle_name")
            private String middleName;
            private String avatar;
            private String rating;
            @JsonProperty("reviews_as_target_count")
            private Integer reviewsAsTargetCount;
            @JsonProperty("master_orders_count")
            private Object masterOrdersCount;
            private ArrayList<Masters.Brand> brands;
            @JsonProperty("count_completed_orders")
            private Integer countCompletedOrders;
        }
    }
}
