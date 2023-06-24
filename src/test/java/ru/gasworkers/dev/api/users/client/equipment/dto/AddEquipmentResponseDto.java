package ru.gasworkers.dev.api.users.client.equipment.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddEquipmentResponseDto {

    private Integer status;
    private String message;
    private List<Object> data;
    private ErrorsDto errors;

    public static AddEquipmentResponseDto missingAllFieldsResponse() {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("brand_id", List.of("Поле brand id обязательно для заполнения."));
        errors.put("custom_brand", List.of("Поле custom brand обязательно для заполнения."));
        errors.put("all_works", List.of("Поле all works обязательно для заполнения."));
        ErrorsDto errorsDto = new ErrorsDto(errors);
        return new AddEquipmentResponseDto(null, "Поле brand id обязательно для заполнения. (и еще 2 ошибки)", null, errorsDto);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private Map<String, List<String>> errors = new HashMap<>();

        @JsonAnySetter
        public void addError(String key, List<String> value) {
            errors.put(key, value);
        }
    }

    public static AddEquipmentResponseDto successResponse() {
        return new AddEquipmentResponseDto(0, "Оборудование добавлено", List.of(), null);
    }

    public static AddEquipmentResponseDto wrongModelIdResponse() {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("model_id", List.of("Выбранное значение для model id некорректно."));
        ErrorsDto errorsDto = new ErrorsDto(errors);
        return new AddEquipmentResponseDto(null, "Выбранное значение для model id некорректно.", null, errorsDto);
    }

    public static AddEquipmentResponseDto invalidPhotoResponse() {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("photos.0", List.of("Поле photos.0 должно быть числом."));
        ErrorsDto errorsDto = new ErrorsDto(errors);
        return new AddEquipmentResponseDto(null, "Поле photos.0 должно быть числом.", null, errorsDto);
    }

    public static AddEquipmentResponseDto invalidVideoResponse() {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("videos.0", List.of("Поле videos.0 должно быть числом."));
        ErrorsDto errorsDto = new ErrorsDto(errors);
        return new AddEquipmentResponseDto(null, "Поле videos.0 должно быть числом.", null, errorsDto);
    }

    public static AddEquipmentResponseDto missingAllWorksResponse() {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("all_works", List.of("Поле all works обязательно для заполнения."));
        ErrorsDto errorsDto = new ErrorsDto(errors);
        return new AddEquipmentResponseDto(null, "Поле all works обязательно для заполнения.", null, errorsDto);
    }
}
