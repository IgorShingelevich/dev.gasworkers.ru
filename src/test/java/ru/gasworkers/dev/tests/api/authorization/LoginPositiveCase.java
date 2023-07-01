package ru.gasworkers.dev.tests.api.authorization;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.registration.authorisation.dto.LoginRequestDTO;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;


@AllArgsConstructor
enum LoginPositiveCase {
    CLIENT_WITH_EMAIL("Client with email"),
    CLIENT_WITH_PHONE("Client with phone"),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone"),
    LOGIN_ADMIN("Login admin");
    //todo add  client with nickname
    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startDto = complexDto.toStartRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return startDto.setPhone(null);
            case CLIENT_WITH_PHONE:
                return startDto.setEmail(null);
                case CLIENT_WITH_EMAIL_AND_PHONE:
                return startDto.setEmail(null);
            default:
                return startDto;
        }
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkDto = complexDto.toCheckRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return checkDto.setPhone(null);
            case CLIENT_WITH_PHONE:
                return checkDto.setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return checkDto.setEmail(null);
            default:
                return checkDto;
        }
    }

    public FinishRegistrationRequestDto getFinishDto() {
        return complexDto.toFinishRegistration();
    }

    public LoginRequestDTO getLoginDto() {
        LoginRequestDTO loginDto = complexDto.toLogin();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return loginDto.setPhone(null);
            case CLIENT_WITH_PHONE:
                return loginDto.setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return loginDto;
            case LOGIN_ADMIN:
                return LoginRequestDTO.asAdmin();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }


}
