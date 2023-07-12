package ru.gasworkers.dev.api.orders.info.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrdersInfoRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("receipt_id")
    private Integer receiptId;

}
