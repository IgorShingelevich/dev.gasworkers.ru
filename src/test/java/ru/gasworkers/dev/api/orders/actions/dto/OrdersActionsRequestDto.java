package ru.gasworkers.dev.api.orders.actions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersActionsRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
}
