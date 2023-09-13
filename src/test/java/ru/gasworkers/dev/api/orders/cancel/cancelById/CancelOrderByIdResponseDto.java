package ru.gasworkers.dev.api.orders.cancel.cancelById;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderByIdResponseDto {

    private Integer status;
    private String message;
    private List<String> data;

    public static CancelOrderByIdResponseDto defaultCancelResponse() {
        return CancelOrderByIdResponseDto.builder()
                .status(0)
                .message("Заказ отменен")
                .data(List.of())
                .build();
    }


}
