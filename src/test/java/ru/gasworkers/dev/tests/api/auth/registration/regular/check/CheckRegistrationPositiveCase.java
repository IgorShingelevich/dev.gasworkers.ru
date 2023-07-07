package ru.gasworkers.dev.tests.api.auth.registration.regular.check;

import lombok.Getter;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

enum CheckRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL_ONLY("Client with email only"),
    CLIENT_WITH_PHONE_ONLY("Client with phone only"),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone");

    private final String description;
    @Getter
    private final StartRegistrationRequestDto startDto;
    private final CheckRegistrationRequestDto checkDto;

    CheckRegistrationPositiveCase(String description) {
        this.description = description;
        ComplexRegistrationRequestDto complex = ComplexRegistrationFactory.defaultRandomClient();
        startDto = complex.toStartRegistration();
        checkDto = complex.toCheckRegularRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL_ONLY:
                return checkDto.setPhone(null);
            case CLIENT_WITH_PHONE_ONLY:
                return checkDto.setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return checkDto;
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }


}
