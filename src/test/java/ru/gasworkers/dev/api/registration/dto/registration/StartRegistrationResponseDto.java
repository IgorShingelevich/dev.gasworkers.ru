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
public class StartRegistrationResponseDto {
    private int status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        @JsonProperty("seconds_left")
        private String secondsLeft;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private String[] type;
        private String[] email;
        private String[] phone;
    }

    public static StartRegistrationResponseDto createSuccessResponse(String secondsLeft) {
        return new StartRegistrationResponseDto(0, "Успешная регистрация",
                new DataDto(secondsLeft), null);
    }

    public static StartRegistrationResponseDto createTypeMissingErrorResponse() {
        String[] typeErrors = {"Поле тип обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(typeErrors, null, null);
        return new StartRegistrationResponseDto(0, "Поле тип обязательно для заполнения.",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto createEmailPhoneErrorResponse() {
        String[] emailErrors = {"Поле E-Mail обязательно для заполнения, когда Номер телефона не указано."};
        String[] phoneErrors = {"Поле Номер телефона обязательно для заполнения, когда E-Mail не указано."};
        ErrorsDto errorsDto = new ErrorsDto(null, emailErrors, phoneErrors);
        return new StartRegistrationResponseDto(0, "Поле E-Mail обязательно для заполнения, когда Номер телефона не указано.",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto createEmailInvalidErrorResponse() {
        String[] emailErrors = {"Поле E-Mail должно быть действительным электронным адресом."};
        ErrorsDto errorsDto = new ErrorsDto(null, emailErrors, null);
        return new StartRegistrationResponseDto(0, "Поле E-Mail должно быть действительным электронным адресом.",
                null, errorsDto);
    }
}
