package ru.gasworkers.dev.api.auth.registration.regular.dto.check;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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

    public static CheckRegistrationRequestDto newInstance(Integer code, String type, String email, String phone) {
        return CheckRegistrationRequestDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .code(code)
                .build();
    }

}
