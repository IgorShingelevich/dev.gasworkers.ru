package ru.gasworkers.dev.api.orders.selectPayment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class SelectPaymentResponseDto {
    private Integer status;
    private String message;
    private String data;
    private ErrorDto error;

    public static SelectPaymentResponseDto successResponse(String url) {
        return SelectPaymentResponseDto.builder()
                .status(0)
                .message("Payment URL")
                .data(url)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class ErrorDto {
        @JsonProperty("order_id")
        private String orderId;
        private String type;
    }


}
