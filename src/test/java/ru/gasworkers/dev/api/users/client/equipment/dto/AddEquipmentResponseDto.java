package ru.gasworkers.dev.api.users.client.equipment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class AddEquipmentResponseDto {
    private Integer status;
    private String message;
    private List<Object> data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ErrorsDto {
        @JsonProperty("brand_id")
        private String[] brandId;
        @JsonProperty("custom_brand")
        private String[] customBrand;
        @JsonProperty("model_id")
        private String[] modelId;
        @JsonProperty("custom_model")
        private String[] customModel;
        @JsonProperty("all_works")
        private String[] allWorks;
        @JsonProperty("photos.0")
        private String[] photos;
        @JsonProperty("videos.0")
        private String[] videos;
    }

    public static AddEquipmentResponseDto missingAllFieldsResponse() {
        ErrorsDto errors = ErrorsDto.builder()
                .brandId(new String[]{"Поле brand id обязательно для заполнения."})
                .customBrand(new String[]{"Поле custom brand обязательно для заполнения."})
                .allWorks(new String[]{"Поле all works обязательно для заполнения."})
                .build();
        return AddEquipmentResponseDto.builder()
                .status(null)
                .message("Поле brand id обязательно для заполнения. (и еще 2 ошибки)")
                .data(null)
                .errors(errors)
                .build();
    }

    public static AddEquipmentResponseDto successResponse() {
        return AddEquipmentResponseDto.builder()
                .status(0)
                .message("Оборудование добавлено")
                .data(List.of())
                .errors(null)
                .build();
    }

    public static AddEquipmentResponseDto wrongModelIdResponse() {
        ErrorsDto errors = ErrorsDto.builder()
                .modelId(new String[]{"Выбранное значение для model id некорректно."})
                .build();
        return AddEquipmentResponseDto.builder()
                .status(null)
                .message("Выбранное значение для model id некорректно.")
                .data(null)
                .errors(errors)
                .build();
    }

    public static AddEquipmentResponseDto invalidPhotoResponse() {
        ErrorsDto errors = ErrorsDto.builder()
                .photos(new String[]{"Поле photos.0 должно быть числом."})
                .build();
        return AddEquipmentResponseDto.builder()
                .status(null)
                .message("Поле photos.0 должно быть числом.")
                .data(null)
                .errors(errors)
                .build();
    }

    public static AddEquipmentResponseDto invalidVideoResponse() {
        ErrorsDto errors = ErrorsDto.builder()
                .videos(new String[]{"Поле videos.0 должно быть числом."})
                .build();
        return AddEquipmentResponseDto.builder()
                .status(null)
                .message("Поле videos.0 должно быть числом.")
                .data(null)
                .errors(errors)
                .build();
    }

    public static AddEquipmentResponseDto missingAllWorksResponse() {
        ErrorsDto errors = ErrorsDto.builder()
                .allWorks(new String[]{"Поле all works обязательно для заполнения."})
                .build();
        return AddEquipmentResponseDto.builder()
                .status(null)
                .message("Поле all works обязательно для заполнения.")
                .data(null)
                .errors(errors)
                .build();
    }
}
