package ru.gasworkers.dev.api.auth.registration.regular.dto.check;

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
    private DataDto dataDto;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        private String[] code;
        private String[] type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        private Integer seconds_left;
    }

    public static CheckRegistrationResponseDto successResponse() {
        List<String> emptyList = new ArrayList<>();
        return new CheckRegistrationResponseDto(0, "Код успешно проверен", null, emptyList, null);
    }

    public static CheckRegistrationResponseDto wrongCodeResponse() {
        String[] codeErrors = {"Неверный код проверки"};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors, null);
        return new CheckRegistrationResponseDto(null, "Неверный код проверки", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto missingCodeResponse() {
        String[] codeErrors = {"Поле code обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors, null);
        return new CheckRegistrationResponseDto(null, "Поле code обязательно для заполнения.", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto invalidTypeResponse() {
        String[] typeErrors = {"Выбранное значение для тип ошибочно."};
        ErrorsDto errorsDto = new ErrorsDto(null, typeErrors);
        return new CheckRegistrationResponseDto(null, "Выбранное значение для тип ошибочно.", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto missingTypeResponse() {
        String[] typeErrors = {"Поле тип обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(null, typeErrors);
        return new CheckRegistrationResponseDto(null, "Поле тип обязательно для заполнения.", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto wrongTypeResponse() {
        String[] typeErrors = {"Неверный тип проверки"};
        ErrorsDto errorsDto = new ErrorsDto(null, typeErrors);
        return new CheckRegistrationResponseDto(null, "Неверный тип проверки", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto invalidPhoneResponse() {
        String[] phoneErrors = {"Неверный формат телефона"};
        ErrorsDto errorsDto = new ErrorsDto(phoneErrors, null);
        return new CheckRegistrationResponseDto(null, "Неверный формат телефона", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto duplicatePhoneResponse() {
        String[] phoneErrors = {"Пользователь с таким телефоном уже существует"};
        ErrorsDto errorsDto = new ErrorsDto(phoneErrors, null);
        return new CheckRegistrationResponseDto(null, "Пользователь с таким телефоном уже существует", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto notMatchPhoneResponse() {
        String[] phoneErrors = {"Указан неверный телефон"};
        ErrorsDto errorsDto = new ErrorsDto(phoneErrors, null);
        return new CheckRegistrationResponseDto(null, "Указан неверный телефон", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto missingPhoneResponse() {
        String[] phoneErrors = {"Поле phone обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(phoneErrors, null);
        return new CheckRegistrationResponseDto(null, "Поле phone обязательно для заполнения.", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto missingAllFieldsResponse() {
        String[] codeErrors = {"Поле code обязательно для заполнения."};
        String[] typeErrors = {"Поле тип обязательно для заполнения."};
        ErrorsDto errorsDto = new ErrorsDto(codeErrors, typeErrors);
        return new CheckRegistrationResponseDto(null, "Поле code обязательно для заполнения. (и еще 1 ошибка)", errorsDto, null, null);
    }

    public static CheckRegistrationResponseDto resentRequestTimeoutResponse() {
        DataDto dataDto = new DataDto(56);
        return new CheckRegistrationResponseDto(1008, "Вы можете повторить запрос через 56 секунд", null, null, dataDto);
    }
}
