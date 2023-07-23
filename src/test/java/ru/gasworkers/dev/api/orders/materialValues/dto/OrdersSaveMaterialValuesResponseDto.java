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
public class OrdersSaveMaterialValuesResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersSaveMaterialValuesResponseDto successResponse() {
        return OrdersSaveMaterialValuesResponseDto.builder()
                .status(0)
                .message("Данные о расходниках сохранены")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
