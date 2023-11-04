package ru.gasworkers.dev.api.orders.actions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSaveActionsResponseDto {
    private Integer status;
    private String message;
    private DataDto data;

    public static OrdersSaveActionsResponseDto oneActionResponse() {

        return OrdersSaveActionsResponseDto.builder()
                .status(0)
                .message("Данные о необходимых работах сохранены")
                .data(DataDto.builder()
                        .totalAmount("12988.00")
                        .build())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        private String totalAmount;
    }
}
