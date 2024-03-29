package ru.gasworkers.dev.api.auth.registration.regular.dto.finish;

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
public class FinishRegistrationResponseDto {
    private Integer status;
    private String message;
    private List<Object> data;
    private ErrorsDto errors;

    public static FinishRegistrationResponseDto missingPasswordErrorResponse() {
        return fieldSpecificErrorResponse("password", "Поле Пароль обязательно для заполнения.");
    }

    public static FinishRegistrationResponseDto missingFirstNameErrorResponse() {
        return fieldSpecificErrorResponse("first_name", "Поле не заполнено");
    }

    public static FinishRegistrationResponseDto missingLastNameErrorResponse() {
        return fieldSpecificErrorResponse("last_name", "Поле не заполнено");
    }

    public static FinishRegistrationResponseDto invalidGenderErrorResponse() {
        return fieldSpecificErrorResponse("gender", "Выбранное значение для gender ошибочно.");
    }

    public static FinishRegistrationResponseDto invalidRoleFieldErrorResponse(String field) {
        return fieldSpecificErrorResponse(field, "Неверный формат");
    }

    public static FinishRegistrationResponseDto missingEmailAndPhoneErrorResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
//        errorMap.put("email", List.of("Поле E-Mail обязательно для заполнения."));
        errorMap.put("phone", List.of("Поле Номер телефона обязательно для заполнения."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Поле Номер телефона обязательно для заполнения.", null, errorsDto);

    }

    public static FinishRegistrationResponseDto invalidPhoneFormatErrorResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("phone", List.of("Введите корректный номер", "Поле Номер телефона должно быть целым числом."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Введите корректный номер (и еще 1 ошибка)", null, errorsDto);
    }

    public static FinishRegistrationResponseDto invalidNameErrorResponse() {
        return fieldSpecificErrorResponse("first_name", "Поле Имя может содержать только буквы и цифры.");
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

    public static FinishRegistrationResponseDto successResponse() {
        return new FinishRegistrationResponseDto(0, "Регистрация завершена", List.of(), null);
    }

    public static FinishRegistrationResponseDto missingEmailErrorResponse() {
        return fieldSpecificErrorResponse("email", "Поле E-Mail обязательно для заполнения.");
    }

    public static FinishRegistrationResponseDto invalidEmailErrorResponse() {
        return fieldSpecificErrorResponse("email", "Поле E-Mail должно быть действительным электронным адресом.");
    }

    public static FinishRegistrationResponseDto duplicateEmailErrorResponse() {
        return duplicateFieldErrorResponse("email");
    }

    public static FinishRegistrationResponseDto missingNameErrorResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("first_name", List.of("Поле не заполнено"));
        errorMap.put("last_name", List.of("Поле не заполнено"));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Поле не заполнено (и еще 1 ошибка)", null, errorsDto);
    }

    public static FinishRegistrationResponseDto invalidFullNameErrorResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("first_name", List.of("Поле Имя может содержать только буквы и цифры."));
        errorMap.put("last_name", List.of("Поле Фамилия может содержать только буквы и цифры."));
        errorMap.put("middle_name", List.of("Поле Отчество может содержать только буквы и цифры."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Поле Имя может содержать только буквы и цифры. (и еще 2 ошибки)", null, errorsDto);
    }

    public static FinishRegistrationResponseDto missingAllFieldsErrorResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("type", List.of("Поле тип обязательно для заполнения."));
        errorMap.put("password", List.of("Поле Пароль обязательно для заполнения."));
        errorMap.put("email", List.of("Поле E-Mail обязательно для заполнения."));
        errorMap.put("phone", List.of("Поле Номер телефона обязательно для заполнения."));
        errorMap.put("first_name", List.of("Поле не заполнено"));
        errorMap.put("last_name", List.of("Поле не заполнено"));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Поле тип обязательно для заполнения. (и еще 5 ошибки)", null, errorsDto);
    }

    public static FinishRegistrationResponseDto missingPhoneErrorResponse() {
        return fieldSpecificErrorResponse("phone", "Поле Номер телефона обязательно для заполнения.");
    }

    public static FinishRegistrationResponseDto duplicatePhoneErrorResponse() {
        return duplicateFieldErrorResponse("phone");
    }

    private static FinishRegistrationResponseDto duplicateFieldErrorResponse(String field) {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put(field, List.of("Пользователь с такими данными уже существует"));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, "Пользователь с такими данными уже существует (и еще 1 ошибка)", null, errorsDto);
    }

    private static FinishRegistrationResponseDto fieldSpecificErrorResponse(String field, String message) {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put(field, List.of(message));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new FinishRegistrationResponseDto(null, message, null, errorsDto);
    }
}

