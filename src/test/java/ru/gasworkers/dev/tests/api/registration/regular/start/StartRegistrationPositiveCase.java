package ru.gasworkers.dev.tests.api.registration.regular.start;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

@AllArgsConstructor
enum StartRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL_ONLY("Client with email only",
            ComplexRegistrationFactory.defaultRandomClient().setPhone(null)),
    CLIENT_WITH_PHONE_ONLY("Client with phone only",
            ComplexRegistrationFactory.defaultRandomClient().setEmail(null)),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone",
            ComplexRegistrationFactory.defaultRandomClient());

    private final String description;
    private final ComplexRegistrationRequestDto complexDto;

    public StartRegistrationRequestDto getStartDto() {
        return complexDto.toStartRegistration();
    }

    @Override
    public String toString() {
        return description;
    }
}
