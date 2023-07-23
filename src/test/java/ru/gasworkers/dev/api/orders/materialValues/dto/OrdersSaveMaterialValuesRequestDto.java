package ru.gasworkers.dev.api.orders.materialValues.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSaveMaterialValuesRequestDto {

    @JsonProperty("order_id")
    private Integer orderId;
    private Boolean paid;
    @JsonProperty("material_values")
    private List<MaterialValuesDto> materialValues;

    public static OrdersSaveMaterialValuesRequestDto oneMaterialRequest(Integer orderId) {
        return OrdersSaveMaterialValuesRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .materialValues(List.of(MaterialValuesDto.builder()
                        .title("Material 1 Title")
                        .partNumber("Material 1 Part Number")
                        .qty(3)
                        .price(1.33)
                        .manufacturer("Material 1 Manufacturer")
                        .searchQuery("")
                        .valueList(List.of())
                        .searchActive(false)
                        .build()))
                .build();
    }

    public static OrdersSaveMaterialValuesRequestDto twoMaterialRequest(Integer orderId) {
        return OrdersSaveMaterialValuesRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .materialValues(List.of(MaterialValuesDto.builder()
                                .title("Material 1 Title")
                                .partNumber("Material 1 Part Number")
                                .qty(3)
                                .price(1.33)
                                .manufacturer("Material 1 Manufacturer")
                                .searchQuery("")
                                .valueList(List.of())
                                .searchActive(false)
                                .build(),
                        MaterialValuesDto.builder()
                                .title("Material 2 Title")
                                .partNumber("Material 2 Part Number")
                                .qty(4)
                                .price(2.33)
                                .manufacturer("Material 2 Manufacturer")
                                .searchQuery("")
                                .valueList(List.of())
                                .searchActive(false)
                                .build()))
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MaterialValuesDto {
        private String title;
        @JsonProperty("part_number")
        private String partNumber;
        private Integer qty;
        private Double price;
        private String manufacturer;
        @JsonProperty("searchQuery")
        private String searchQuery;
        @JsonProperty("valueList")
        private List<String> valueList;
        @JsonProperty("searchActive")
        private Boolean searchActive;
    }
}
