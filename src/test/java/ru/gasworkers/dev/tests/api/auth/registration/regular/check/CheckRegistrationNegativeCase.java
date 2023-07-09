package ru.gasworkers.dev.tests.api.auth.registration.regular.check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;
import ru.gasworkers.dev.model.apiModel.UserType;

@AllArgsConstructor
enum CheckRegistrationNegativeCase {
    CLIENT_INVALID_CODE("Invalid code", CheckRegistrationResponseDto.wrongCodeResponse()),
    //    CLIENT_EXPIRED_CODE("Expired code", CheckRegistrationResponseDto.expiredCodeResponse()), // todo add test  waiting for one min
    CLIENT_MISSING_CODE("Missing code", CheckRegistrationResponseDto.missingCodeResponse()),
    CLIENT_INVALID_TYPE("Invalid type", CheckRegistrationResponseDto.invalidTypeResponse()),
    CLIENT_MISSING_TYPE("Missing type", CheckRegistrationResponseDto.missingTypeResponse()),
    CLIENT_WRONG_TYPE("Wrong type( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement wrong type
    CLIENT_INVALID_PHONE("Invalid phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement invalid phone
    CLIENT_DUPLICATE_PHONE("Duplicate phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement duplicate phone
    CLIENT_NOT_MATCH_PHONE("Not match phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement not match phone
    CLIENT_MISSING_ALL_FIELDS("Missing all fields( the response need to be handled properly- expected description of all the fields)", CheckRegistrationResponseDto.missingAllFieldsResponse()),
    CLIENT_MISSING_PHONE("Missing phone( discuss this case)  ", CheckRegistrationResponseDto.missingPhoneResponse()),
    CLIENT_START_MISSING_TYPE("Missing type in start", CheckRegistrationResponseDto.missingTypeResponse());
    /*todo
    * {
    "status": 1008,
    "message": "Вы можете повторить запрос через 59 секунд",
    "data": {
        "seconds_left": 59
    }
}
*/

    private final String description;
    @Getter
    private final CheckRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startDto = complexDto.toStartRegistration();
        switch (this) {
            case CLIENT_MISSING_PHONE:
                return startDto.setEmail(null);
            case CLIENT_START_MISSING_TYPE:
                return startDto.setType(null);
            default:
                return startDto;
        }
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkDto = complexDto.toCheckRegularRegistration();
        switch (this) {
            case CLIENT_INVALID_CODE:
                return checkDto.setCode(123456);
            case CLIENT_MISSING_CODE:
                return checkDto.setCode(null);
            case CLIENT_INVALID_TYPE:
                return checkDto.setType("invalid");
            case CLIENT_MISSING_TYPE:
                return checkDto.setType(null);
            case CLIENT_WRONG_TYPE:
                return checkDto.setType(UserType.MASTER.toString());
            case CLIENT_INVALID_PHONE:
                return checkDto.setPhone("123");
            case CLIENT_DUPLICATE_PHONE:
                return checkDto.setPhone("70012223344"); //already registered
            case CLIENT_NOT_MATCH_PHONE:
                return checkDto.setPhone("79992223344"); //not match to start phone but valid and not duplicate
            case CLIENT_MISSING_ALL_FIELDS:
                return checkDto.setCode(null)
                        .setPhone(null)
                        .setType(null);
            case CLIENT_MISSING_PHONE:
                return checkDto.setPhone(null);
            case CLIENT_START_MISSING_TYPE:
                return checkDto;
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
