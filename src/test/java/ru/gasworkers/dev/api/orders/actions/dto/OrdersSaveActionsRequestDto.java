package ru.gasworkers.dev.api.orders.actions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSaveActionsRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    private Boolean paid;
    private List<Actions> actions;

    public static OrdersSaveActionsRequestDto actionRequest(Integer qty, Double price, Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(Actions.builder()
                        .title("Work name")
                        .qty(qty)
                        .price(price)
                        .searchQuery("")
                        .actionList(Collections.emptyList())
                        .searchActive(false)
                        .build()))
                .build();
    }

    public static OrdersSaveActionsRequestDto oneActionRequest(Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(Actions.builder()
                        .title("Work name")
                        .qty(3)
                        .price(1000.0)
                        .searchQuery("")
                        .actionList(Collections.emptyList())
                        .searchActive(false)
                        .build()))
                .build();
    }

    public static OrdersSaveActionsRequestDto twoActionsRequest(Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(Actions.builder()
                                .title("Work name")
                                .qty(3)
                                .price(33.99)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build(),
                        Actions.builder()
                                .title("Work name 2")
                                .qty(9)
                                .price(111.99)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build()))
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Actions {
        private String title;
        private Integer qty;
        private Double price;
        private String searchQuery;
        private List<ActionList> actionList;
        private Boolean searchActive;
    }

    public static class ActionList {
    }
}
