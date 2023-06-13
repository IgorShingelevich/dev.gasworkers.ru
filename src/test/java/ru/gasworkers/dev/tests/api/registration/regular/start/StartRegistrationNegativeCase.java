package ru.gasworkers.dev.tests.api.registration.regular.start;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationResponseDto;

@AllArgsConstructor
enum StartRegistrationNegativeCase {
    MISSING_TYPE(
            "Missing type",
            StartRegistrationResponseDto.missingTypeResponse()),
    MISSING_EMAIL_OR_PHONE(
            "Missing email or phone",
            StartRegistrationResponseDto.missingEmailOrPhoneResponse()),
    INVALID_EMAIL(
            "Invalid email",
            StartRegistrationResponseDto.invalidEmailResponse()),
    INVALID_PHONE(
            "Invalid phone",
            StartRegistrationResponseDto.invalidPhoneResponse()),
    PHONE_ALREADY_EXISTS(
            "Phone already exists",
            StartRegistrationResponseDto.phoneAlreadyExistsResponse()),
    EMAIL_AND_PHONE_ALREADY_EXIST(
            "Email and phone already exist",
            StartRegistrationResponseDto.emailAndPhoneAlreadyExistResponse()),
    NO_TYPE_EMAIL_AND_PHONE_ALREADY_EXIST(
            "No type, email, and phone already exist",
            StartRegistrationResponseDto.noTypeEmailAndPhoneAlreadyExistResponse());

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
            case MISSING_TYPE:
                return complexDto.toStartRegistration()
                        .setType(null);
            case MISSING_EMAIL_OR_PHONE:
                return complexDto.toStartRegistration()
                        .setEmail(null)
                        .setPhone(null);
            case INVALID_EMAIL:
                return complexDto.toStartRegistration()
                        .setEmail("invalid_email");
            case INVALID_PHONE:
                return complexDto.toStartRegistration()
                        .setPhone("invalid_phone");
            case PHONE_ALREADY_EXISTS:
                return complexDto.toStartRegistration()
                        .setPhone("70012223344");
            case EMAIL_AND_PHONE_ALREADY_EXIST:
                return complexDto.toStartRegistration()
                        .setEmail("shingelevich@gmail.com")
                        .setPhone("70012223344");
            case NO_TYPE_EMAIL_AND_PHONE_ALREADY_EXIST:
                return complexDto.toStartRegistration()
                        .setType(null)
                        .setEmail("shingelevich@gmail.com")
                        .setPhone("70012223344");
            default:
                throw new IllegalArgumentException("Invalid negative case: " + this);
        }
    }
}
