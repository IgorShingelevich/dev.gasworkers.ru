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
    private Integer status;
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

    public static StartRegistrationResponseDto successResponse(String secondsLeft) {
        return new StartRegistrationResponseDto(0, "Успешная регистрация",
                new DataDto(secondsLeft), null);
    }

    public static StartRegistrationResponseDto typeMissingResponse() {
        String[] typeErrors = {"Поле тип обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(typeErrors, null, null);
        return new StartRegistrationResponseDto(null, "Поле тип обязательно для заполнения.",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto missingEmailOrPhoneResponse() {
        String[] emailErrors = {"Поле E-Mail обязательно для заполнения, когда Номер телефона не указано."};
        String[] phoneErrors = {"Поле Номер телефона обязательно для заполнения, когда E-Mail не указано."};
        ErrorsDto errorsDto = new ErrorsDto(null, emailErrors, phoneErrors);
        return new StartRegistrationResponseDto(null, "Поле E-Mail обязательно для заполнения, когда Номер телефона не указано. (и еще 1 ошибка)",
                null, errorsDto);
    }



    public static StartRegistrationResponseDto emailInvalidErrorResponse() {
        String[] emailErrors = {"Поле E-Mail должно быть действительным электронным адресом."};
        ErrorsDto errorsDto = new ErrorsDto(null, emailErrors, null);
        return new StartRegistrationResponseDto(null, "Поле E-Mail должно быть действительным электронным адресом.",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto phoneInvalidErrorResponse() {
        String[] phoneErrors = {
                "Введите корректный номер"};
        ErrorsDto errorsDto = new ErrorsDto(null, null, phoneErrors);
        return new StartRegistrationResponseDto(null, "Введите корректный номер",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto phoneAlreadyExistsErrorResponse() {
        String[] phoneErrors = {
                "Такое значение поля Номер телефона уже существует."};
        ErrorsDto errorsDto = new ErrorsDto(null, null, phoneErrors);
        return new StartRegistrationResponseDto(null, "Такое значение поля Номер телефона уже существует.",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto emailAndPhoneAlreadyExistsErrorResponse() {
        String[] emailErrors = {
                "Такое значение поля E-Mail уже существует."};
        String[] phoneErrors = {
                "Такое значение поля Номер телефона уже существует."};
        ErrorsDto errorsDto = new ErrorsDto(null, emailErrors, phoneErrors);
        return new StartRegistrationResponseDto(null, "Такое значение поля E-Mail уже существует. (и еще 1 ошибка)",
                null, errorsDto);
    }

    public static StartRegistrationResponseDto noTypeEmailAndPhoneAlreadyExistsErrorResponse() {
        String[] typeErrors = {"Поле тип обязательно для заполнения."};
        String[] emailErrors = {
                "Такое значение поля E-Mail уже существует."};
        String[] phoneErrors = {
                "Такое значение поля Номер телефона уже существует."};
        ErrorsDto errorsDto = new ErrorsDto(typeErrors, emailErrors, phoneErrors);
        return new StartRegistrationResponseDto(null, "Поле тип обязательно для заполнения. (и еще 2 ошибки)",
                null, errorsDto);
    }

}
