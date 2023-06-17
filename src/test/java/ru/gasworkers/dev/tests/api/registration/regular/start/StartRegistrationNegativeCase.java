package ru.gasworkers.dev.tests.api.registration.regular.start;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationResponseDto;

@AllArgsConstructor
enum StartRegistrationNegativeCase {
    CLIENT_MISSING_TYPE(
            "Missing type",
            StartRegistrationResponseDto.missingTypeResponse()),
    CLIENT_MISSING_EMAIL_OR_PHONE(
            "Missing email or phone",
            StartRegistrationResponseDto.missingEmailOrPhoneResponse()),
    CLIENT_INVALID_EMAIL(
            "Invalid email",
            StartRegistrationResponseDto.invalidEmailResponse()),

    CLIENT_PHONE_ALREADY_EXISTS(
            "Phone already exists",
            StartRegistrationResponseDto.phoneAlreadyExistsResponse()),
    CLIENT_EMAIL_AND_PHONE_ALREADY_EXIST(
            "Email and phone already exist",
            StartRegistrationResponseDto.emailAndPhoneAlreadyExistResponse()),
    CLIENT_NO_TYPE_EMAIL_AND_PHONE_ALREADY_EXIST(
            "No type, email, and phone already exist",
            StartRegistrationResponseDto.noTypeEmailAndPhoneAlreadyExistResponse()),
    CLIENT_INVALID_PHONE(
            "Invalid phone",
            StartRegistrationResponseDto.invalidPhoneResponse());


    private final String description;
    private final StartRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationResponseDto getExpectedResponse() {
        return expectedResponse;
    }

    public String getDescription() {
        return description;
    }

    public StartRegistrationRequestDto getStartDto() {
        switch (this) {
            case CLIENT_MISSING_TYPE:
                return complexDto.toStartRegistration()
                        .setType(null);
            case CLIENT_MISSING_EMAIL_OR_PHONE:
                return complexDto.toStartRegistration()
                        .setEmail(null)
                        .setPhone(null);
            case CLIENT_INVALID_EMAIL:
                return complexDto.toStartRegistration()
                        .setEmail("invalid_email");
            case CLIENT_PHONE_ALREADY_EXISTS:
                return complexDto.toStartRegistration()
                        .setPhone("70012223344");
            case CLIENT_EMAIL_AND_PHONE_ALREADY_EXIST:
                return complexDto.toStartRegistration()
                        .setEmail("shingelevich@gmail.com")
                        .setPhone("70012223344");
            case CLIENT_NO_TYPE_EMAIL_AND_PHONE_ALREADY_EXIST:
                return complexDto.toStartRegistration()
                        .setType(null)
                        .setEmail("shingelevich@gmail.com")
                        .setPhone("70012223344");
            default:
                throw new IllegalArgumentException("Invalid negative case: " + this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
