package ru.gasworkers.dev.api.orders.cancel.cancelOfferClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOfferRequestDto {
    @JsonProperty("offer_id")
    private Integer offerId;
}
