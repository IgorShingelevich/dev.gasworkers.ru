package ru.gasworkers.dev.api.registration.regular.dto.check;

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
public class CheckRegistrationResponseDto {
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

    public static CheckRegistrationResponseDto successResponse() {
        List<String> emptyList = new ArrayList<>();
        return new CheckRegistrationResponseDto(0, "Код успешно проверен", null, emptyList);
    }

    public static CheckRegistrationResponseDto wrongCodeResponse() {
        String[] codeErrors = {"Неверный код проверки"};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors);
        return new CheckRegistrationResponseDto(null, "Неверный код проверки", errorsDto, null);
    }

    public static CheckRegistrationResponseDto missingCodeResponse() {
        String[] codeErrors = {"Поле code обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors);
        return new CheckRegistrationResponseDto(null, "Поле code обязательно для заполнения.", errorsDto, null);
    }

}
