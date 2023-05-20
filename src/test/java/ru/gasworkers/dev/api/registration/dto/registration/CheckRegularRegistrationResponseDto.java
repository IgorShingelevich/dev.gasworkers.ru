package ru.gasworkers.dev.api.registration.dto.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRegularRegistrationResponseDto {
    private Integer status;
    private String message;
    private ErrorsDto errors;
    private List<String> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private String[] code;
    }

    public static CheckRegularRegistrationResponseDto successResponse() {
        List<String> emptyList = new ArrayList<>();
        return new CheckRegularRegistrationResponseDto(0, "Код успешно проверен", null, emptyList);
    }

    public static CheckRegularRegistrationResponseDto wrongCodeResponse() {
        String[] codeErrors = {"Неверный код проверки"};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors);
        return new CheckRegularRegistrationResponseDto(null, "Неверный код проверки", errorsDto, null);
    }
}
