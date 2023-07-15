package ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto;

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
public class AddEquipmentRequestDto {
    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("model_id")
    private Integer modelId;

    @JsonProperty("custom_brand")
    private String customBrand;

    @JsonProperty("custom_model")
    private String customModel;

    @JsonProperty("type_id")
    private Integer typeId;

    @JsonProperty("all_works")
    private Boolean allWorks;

    private Double power;
    private String[] photos;
    private String[] videos;

    // Add an exemplar of the class to the test case
    public static AddEquipmentRequestDto defaultBoilerEquipment() {
        return AddEquipmentRequestDto.builder()
                .power(Double.valueOf(35))
                .photos(new String[]{})
                .videos(new String[]{})
                .modelId(2199)
                .customModel(null)
                .customBrand(null)
                .allWorks(true)
                .typeId(1)
                .brandId(25)
                .build();
    }
}
