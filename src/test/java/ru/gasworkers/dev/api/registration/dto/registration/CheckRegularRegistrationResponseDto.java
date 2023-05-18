package ru.gasworkers.dev.api.registration.dto.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CheckRegularRegistrationResponseDto {
    private Integer status;
    private String message;
    private ErrorsDto errors;
    private DataDto data;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private String[] code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        private String[] code;
    }

    public static CheckRegularRegistrationResponseDto successResponse() {
        return new CheckRegularRegistrationResponseDto(0, "Код успешно проверен", null, null );
    }

    public static CheckRegularRegistrationResponseDto wrongCodeResponse() {
        String[] codeErrors = {"Неверный код проверки"};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors);
        return new CheckRegularRegistrationResponseDto(null, "Неверный код проверки", errorsDto, null);
    }


}
