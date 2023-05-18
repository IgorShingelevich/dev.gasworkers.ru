package ru.gasworkers.dev.api.registration.dto.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRegularRegistrationRequestDto {

    private Integer code;
    private String type, email, phone;

    public static CheckRegularRegistrationRequestDto newInstance(Integer code, String type, String email, String phone) {
        return CheckRegularRegistrationRequestDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .code(code)
                .build();
    }

    public static CheckRegularRegistrationRequestDto fromStartRegistration(Integer code, StartRegistrationRequestDto startRegistration) {
        return CheckRegularRegistrationRequestDto.builder()
                .code(code)
                .type(startRegistration.getType())
                .email(startRegistration.getEmail())
                .phone(startRegistration.getPhone())
                .build();
    }

}
