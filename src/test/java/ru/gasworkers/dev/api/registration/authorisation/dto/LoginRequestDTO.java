package ru.gasworkers.dev.api.registration.authorisation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String type;
    private String email;
    private String phone;
    private String login;
    private String password;
    @JsonProperty("with_admin")
    private Boolean withAdmin;

    public static LoginRequestDTO newInstance(String type, String email, String phone, String login, String password) {
        return LoginRequestDTO.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .login(login)
                .password(password)
                .build();
    }

    public static LoginRequestDTO asUser(String phone, String password) {
        return LoginRequestDTO.builder()
                .phone(phone)
                .password(password)
                .build();
    }

    public static LoginRequestDTO asAdmin() {
        return LoginRequestDTO.builder()
                .email("admin@gastech.com")
                .password("1234")
                .withAdmin(true)
                .build();
    }
}
