package ru.gasworkers.dev.api.orders.createAct;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateActRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    private List<Videos> videos;
    @JsonProperty("all_works")
    private Boolean allWorks;

    public static OrdersCreateActRequestDto defaultRequest(Integer orderId) {
        return OrdersCreateActRequestDto.builder()
                .orderId(orderId)
                .allWorks(true)
                .videos(List.of())
                .build();
    }

    public static class Videos {
    }
}

