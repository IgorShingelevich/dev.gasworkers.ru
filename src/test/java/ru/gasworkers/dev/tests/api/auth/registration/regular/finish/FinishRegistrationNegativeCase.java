package ru.gasworkers.dev.tests.api.auth.registration.regular.finish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum FinishRegistrationNegativeCase {
    CLIENT_INVALID_EMAIL(
            "Client with invalid email",
            FinishRegistrationResponseDto.invalidEmailErrorResponse(), 422),
    CLIENT_DUPLICATE_EMAIL(
            "Client with duplicate email",
            FinishRegistrationResponseDto.duplicateEmailErrorResponse(), 422),
    CLIENT_MISSING_NAME(
            "Client missing name",
            FinishRegistrationResponseDto.missingNameErrorResponse(), 422),
    CLIENT_INVALID_NAME(
            "Client with invalid name",
            FinishRegistrationResponseDto.invalidNameErrorResponse(), 422),
    CLIENT_MISSING_PHONE(
            "Client missing phone",
            FinishRegistrationResponseDto.missingPhoneErrorResponse(), 422),
    CLIENT_DUPLICATE_PHONE(
            "Client with duplicate phone",
            FinishRegistrationResponseDto.duplicatePhoneErrorResponse(), 422),
    MISSING_PASSWORD(
            "Missing password",
            FinishRegistrationResponseDto.missingPasswordErrorResponse(), 422),
    MISSING_FIRST_NAME(
            "Missing first name",
            FinishRegistrationResponseDto.missingFirstNameErrorResponse(), 422),
    MISSING_LAST_NAME(
            "Missing last name",
            FinishRegistrationResponseDto.missingLastNameErrorResponse(), 422),
    INVALID_GENDER(
            "Invalid gender",
            FinishRegistrationResponseDto.invalidGenderErrorResponse(), 422),
    /*FIELD_SPECIFIC_TO_SELF_EMPLOYED(
            "Field specific to self-employed",
            FinishRegistrationResponseDto.invalidRoleFieldErrorResponse("isHaveContract for client")
    FIELD_SPECIFIC_TO_MASTER(
            "Field specific to master",
            FinishRegistrationResponseDto.invalidRoleFieldErrorResponse("serviceId for client")),*/ // now allowed 200
    MISSING_EMAIL_AND_PHONE(
            "Missing both email and phone",
            FinishRegistrationResponseDto.missingEmailAndPhoneErrorResponse(), 422),
    INVALID_PHONE_FORMAT(
            "Invalid phone format",
            FinishRegistrationResponseDto.invalidPhoneFormatErrorResponse(), 422);

    private final String description;
    @Getter
    private final FinishRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();
    @Getter
    private final int expectedStatusCode;

    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startDto = complexDto.toStartRegistration();
        return startDto.setEmail(null);
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkDto = complexDto.toCheckRegularRegistration();
        return checkDto.setEmail(null);
    }

    public FinishRegistrationRequestDto getFinishDto() {
        FinishRegistrationRequestDto finishDto = complexDto.toFinishRegistration();

        switch (this) {
            case CLIENT_INVALID_EMAIL:
                finishDto.setEmail("invalidemail.com");
                break;
            case CLIENT_DUPLICATE_EMAIL:
                finishDto.setEmail("shingelevich@gmail.com");
                break;
            case CLIENT_MISSING_NAME:
                finishDto.setFirstName(null).setLastName(null);
                break;
            case CLIENT_INVALID_NAME:
                finishDto.setFirstName("123   123");
                break;
            case CLIENT_MISSING_PHONE:
                finishDto.setPhone(null);
                break;
            case CLIENT_DUPLICATE_PHONE:
                finishDto.setPhone("70026442413");
                break;
            case MISSING_PASSWORD:
                finishDto.setPassword(null);
                break;
            case MISSING_FIRST_NAME:
                finishDto.setFirstName(null);
                break;
            case MISSING_LAST_NAME:
                finishDto.setLastName(null);
                break;
            case INVALID_GENDER:
                finishDto.setGender("other");
                break;
            /*case FIELD_SPECIFIC_TO_SELF_EMPLOYED:
                finishDto.setIsHaveContract(true);
                break;
            case FIELD_SPECIFIC_TO_MASTER:
                finishDto.setServiceId(13);
                break;*/ // now allowed 200
            case MISSING_EMAIL_AND_PHONE:
                finishDto.setEmail(null).setPhone(null);
                break;
            case INVALID_PHONE_FORMAT:
                finishDto.setPhone("123ABC");
                break;
            default:
                throw new EnumNotSupportedException(this);
        }
        return finishDto;
    }

    @Override
    public String toString() {
        return description;
    }
}
