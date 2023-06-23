package ru.gasworkers.dev.api.users.client.equipment.dto;

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

    private Integer power;
    private String[] photos;
    private String[] videos;
}
