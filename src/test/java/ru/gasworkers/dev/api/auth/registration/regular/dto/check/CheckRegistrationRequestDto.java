package ru.gasworkers.dev.api.auth.registration.regular.dto.check;

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
public class CheckRegistrationRequestDto {

    private Integer code;
    private String type, email, phone;

    public static CheckRegistrationRequestDto newInstanceRegularRegistration(Integer code, String type, String email, String phone) {
        return CheckRegistrationRequestDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .code(code)
                .build();
    }

}
