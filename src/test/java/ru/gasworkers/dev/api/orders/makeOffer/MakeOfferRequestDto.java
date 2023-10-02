package ru.gasworkers.dev.api.orders.makeOffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeOfferRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
}
