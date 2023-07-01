package ru.gasworkers.dev.tests.api.administration.getUserWithAdmin;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum GetUserWithAdminPositiveCase {
    LOGIN_ADMIN("Login admin");
    //todo add  client with nickname
    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public LoginRequestDto getLoginDto() {
        LoginRequestDto loginDto = complexDto.toLogin();
        if (this == GetUserWithAdminPositiveCase.LOGIN_ADMIN) {
            return LoginRequestDto.asAdmin();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }

}
