package ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class OnlineMastersRequestDto {

    /*search=cc&order_id=6675&main_filter=price&only_user_brands=true&price_from=2*/
    private String search;
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("main_filter") // price, rating
    private String mainFilter;
    @JsonProperty("only_user_brands")
    private Boolean onlyUserBrands;
    @JsonProperty("price_from")
    private Integer priceFrom;
}
