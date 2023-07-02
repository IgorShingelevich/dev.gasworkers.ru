package ru.gasworkers.dev.api.orders.selectHouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class SelectHouseResponseDto {

    private Integer status;
    private String message;
    private DataDto data;

    public static SelectHouseResponseDto successResponse() {
        return SelectHouseResponseDto.builder()
                .status(0)
                .message("Оборудование выбрано")
                .data(new DataDto().setIsInsuranceCase(false))
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @Accessors(chain = true)
    public static class DataDto {
        @JsonProperty("is_insurance_case")
        private Boolean isInsuranceCase;
    }
}
