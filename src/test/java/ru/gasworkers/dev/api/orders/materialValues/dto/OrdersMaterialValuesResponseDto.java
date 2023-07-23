package ru.gasworkers.dev.api.orders.materialValues.dto;

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
public class OrdersMaterialValuesResponseDto {
    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersMaterialValuesResponseDto successResponse() {
        return OrdersMaterialValuesResponseDto.builder()
                .status(1)
                .message("Список расходников по заказу")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
