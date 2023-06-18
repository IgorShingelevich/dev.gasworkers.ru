package ru.gasworkers.dev.api.registration.authorisation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class LoginRequestDTO {
    private String userType;
    private String email;
    private String phone;
    private String login;
    private String password;

    public static LoginRequestDTO newInstance(String userType, String email, String phone, String login, String password) {
        return LoginRequestDTO.builder()
                .userType(userType)
                .email(email)
                .phone(phone)
                .login(login)
                .password(password)
                .build();
    }
}
