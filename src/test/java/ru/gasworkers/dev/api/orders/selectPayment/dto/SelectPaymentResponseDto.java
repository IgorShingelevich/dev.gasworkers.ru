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
    private DataDto data;
    private ErrorDto error;

    @Builder
    public static SelectPaymentResponseDto successResponse(String url, Integer paymentId) {
        return SelectPaymentResponseDto.builder()
                .status(0)
                .message("Ссылка на оплату заказа")
                .data(DataDto.builder()
                        .payUrl(url)
                        .qrLink(null)
                        .fpsLink(null)
                        .paymentId(paymentId)
                        .build())
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class DataDto {
        @JsonProperty("pay_url")
        private String payUrl;
        @JsonProperty("qr_link")
        private String qrLink;
        @JsonProperty("fps_link")
        private String fpsLink;
        @JsonProperty("payment_id")
        private Integer paymentId;
    }


}
