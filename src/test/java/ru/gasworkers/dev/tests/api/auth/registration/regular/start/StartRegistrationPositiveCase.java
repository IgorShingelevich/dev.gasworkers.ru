package ru.gasworkers.dev.tests.api.auth.registration.regular.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationResponseDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum StartRegistrationPositiveCase {
    CLIENT_WITH_EMAIL_ONLY("Client with email only",
            StartRegistrationResponseDto.successResponse("60")),
    CLIENT_WITH_PHONE_ONLY("Client with phone only",
            StartRegistrationResponseDto.successResponse("60")),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone",
            StartRegistrationResponseDto.successResponse("60")),
    CLIENT_VALID_EMAIL_WITH_INVALID_PHONE(
            "Client with valid Email invalid Phone",
            StartRegistrationResponseDto.successResponse("60")),
    SELF_EMPLOYED_WITH_EMAIL_ONLY("SelfEmployed with email only",
            StartRegistrationResponseDto.successResponse("60")),
    SELF_EMPLOYED_WITH_PHONE_ONLY("SelfEmployed with phone only",
            StartRegistrationResponseDto.successResponse("60")),
    SELF_EMPLOYED_WITH_EMAIL_AND_PHONE("SelfEmployed with email and phone",
            StartRegistrationResponseDto.successResponse("60")),
    SELF_EMPLOYED_VALID_EMAIL_WITH_INVALID_PHONE(
            "SelfEmployed with valid Email invalid Phone",
            StartRegistrationResponseDto.successResponse("60"));

    private final String description;
    @Getter
    private final StartRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexClientDto = ComplexRegistrationFactory.defaultRandomClient();
    private final ComplexRegistrationRequestDto complexSelfEmployedDto = ComplexRegistrationFactory.defaultRandomSelfEmployed();

    public StartRegistrationRequestDto getStartDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL_ONLY:
                return complexClientDto.toStartRegistration().setPhone(null);
            case CLIENT_WITH_PHONE_ONLY:
                return complexClientDto.toStartRegistration().setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return complexClientDto.toStartRegistration();
            case CLIENT_VALID_EMAIL_WITH_INVALID_PHONE:
                return complexClientDto.toStartRegistration().setPhone("invalid_phone");
            case SELF_EMPLOYED_WITH_EMAIL_ONLY:
                return complexSelfEmployedDto.toStartRegistration().setPhone(null);
            case SELF_EMPLOYED_WITH_PHONE_ONLY:
                return complexSelfEmployedDto.toStartRegistration().setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL_AND_PHONE:
                return complexSelfEmployedDto.toStartRegistration();
            case SELF_EMPLOYED_VALID_EMAIL_WITH_INVALID_PHONE:
                return complexSelfEmployedDto.toStartRegistration().setPhone("invalid_phone");
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
