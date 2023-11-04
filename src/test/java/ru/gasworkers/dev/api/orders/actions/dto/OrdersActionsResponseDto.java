package ru.gasworkers.dev.api.orders.actions.dto;

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
public class OrdersActionsResponseDto {
    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersActionsResponseDto successResponse() {
        return OrdersActionsResponseDto.builder()
                .status(0)
                .message("Список выполняемых работ по заказу")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
