package ru.gasworkers.dev.tests.api.orders.create;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum CreateOrdersPositiveCase {
    CREATE_ORDERS_MAINTENANCE("maintenance(  todo  add optional objectId cases)"),
    CREATE_ORDERS_CONSULTATION("consultation"),
    CREATE_ORDERS_URGENT("urgent"),
    CREATE_ORDERS_REPAIR("repair");

    private final String description;

    public CreateOrderRequestDto getCreateRequestDto() {
        switch (this) {
            case CREATE_ORDERS_MAINTENANCE:
                return CreateOrderRequestDto.builder()
                        .type("maintenance")
                        .build();
            case CREATE_ORDERS_CONSULTATION:
                return CreateOrderRequestDto.builder()
                        .type("consultation")
                        .build();
            case CREATE_ORDERS_URGENT:
                return CreateOrderRequestDto.builder()
                        .type("urgent")
                        .build();
            case CREATE_ORDERS_REPAIR:
                return CreateOrderRequestDto.builder()
                        .type("repair")
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    public CreateOrderResponseDto getCreateResponseDto(Integer orderId, Boolean isInsuranceCase) {
        switch (this) {
            case CREATE_ORDERS_MAINTENANCE:
            case CREATE_ORDERS_CONSULTATION:
            case CREATE_ORDERS_URGENT:
            case CREATE_ORDERS_REPAIR:
                return CreateOrderResponseDto.builder()
                        .status(0)
                        .message("Заказ создан")
                        .data(CreateOrderResponseDto.DataDto.builder()
                                .orderId(orderId)
                                .isInsuranceCase(false)
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
