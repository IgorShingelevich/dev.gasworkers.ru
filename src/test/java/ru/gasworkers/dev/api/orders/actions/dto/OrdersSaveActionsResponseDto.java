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
public class OrdersSaveActionsResponseDto {
    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersSaveActionsResponseDto successResponse() {
        return OrdersSaveActionsResponseDto.builder()
                .status(0)
                .message("Данные о необходимых работах сохранены")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
