package ru.gasworkers.dev.api.orders.createAct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateActResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersCreateActResponseDto defaultResponse() {
        return OrdersCreateActResponseDto.builder()
                .status(0)
                .message("Акт создан. Код подтверждения выслан в смс")
                .data(List.of())
                .build();
    }

    public static class DataDto {
    }
}
