package ru.gasworkers.dev.tests.api.auth.registration.regular.check;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum CheckRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL_ONLY("Client with email only"),
    CLIENT_WITH_PHONE_ONLY("Client with phone only"),
    CLIENT_WITH_EMAIL_AND_PHONE("Client with email and phone"),
    SELF_EMPLOYED_WITH_EMAIL_ONLY("Self-employed with email only"),
    SELF_EMPLOYED_WITH_PHONE_ONLY("Self-employed with phone only"),
    SELF_EMPLOYED_WITH_EMAIL_AND_PHONE("Self-employed with email and phone");

    private final String description;
    private final ComplexRegistrationRequestDto complexClientDto = ComplexRegistrationFactory.defaultRandomClient();
    private final ComplexRegistrationRequestDto complexSelfEmployedDto = ComplexRegistrationFactory.defaultRandomSelfEmployed();

    public StartRegistrationRequestDto getStartDto() {
        StartRegistrationRequestDto startClientDto = complexClientDto.toStartRegistration();
        StartRegistrationRequestDto startSelfEmployedDto = complexSelfEmployedDto.toStartRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL_ONLY:
                return startClientDto.setPhone(null);
            case CLIENT_WITH_PHONE_ONLY:
                return startClientDto.setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return startClientDto;
            case SELF_EMPLOYED_WITH_EMAIL_ONLY:
                return startSelfEmployedDto.setPhone(null);
            case SELF_EMPLOYED_WITH_PHONE_ONLY:
                return startSelfEmployedDto.setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL_AND_PHONE:
                return startSelfEmployedDto;
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkClientDto = complexClientDto.toCheckRegularRegistration();
        CheckRegistrationRequestDto checkSelfEmployedDto = complexSelfEmployedDto.toCheckRegularRegistration();

        switch (this) {
            case CLIENT_WITH_EMAIL_ONLY:
                return checkClientDto.setPhone(null);
            case CLIENT_WITH_PHONE_ONLY:
                return checkClientDto.setEmail(null);
            case CLIENT_WITH_EMAIL_AND_PHONE:
                return checkClientDto;
            case SELF_EMPLOYED_WITH_EMAIL_ONLY:
                return checkSelfEmployedDto.setPhone(null);
            case SELF_EMPLOYED_WITH_PHONE_ONLY:
                return checkSelfEmployedDto.setEmail(null);
            case SELF_EMPLOYED_WITH_EMAIL_AND_PHONE:
                return checkSelfEmployedDto;
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
 /* @Getter
     private final StartRegistrationRequestDto startDto;
     private final CheckRegistrationRequestDto checkDto;

     CheckRegistrationPositiveCase(String description) {
         this.description = description;
         ComplexRegistrationRequestDto complexClientDto = ComplexRegistrationFactory.defaultRandomClient();
         startDto = complexClientDto.toStartRegistration();
         checkDto = complexClientDto.toCheckRegularRegistration();
     }*/