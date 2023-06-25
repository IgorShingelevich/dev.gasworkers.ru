package ru.gasworkers.dev.api.orders.create.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CreateOrdersResponseDto {

    private Integer status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @Builder
    public static class ErrorsDto {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DataDto {
        @JsonProperty("order_id")
        private Integer orderId;
        @JsonProperty("insurance_case")
        private Boolean insuranceCase;
    }

    public static CreateOrdersResponseDto successResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .status(0)
                .message("Заказ создан")
                .data(DataDto.builder()
                        .orderId(orderId)
                        .insuranceCase(isInsuranceCase)
                        .build())
                .errors(null)
                .build();
    }
}
