package ru.gasworkers.dev.tests.api.registration.regular.finish;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum FinishRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL(
            "Client with email"),
    CLIENT_WITH_PHONE(
            "Client with phone");


    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {

        StartRegistrationRequestDto startDto = complexDto.toStartRegistration();
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return startDto.setPhone(null);
            case CLIENT_WITH_PHONE:
                return startDto.setEmail(null);
            default:
                return startDto;
        }
    }

    public CheckRegistrationRequestDto getCheckDto() {
        CheckRegistrationRequestDto checkDto = complexDto.toCheckRegistration();


        switch (this) {
            case CLIENT_WITH_EMAIL:
                return checkDto.setPhone(null);
            case CLIENT_WITH_PHONE:
               return checkDto.setEmail(null);
            default:
              return checkDto;
        }
    }

    public FinishRegistrationRequestDto getFinishDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return complexDto.toFinishRegistration();
            case CLIENT_WITH_PHONE:
                return complexDto.toFinishRegistration();
            default:
                throw new EnumNotSupportedException(this);
        }
    }


    @Override
    public String toString() {
        return description;
    }

}
