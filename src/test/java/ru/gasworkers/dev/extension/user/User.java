package ru.gasworkers.dev.extension.user;

import lombok.Builder;
import lombok.Data;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;

@Data
@Builder
public final class User {

    public static User fromComplexDto(ComplexRegistrationRequestDto complexDto) {
        return User.builder()
                .email(complexDto.getEmail())
                .phone(complexDto.getPhone())
                .password(complexDto.getPassword())
                .build();
    }

    private String email, phone, password;

}
