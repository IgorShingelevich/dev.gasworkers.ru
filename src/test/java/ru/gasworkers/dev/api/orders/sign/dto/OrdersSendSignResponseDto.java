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
public class OrdersSendSignResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersSendSignResponseDto successResponse() {
        return OrdersSendSignResponseDto.builder()
                .status(0)
                .message("Код отправлен")
                .data(List.of())
                .build();
    }

    public static class DataDto {

    }
}
