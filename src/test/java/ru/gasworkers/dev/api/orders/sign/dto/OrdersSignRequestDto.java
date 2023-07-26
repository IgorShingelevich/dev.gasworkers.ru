package ru.gasworkers.dev.api.orders.sign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSignRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    private String code;
    private Boolean isClient;

    public static OrdersSignRequestDto clientRequest(Integer orderId) {
        return OrdersSignRequestDto.builder()
                .orderId(orderId)
                .code("111111")
                .isClient(true)
                .build();
    }
}
