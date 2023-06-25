package ru.gasworkers.dev.api.orders.create;

import lombok.Builder;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;

@Builder
public class CreateOrdersBuilder {

    public static CreateOrdersResponseDto newOrderResponse(Integer orderId) {
        return CreateOrdersResponseDto.builder()
                .status(0)
                .message("Заказ создан")
                .data(CreateOrdersResponseDto.DataDto.builder()
                        .orderId(orderId)
                        .insuranceCase(false)
                        .build()
                )
                .build();
    }
}
