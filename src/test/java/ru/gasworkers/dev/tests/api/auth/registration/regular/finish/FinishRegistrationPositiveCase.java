package ru.gasworkers.dev.tests.api.auth.registration.regular.finish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum FinishRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL(
            "Client with email"),
    CLIENT_ONLY_PHONE(
            "Client missing email, only phone"),
    CLIENT_WITH_PHONE(
            "Client with phone"),
    SELF_EMPLOYED_WITH_EMAIL(
            "Self-employed with email"),
    SELF_EMPLOYED_WITH_PHONE(
            "Self-employed with phone");

    private final String description;
    @Getter
    private final int expectedStatusCode = 200;
    private final ComplexRegistrationRequestDto complexClientDto = ComplexRegistrationFactory.defaultRandomClient();
    private final ComplexRegistrationRequestDto complexSelfEmployedDto = ComplexRegistrationFactory.defaultRandomSelfEmployed();

    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startClientDto = complexClientDto.toStartRegistration();
        StartRegistrationRequestDto startSelfEmployedDto = complexSelfEmployedDto.toStartRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return startClientDto.setPhone(null);
            case CLIENT_WITH_PHONE:
            case CLIENT_ONLY_PHONE:
                return startClientDto.setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL:
                return startSelfEmployedDto.setPhone(null);
            case SELF_EMPLOYED_WITH_PHONE:
                return startSelfEmployedDto.setEmail(null);
            default:
                return startClientDto;
        }
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkClientDto = complexClientDto.toCheckRegularRegistration();
        CheckRegistrationRequestDto checkSelfEmployedDto = complexSelfEmployedDto.toCheckRegularRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return checkClientDto.setPhone(null);
            case CLIENT_WITH_PHONE:
            case CLIENT_ONLY_PHONE:
                return checkClientDto.setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL:
                return checkSelfEmployedDto.setPhone(null);
            case SELF_EMPLOYED_WITH_PHONE:
                return checkSelfEmployedDto.setEmail(null);
            default:
                return checkClientDto;
        }
    }

    public FinishRegistrationRequestDto getFinishDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL:
            case CLIENT_WITH_PHONE:
                return complexClientDto.toFinishRegistration();
            case CLIENT_ONLY_PHONE:
                return complexClientDto.toFinishRegistration().setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL:
            case SELF_EMPLOYED_WITH_PHONE:
                return complexSelfEmployedDto.toFinishRegistration();
            default:
                throw new EnumNotSupportedException(this);
        }
    }


    @Override
    public String toString() {
        return description;
    }

}
