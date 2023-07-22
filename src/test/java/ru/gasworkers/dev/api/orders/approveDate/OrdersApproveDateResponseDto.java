package ru.gasworkers.dev.api.orders.approveDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersApproveDateResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static OrdersApproveDateResponseDto successResponse() {
        return OrdersApproveDateResponseDto.builder()
                .status(0)
                .message("Дата исполнения подтверждена")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
