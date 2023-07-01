package ru.gasworkers.dev.api.auth.registration.authorisation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {
    private Integer status;
    private String message;
    private DataDto data;
    private Errors errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        private String token;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Errors {
        private LoginError login;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginError {
        private String[] messages;
    }

    public static LoginResponseDTO successResponse(String token) {
        DataDto dataDto = new DataDto(token);
        return new LoginResponseDTO(0, "Auth success", dataDto, null);
        /*// Example usage after registration
        String token = "YOUR_TOKEN_HERE";
        LoginResponseDTO response = LoginResponseDTO.successResponse(token);
        */
    }

    public static LoginResponseDTO unauthenticatedResponse() {
        return new LoginResponseDTO(null, "Unauthenticated.", null, null);
    }

    public static LoginResponseDTO wrongDataResponse() {
        String[] messages = {"Неверные данные для входа"};
        LoginError loginError = new LoginError(messages);
        Errors errors = new Errors(loginError);
        return new LoginResponseDTO(1004, "Неверные данные для входа", null, errors);
    }
}
