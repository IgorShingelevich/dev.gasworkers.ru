package ru.gasworkers.dev.api.orders.materialValues.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersMaterialValuesRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;


}
