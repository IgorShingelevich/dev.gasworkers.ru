package ru.gasworkers.dev.tests.api.registration.regular.finish;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

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


  /*  public StartRegistrationRequestDto getStartDto() {
        return complexDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        return complexDto.toCheckRegistration();
    }*/

    public StartRegistrationRequestDto getStartDto() {
        ComplexRegistrationRequestDto copyDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            copyDto = objectMapper.readValue(objectMapper.writeValueAsString(complexDto), ComplexRegistrationRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a deep copy of complexDto", e);
        }

        switch (this) {

            default:
                copyDto.setEmail(null);
                break;
        }

        return copyDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        ComplexRegistrationRequestDto copyDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            copyDto = objectMapper.readValue(objectMapper.writeValueAsString(complexDto), ComplexRegistrationRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a deep copy of complexDto", e);
        }

        switch (this) {

            default:
                copyDto.setEmail(null);
                break;
        }

        return copyDto.toCheckRegistration();
    }

    public FinishRegistrationRequestDto getFinishDto() {
        ComplexRegistrationRequestDto copyDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            copyDto = objectMapper.readValue(objectMapper.writeValueAsString(complexDto), ComplexRegistrationRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a deep copy of complexDto", e);
        }

        switch (this) {
            case CLIENT_MISSING_EMAIL:
                return copyDto.toFinishRegistration()
                        .setEmail(null);
            case CLIENT_INVALID_EMAIL:
                return copyDto.toFinishRegistration()
                        .setEmail("lalala.ru");
            case CLIENT_DUPLICATE_EMAIL:
                return complexDto.toFinishRegistration()
                        .setEmail("shingelevich@gmail.com");
            case CLIENT_MISSING_NAME:
                return complexDto.toFinishRegistration()
                        .setFirstName(null)
                        .setLastName(null);
            case CLIENT_INVALID_NAME:
                return complexDto.toFinishRegistration()
                        .setFirstName("La la")
                        .setMiddleName("Ла ла")
                        .setLastName("La 123");
            case CLIENT_MISSING_ALL_FIELDS:
                return new FinishRegistrationRequestDto();
            case CLIENT_MISSING_PHONE:
                return complexDto.toFinishRegistration()
                        .setPhone(null);
            case CLIENT_DUPLICATE_PHONE:
                return complexDto.toFinishRegistration()
                        .setPhone("70026442413");
            default:
                throw new RuntimeException("Enum with type " + name() + " not supported");
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
