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

    /*{
    "order_id": 11055,
    "paid": false,
    "actions": [
        {
            "title": "Первичный выезд мастера",
            "qty": "1",
            "price": 5999,
            "searchQuery": "",
            "actionList": [],
            "searchActive": false
        },
        {
            "title": "123",
            "qty": 111,
            "price": 1,
            "searchQuery": "",
            "actionList": [],
            "searchActive": false
        }
    ],
    "video": [
        3811
    ],
    "all_works": true
}*/

    @JsonProperty("order_id")
    private Integer orderId;
    private Boolean paid;
    private List<Actions> actions;
    private List<Integer> video;
    @JsonProperty("all_works")
    private Boolean allWorks;

    public static OrdersSaveActionsRequestDto oneActionRequest(Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(
                        Actions.builder()
                                .title("Первичный выезд мастера")
                                .qty(1)
                                .price(5999.0)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build(),
                        Actions.builder()
                                .title("Work name 1")
                                .qty(1)
                                .price(6999.0)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build()
                ))
                .video(List.of(3811))
                .allWorks(true)
                .build();
    }

    public static OrdersSaveActionsRequestDto twoActionsRequest(Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(
                        Actions.builder()
                                .title("Первичный выезд мастера")
                                .qty(1)
                                .price(5999.0)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build(),
                        Actions.builder()
                                .title("Work name 1")
                                .qty(1)
                                .price(6999.0)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build(),
                        Actions.builder()
                                .title("Work name 2")
                                .qty(1)
                                .price(7999.0)
                                .searchQuery("")
                                .actionList(Collections.emptyList())
                                .searchActive(false)
                                .build()
                ))
                .video(List.of(3811))
                .allWorks(true)
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
