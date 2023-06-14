package ru.gasworkers.dev.tests.api.registration.regular.check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;
@AllArgsConstructor
 enum CheckRegistrationNegativeCase {
    CLIENT_INVALID_CODE_CASE("Invalid code", CheckRegistrationResponseDto.wrongCodeResponse()),
//    CLIENT_EXPIRED_CODE_CASE("Expired code", CheckRegistrationResponseDto.expiredCodeResponse()), // add test  waiting for one min
    ClIENT_MISSING_CODE_CASE("Missing code", CheckRegistrationResponseDto.missingCodeResponse());

    /*,
    CLIENT_MISSING_PHONE_CASE("Missing phone", CheckRegistrationResponseDto.missingPhoneResponse()),
    CLIENT_DUPLICATE_PHONE_CASE("Duplicate phone", CheckRegistrationResponseDto.duplicatePhoneResponse()),
    CLIENT_MISSING_ALL_FIELDS_CASE("Missing all fields", CheckRegistrationResponseDto.missingAllFieldsResponse())*/




    private final String description;
    private final CheckRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();




    public CheckRegistrationResponseDto getExpectedResponse() {
        return expectedResponse;
    }

    public StartRegistrationRequestDto getStartDto() {
        return complexDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto () {
        switch (this) {
            case CLIENT_INVALID_CODE_CASE :
                return complexDto.toCheckRegistration()
                        .setCode(123456);
            case ClIENT_MISSING_CODE_CASE:
                return complexDto.toCheckRegistration()
                        .setCode(null);
            default:
                throw new IllegalArgumentException("Unknown case: " + this);
        }
    }


}
