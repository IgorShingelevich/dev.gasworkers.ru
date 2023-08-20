package ru.gasworkers.dev.api.users.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationsResponseDto {

    private ArrayList<Data> data;
    private Links links;
    private Meta meta;
    private Integer status;
    private String message;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Data {
        private Integer id;
        private Order order;
        private Object target_user;
        private Integer order_id;
        private String text;
        private Boolean read;
        private String key;
        private Boolean important;
        private Boolean dispatcher_map;
        private String button_text;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Links {
        private String url;
        private String label;
        private Boolean active;
        private String first;
        private String last;
        private Object prev;
        private String next;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Meta {
        private Integer current_page;
        private Integer from;
        private Integer last_page;
        private ArrayList<Links> links;
        private String path;
        private Integer per_page;
        @JsonProperty("to")
        private Integer myto;
        private Integer total;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Order {
        private Integer id;
        private String number;
        private String creation_status;
        private String type;
        private String status;
    }

}
