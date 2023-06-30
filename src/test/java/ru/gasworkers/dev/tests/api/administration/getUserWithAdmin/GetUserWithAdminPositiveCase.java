package ru.gasworkers.dev.tests.api.administration.getUserWithAdmin;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.authorisation.dto.LoginRequestDTO;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum GetUserWithAdminPositiveCase {
    LOGIN_ADMIN("Login admin");
    //todo add  client with nickname
    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public LoginRequestDTO getLoginDto() {
        LoginRequestDTO loginDto = complexDto.toLogin();
        if (this == GetUserWithAdminPositiveCase.LOGIN_ADMIN) {
            return LoginRequestDTO.asAdmin();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }

}
