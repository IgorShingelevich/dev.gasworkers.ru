package ru.gasworkers.dev.tests.api.registration.regular;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.dto.registration.regular.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.dto.registration.regular.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationRequestDto;

@AllArgsConstructor
public enum ComplexRegistrationCase {
    // CLIENT
    CLIENT_WITH_EMAIL_ONLY("Client with email only",
            ComplexRegistrationFactory.defaultRandomClient().setPhone(null)),
    CLIENT_WITH_PHONE_ONLY("Client with phone only",
            ComplexRegistrationFactory.defaultRandomClient().setEmail(null)),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone",
            ComplexRegistrationFactory.defaultRandomClient());
    // MASTER

    private final String description;
    private final ComplexRegistrationRequestDto complexDto;

    public StartRegistrationRequestDto getStartRequest() {
        return complexDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckRequest() {
        return complexDto.toCheckRegistration();
    }

    public FinishRegistrationRequestDto getFinishRequest() {
        return complexDto.toFinishRegistration();
    }

    @Override
    public String toString() {
        return description;
    }

}
