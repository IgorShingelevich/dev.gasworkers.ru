package ru.gasworkers.dev.api.orders.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTransferResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrderTransferResponseDto successResponse() {
        return OrderTransferResponseDto.builder()
                .status(0)
                .message("Заказ перенаправлен")
                .data(List.of())
                .build();
    }

    public static class DataDto {
    }
}
