package ru.gasworkers.dev.tests.api.registration.regular.start;

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
            StartRegistrationResponseDto.successResponse("60"));

    private final String description;
    @Getter
    private final StartRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL_ONLY:
                return complexDto.toStartRegistration().setPhone(null);
            case CLIENT_WITH_PHONE_ONLY:
                return complexDto.toStartRegistration().setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
            case CLIENT_VALID_EMAIL_WITH_INVALID_PHONE:
                return complexDto.toStartRegistration().setPhone("invalid_phone");
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
