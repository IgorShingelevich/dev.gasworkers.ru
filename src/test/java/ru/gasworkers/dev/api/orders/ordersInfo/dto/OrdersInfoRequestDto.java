package ru.gasworkers.dev.api.orders.ordersInfo.dto;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInfoRequestDto {
    @JsonProperty("order_id")
    private Integer order_id;

}
