package ru.gasworkers.dev.api.orders.selectServiceCompany.dto;

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
public class SelectServiceCompanyRequestDto {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-service-company

    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("service_id")
    private Integer serviceId;
    @JsonProperty("master_id")
    private Integer masterId;
}
