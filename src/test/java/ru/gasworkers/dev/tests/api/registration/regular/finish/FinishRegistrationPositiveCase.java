package ru.gasworkers.dev.tests.api.registration.regular.finish;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

@AllArgsConstructor
enum FinishRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone");

    private final String description;
    private final ComplexRegistrationRequestDto complex = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {
        return complex.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        return complex.toCheckRegistration();
    }

    public FinishRegistrationRequestDto getFinishDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return complex.toFinishRegistration();
            default:
                throw new RuntimeException("Enum with type " + name() + " not supported");
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
