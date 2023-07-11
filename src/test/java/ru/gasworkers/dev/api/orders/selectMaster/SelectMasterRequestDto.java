package ru.gasworkers.dev.api.orders.selectMaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectMasterRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("master_id")
    private Integer masterId;
}
