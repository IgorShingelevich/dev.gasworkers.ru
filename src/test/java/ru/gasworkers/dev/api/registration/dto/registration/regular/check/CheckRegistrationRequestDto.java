package ru.gasworkers.dev.api.registration.dto.registration.regular.check;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static CheckRegistrationRequestDto fromStartRegistration(Integer code, StartRegistrationRequestDto startRegistration) {
        return CheckRegistrationRequestDto.builder()
                .code(code)
                .type(startRegistration.getType())
                .email(startRegistration.getEmail())
                .phone(startRegistration.getPhone())
                .build();
    }

}
