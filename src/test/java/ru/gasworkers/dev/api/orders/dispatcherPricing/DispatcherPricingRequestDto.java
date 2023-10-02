package ru.gasworkers.dev.api.orders.dispatcherPricing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatcherPricingRequestDto {
    @JsonProperty("first_accept_price")
    private Integer firstAcceptPrice;
    @JsonProperty("full_repair_price")
    private String fullRepairPrice;
    @JsonProperty("master_id")
    private Integer masterId;
}
