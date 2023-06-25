package ru.gasworkers.dev.api.orders.create.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Accessors(chain = true)
public class CreateOrdersRequestDto {
    /*{
    "type": "consultation"
    Тип заказа (urgent/repair/maintenance/consultation) Example: maintenance
}*/
    private String type;
    @JsonProperty("object_id")
    private String objectId;
}
