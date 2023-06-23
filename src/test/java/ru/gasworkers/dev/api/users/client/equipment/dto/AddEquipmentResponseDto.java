package ru.gasworkers.dev.api.users.client.equipment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddEquipmentResponseDto {
    private Integer status;
    private String message;
    private List<Object> data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private List<String> errors;
    }

    public static AddEquipmentResponseDto successResponse() {
        return new AddEquipmentResponseDto(0, "Оборудование добавлено", List.of(), null);
    }
}
