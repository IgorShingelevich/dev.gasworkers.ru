package ru.gasworkers.dev.api.registration.regular.dto.start;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

        private Map<String, List<String>> errors = new HashMap<>();
        @JsonAnySetter
        public void addError(String key, List<String> value) {
            errors.put(key, value);
        }

    }
    public static StartRegistrationResponseDto successResponse(String secondsLeft) {
        return new StartRegistrationResponseDto(0, "Успешная регистрация", new DataDto(secondsLeft), null);
    }

    public static StartRegistrationResponseDto missingTypeResponse() {
        return fieldRequiredErrorResponse("type", "Поле тип обязательно для заполнения.");
    }

    public static StartRegistrationResponseDto missingEmailOrPhoneResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("email", List.of("Поле E-Mail обязательно для заполнения, когда Номер телефона не указано."));
        errorMap.put("phone", List.of("Поле Номер телефона обязательно для заполнения, когда E-Mail не указано."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new StartRegistrationResponseDto(null, "Поле E-Mail обязательно для заполнения, когда Номер телефона не указано. (и еще 1 ошибка)", null, errorsDto);
    }

    public static StartRegistrationResponseDto invalidEmailResponse() {
        return fieldRequiredErrorResponse("email", "Поле E-Mail должно быть действительным электронным адресом.");
    }

    public static StartRegistrationResponseDto validEmailWithInvalidPhoneResponse(String secondsLeft) {
        return new StartRegistrationResponseDto(0, "Успешная регистрация", new DataDto(secondsLeft), null);
    }

    public static StartRegistrationResponseDto phoneAlreadyExistsResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("phone", List.of("Такое значение поля Номер телефона уже существует."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new StartRegistrationResponseDto(null, "Такое значение поля Номер телефона уже существует.", null, errorsDto);
    }

    public static StartRegistrationResponseDto emailAndPhoneAlreadyExistResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("email", List.of("Такое значение поля E-Mail уже существует."));
        errorMap.put("phone", List.of("Такое значение поля Номер телефона уже существует."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new StartRegistrationResponseDto(null, "Такое значение поля E-Mail уже существует. (и еще 1 ошибка)", null, errorsDto);
    }

    public static StartRegistrationResponseDto noTypeEmailAndPhoneAlreadyExistResponse() {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("type", List.of("Поле тип обязательно для заполнения."));
        errorMap.put("email", List.of("Такое значение поля E-Mail уже существует."));
        errorMap.put("phone", List.of("Такое значение поля Номер телефона уже существует."));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new StartRegistrationResponseDto(null, "Поле тип обязательно для заполнения. (и еще 2 ошибки)", null, errorsDto);
    }

    private static StartRegistrationResponseDto fieldRequiredErrorResponse(String field, String message) {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put(field, List.of(message));
        ErrorsDto errorsDto = new ErrorsDto(errorMap);
        return new StartRegistrationResponseDto(null, message, null, errorsDto);
    }

    public static StartRegistrationResponseDto invalidPhoneResponse() {
        return fieldRequiredErrorResponse("phone", "Поле Номер телефона должно быть действительным номером телефона.");
    }
}
