package ru.gasworkers.dev.api.orders.dispatcherPricing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatcherPricingResponseDto {
    private Integer status;
    private String message;
    private List<DataDto> data;

    public static DispatcherPricingResponseDto successResponse() {
        return DispatcherPricingResponseDto.builder()
                .status(0)
                .message("orders__dispatcher_pricing_stored")
                .data(List.of())
                .build();
    }

    public static class DataDto {
    }
}
