package ru.gasworkers.dev.api.orders.materialValues.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSaveMaterialValuesResponseDto {


    private Integer status;
    private String message;
    private DataDto data;

    public static OrdersSaveMaterialValuesResponseDto oneMaterialResponse() {

        return OrdersSaveMaterialValuesResponseDto.builder()
                .status(0)
                .message("Данные о расходниках сохранены")
                .data(DataDto.builder()
                        .totalAmount("3300.00")
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
