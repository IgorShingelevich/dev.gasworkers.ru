package ru.gasworkers.dev.api.orders.create;

import lombok.Builder;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;

@Builder
public class CreateOrdersBuilder {

    public static CreateOrderResponseDto newOrderResponse(Integer orderId) {
        return CreateOrderResponseDto.builder()
                .status(0)
                .message("Заказ создан")
                .data(CreateOrderResponseDto.DataDto.builder()
                        .orderId(orderId)
                        .isInsuranceCase(false)
                        .build()
                )
                .build();
    }
}
