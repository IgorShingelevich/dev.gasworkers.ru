package ru.gasworkers.dev.api.orders.selectHouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class SelectHouseRequestDto {

    @JsonProperty("client_object_id")
    private Integer clientObjectId;
    private List<Integer> equipment;
    @JsonProperty("order_id")
    private Integer orderId;

}
