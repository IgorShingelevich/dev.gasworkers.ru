package ru.gasworkers.dev.api.orders.selectPayment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
//@Accessors(chain = true)
public class SelectPaymentRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    private String type;
    @JsonProperty("receipt_id")
    private Integer receiptId;

    public static SelectPaymentRequestDto emptyRequest() {
        return SelectPaymentRequestDto.builder()
                .build();
    }
}
