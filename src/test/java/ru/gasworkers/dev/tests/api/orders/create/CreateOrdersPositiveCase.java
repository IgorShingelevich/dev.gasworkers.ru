package ru.gasworkers.dev.tests.api.orders.create;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum CreateOrdersPositiveCase {
    CREATE_ORDERS_MAINTENANCE("maintenance"),
    CREATE_ORDERS_CONSULTATION("consultation"),
    CREATE_ORDERS_URGENT("urgent"),
    CREATE_ORDERS_REPAIR("repair");

    private final String description;

    public CreateOrdersRequestDto getCreateRequestDto() {
        switch (this) {
            case CREATE_ORDERS_MAINTENANCE:
                return CreateOrdersRequestDto.builder()
                        .type("maintenance")
                        .build();
            case CREATE_ORDERS_CONSULTATION:
                return CreateOrdersRequestDto.builder()
                        .type("consultation")
                        .build();
            case CREATE_ORDERS_URGENT:
                return CreateOrdersRequestDto.builder()
                        .type("urgent")
                        .build();
            case CREATE_ORDERS_REPAIR:
                return CreateOrdersRequestDto.builder()
                        .type("repair")
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    public CreateOrdersResponseDto getCreateResponseDto(Integer orderId, Boolean isInsuranceCase) {
        switch (this) {
            case CREATE_ORDERS_MAINTENANCE:
            case CREATE_ORDERS_CONSULTATION:
            case CREATE_ORDERS_URGENT:
            case CREATE_ORDERS_REPAIR:
                return CreateOrdersResponseDto.builder()
                        .status(0)
                        .message("Заказ создан")
                        .data(CreateOrdersResponseDto.DataDto.builder()
                                .orderId(orderId)
                                .insuranceCase(false)
                                .build()
                        )
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }
    @Override
    public String toString() {
        return description;
    }
}
