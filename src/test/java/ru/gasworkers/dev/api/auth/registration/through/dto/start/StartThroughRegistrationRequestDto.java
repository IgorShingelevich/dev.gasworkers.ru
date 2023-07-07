package ru.gasworkers.dev.api.auth.registration.through.dto.start;

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
public class StartThroughRegistrationRequestDto {
    ////    https://api.dev.gasworkers.ru/docs#avtorizaciiaregistraciia-POSTapi-v1-auth-register-through
    private String type;
    @JsonProperty("object_id")
    private Integer objectId;
    @JsonProperty("address_id")
    private Integer addressId;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    private Integer time;
    @JsonProperty("time_started")
    private String timeStarted;
    @JsonProperty("time_ended")
    private String timeEnded;
    private String email;
    private Long phone;
    private String description;
    @JsonProperty("no_guide")
    private Boolean noGuide;
    @JsonProperty("old_equipments")
    private OldEquipmentsDto oldEquipments;
    private EquipmentsDto equipments;

    @Builder
    public static StartThroughRegistrationRequestDto newInstance(String type, Integer objectId, Integer addressId, String startDate, String endDate, Integer time, String timeStarted, String timeEnded, String email, Long phone, String description, Boolean noGuide, StartThroughRegistrationRequestDto.OldEquipmentsDto oldEquipments, StartThroughRegistrationRequestDto.EquipmentsDto equipments) {
        return StartThroughRegistrationRequestDto.builder()
                .type(type)
                .objectId(objectId)
                .addressId(addressId)
                .startDate(startDate)
                .endDate(endDate)
                .time(time)
                .timeStarted(timeStarted)
                .timeEnded(timeEnded)
                .email(email)
                .phone(phone)
                .description(description)
                .noGuide(noGuide)
                .oldEquipments(oldEquipments)
                .equipments(equipments)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @Accessors(chain = true)
    public static class EquipmentsDto {
        @JsonProperty("type_id")
        private Integer typeId;
        @JsonProperty("brand_id")
        private Integer brandId;
        @JsonProperty("custom_brand")
        private String customBrand;
        @JsonProperty("model_id")
        private Integer modelId;
        @JsonProperty("custom_model")
        private String customModel;
        private Double power;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @Accessors(chain = true)
    public static class OldEquipmentsDto {
        @JsonProperty("type_id")
        private Integer typeId;
        @JsonProperty("brand_id")
        private Integer brandId;
        @JsonProperty("custom_brand")
        private String customBrand;
        @JsonProperty("model_id")
        private Integer modelId;
        @JsonProperty("custom_model")
        private String customModel;
        private String power;
    }
}
