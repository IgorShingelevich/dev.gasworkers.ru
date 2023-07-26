package ru.gasworkers.dev.api.orders.sign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSignResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersSignResponseDto successResponse() {
        return OrdersSignResponseDto.builder()
                .status(0)
                .message("Документ подписан")
                .data(List.of())
                .build();
    }

    public static class DataDto {
    }
}
