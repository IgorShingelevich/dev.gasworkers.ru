package ru.gasworkers.dev.extension.user;

import lombok.Builder;
import lombok.Data;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;

@Data
@Builder
public final class User {

    private String email, phone, password, firstName, lastName, middleName, fullName;

    public static User fromComplexDto(ComplexRegistrationRequestDto complexDto) {
        return User.builder()
                .email(complexDto.getEmail())
                .phone(complexDto.getPhone())
                .password(complexDto.getPassword())
                .firstName(complexDto.getFirstName())
                .lastName(complexDto.getLastName())
                .middleName(complexDto.getMiddleName())
                .fullName(complexDto.getFullName())
                .build();
    }

}
