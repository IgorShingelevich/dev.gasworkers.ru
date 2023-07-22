package ru.gasworkers.dev.api.orders.approveDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersApproveDateRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("selected_date")
    private String selectedDate;

}
