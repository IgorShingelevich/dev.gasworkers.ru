package ru.gasworkers.dev.api.orders.suggestedServices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestServicesRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    private String filter; //rating

}
