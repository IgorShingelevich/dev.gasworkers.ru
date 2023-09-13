package ru.gasworkers.dev.api.orders.cancel.moneyBack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyBackRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("receipt_id")
    private Integer receiptId;
}
