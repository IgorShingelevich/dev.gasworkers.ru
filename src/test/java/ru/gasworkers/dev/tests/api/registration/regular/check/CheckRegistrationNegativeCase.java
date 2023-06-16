package ru.gasworkers.dev.tests.api.registration.regular.check;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.model.apiModel.UserType;

@AllArgsConstructor
 enum CheckRegistrationNegativeCase {
    CLIENT_INVALID_CODE_CASE("Invalid code", CheckRegistrationResponseDto.wrongCodeResponse()),
//    CLIENT_EXPIRED_CODE_CASE("Expired code", CheckRegistrationResponseDto.expiredCodeResponse()), // todo add test  waiting for one min
    ClIENT_MISSING_CODE_CASE("Missing code", CheckRegistrationResponseDto.missingCodeResponse()),
    CLIENT_INVALID_TYPE_CASE("Invalid type", CheckRegistrationResponseDto.invalidTypeResponse()),
    CLIENT_MISSING_TYPE_CASE("Missing type", CheckRegistrationResponseDto.missingTypeResponse()),
    CLIENT_WRONG_TYPE_CASE("Wrong type( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement wrong type
    CLIENT_INVALID_PHONE_CASE("Invalid phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement invalid phone
    CLIENT_DUPLICATE_PHONE_CASE("Duplicate phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement duplicate phone
    CLIENT_NOT_MATCH_PHONE_CASE("Not match phone( the response need to be handled properly)", CheckRegistrationResponseDto.wrongCodeResponse()), // todo implement not match phone
    CLIENT_MISSING_ALL_FIELDS_CASE("Missing all fields( the response need to be handled properly- expected description of all the fields)", CheckRegistrationResponseDto.missingAllFieldsResponse()),
    CLIENT_MISSING_PHONE_CASE("Missing phone( discuss this case)  ", CheckRegistrationResponseDto.missingPhoneResponse()),
    CLIENT_START_MISSING_TYPE_CASE("Missing type in start", CheckRegistrationResponseDto.missingTypeResponse());

    private final String description;
    private final CheckRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();




    public CheckRegistrationResponseDto getExpectedResponse() {
        return expectedResponse;
    }

   /* public StartRegistrationRequestDto getStartDto() {
        return complexDto.toStartRegistration();
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
            case CLIENT_MISSING_PHONE_CASE:
                copyDto.setEmail(null);
                break;
            case CLIENT_START_MISSING_TYPE_CASE:
                copyDto.setType(null);
                break;
            // Add other cases here if needed
            default:
                break;
        }
        return copyDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto () {
        switch (this) {
            case CLIENT_INVALID_CODE_CASE :
                return complexDto.toCheckRegistration()
                        .setCode(123456);
            case ClIENT_MISSING_CODE_CASE:
                return complexDto.toCheckRegistration()
                        .setCode(null);
            case CLIENT_INVALID_TYPE_CASE:
                return complexDto.toCheckRegistration()
                        .setType("invalid");
            case CLIENT_MISSING_TYPE_CASE:
                return complexDto.toCheckRegistration()
                        .setType(null);
            case CLIENT_WRONG_TYPE_CASE:
                return complexDto.toCheckRegistration()
                        .setType(UserType.MASTER.toString());
            case CLIENT_INVALID_PHONE_CASE:
                return complexDto.toCheckRegistration()
                        .setPhone("123");
            case CLIENT_DUPLICATE_PHONE_CASE:
                return complexDto.toCheckRegistration()
                        .setPhone("70012223344"); //already registered
            case CLIENT_NOT_MATCH_PHONE_CASE:
                return complexDto.toCheckRegistration()
                        .setPhone("79992223344"); //not match to start phone but valid and not duplicate
            case CLIENT_MISSING_ALL_FIELDS_CASE:
                return complexDto.toCheckRegistration()
                        .setCode(null)
                        .setPhone(null)
                        .setType(null);
//            complexDto.toStartRegistration().setEmail(null);
            case CLIENT_MISSING_PHONE_CASE:
                return complexDto.toCheckRegistration()
                        .setPhone(null);
                case CLIENT_START_MISSING_TYPE_CASE:
                return complexDto.toCheckRegistration();



            default:
                throw new IllegalArgumentException("Unknown case: " + this);
        }
    }
    @Override
    public String toString() {
        return description;
    }

}
