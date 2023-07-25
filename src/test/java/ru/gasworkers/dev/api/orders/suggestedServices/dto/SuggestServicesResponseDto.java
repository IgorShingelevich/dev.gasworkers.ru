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
    public Integer status;
    public String message;
    public Data data;

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
        public Integer id;
        public String title;
        public String logo;
        public Object price;
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
        @JsonProperty("price_wi")
        public Object priceWi;
        @JsonProperty("start_price")
        public Object startPrice;
        @JsonProperty("chat_id")
        public Object chatId;
        @JsonProperty("unread_count")
        public Object unreadCount;
        public String status;
        public ArrayList<Double> coordinates;
        public String rating;
        @JsonProperty("full_name")
        public String fullName;
        public String avatar;
        @JsonProperty("reviews_as_target_count")
        public Integer reviewsAsTargetCount;
        @JsonProperty("master_orders_count")
        public Integer masterOrdersCount;
        public ArrayList<Brand> brands;
        @JsonProperty("offer_id")
        public Integer offerId;
        public Boolean shoved;


        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Brand {
            public Integer id;
            public String title;
            public String logo;
        }
    }

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Service {
        public Integer id;
        public String title;
        public String logo;
        public Double price;
        @JsonProperty("price_wi")
        public Double priceWi;
        @JsonProperty("price_real")
        public Double priceReal;
        public ArrayList<Double> coordinates;
        public Boolean connected;
        public String status;
        public String rating;
        @JsonProperty("first_accept")
        public Double firstAccept;
        public MasterSC master;
        public Integer index;
        @JsonProperty("offer_id")
        public Integer offerId;
        public Boolean shoved;

        @lombok.Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MasterSC {
            public Integer id;
            @JsonProperty("full_name")
            public String fullName;
            public String avatar;
            public String rating;
            @JsonProperty("reviews_as_target_count")
            public Integer reviewsAsTargetCount;
            @JsonProperty("master_orders_count")
            public Object masterOrdersCount;
            public ArrayList<Masters.Brand> brands;
        }
    }
}
