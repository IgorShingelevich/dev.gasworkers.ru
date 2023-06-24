package ru.gasworkers.dev.api.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private List<T> data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class ErrorsDto {
        private Map<String, List<String>> errors;

        // Constructors, getters, and setters
    }
}
