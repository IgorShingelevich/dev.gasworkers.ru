package ru.gasworkers.dev.tests.api.registration.regular.finish;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum FinishRegistrationNegativeCase {
    CLIENT_MISSING_EMAIL(
            "Client without email",
            FinishRegistrationResponseDto.missingEmailErrorResponse()),
    CLIENT_INVALID_EMAIL(
            "Client with invalid email",
            FinishRegistrationResponseDto.invalidEmailErrorResponse()),
    CLIENT_DUPLICATE_EMAIL(
            "Client with already existing email",
            FinishRegistrationResponseDto.duplicateEmailErrorResponse()),
    CLIENT_MISSING_NAME(
            "Client without first and last name",
            FinishRegistrationResponseDto.missingNameErrorResponse()),
    CLIENT_INVALID_NAME(
            "Client with invalid name",
            FinishRegistrationResponseDto.invalidNameErrorResponse()),
    CLIENT_MISSING_ALL_FIELDS(
            "Client without all data",
            FinishRegistrationResponseDto.missingAllFieldsErrorResponse()),
    CLIENT_MISSING_PHONE(
            "Client without phone",
            FinishRegistrationResponseDto.missingPhoneErrorResponse()),
    CLIENT_DUPLICATE_PHONE(
            "Client with already existing phone",
            FinishRegistrationResponseDto.duplicatePhoneErrorResponse());

    private final String description;
    private final FinishRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();
    public FinishRegistrationResponseDto getExpectedResponse() {
        return expectedResponse;
    }



    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startDto = complexDto.toStartRegistration();
        switch (this) {
            default:
                return startDto.setEmail(null);
        }

    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkDto = complexDto.toCheckRegistration();
        switch (this) {
            default:
                return checkDto.setEmail(null);
        }
    }

    public FinishRegistrationRequestDto getFinishDto() {
        FinishRegistrationRequestDto finishDto = complexDto.toFinishRegistration();

        switch (this) {
            case CLIENT_MISSING_EMAIL:
                return finishDto.setEmail(null);
            case CLIENT_INVALID_EMAIL:
                return finishDto.setEmail("shingelevichgmail.com");
            case CLIENT_DUPLICATE_EMAIL:
                return finishDto.setEmail("shingelevich@gmail.com");
            case CLIENT_MISSING_NAME:
                return finishDto
                        .setFirstName(null)
                        .setLastName(null);
            case CLIENT_INVALID_NAME:
                return finishDto
                        .setFirstName("La la")
                        .setMiddleName("Ла ла")
                        .setLastName("La 123");
            case CLIENT_MISSING_ALL_FIELDS:
                return finishDto
                        .setFirstName(null)
                        .setMiddleName(null)
                        .setLastName(null)
                        .setEmail(null)
                        .setPhone(null);
            case CLIENT_MISSING_PHONE:
                return finishDto
                        .setPhone(null);
            case CLIENT_DUPLICATE_PHONE:
                return finishDto
                        .setPhone("70026442413");
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
