package ru.gasworkers.dev.tests.api.registration.regular.finish;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

@AllArgsConstructor
enum FinishRegistrationNegativeCase {
    // CLIENT
    CLIENT_WITHOUT_EMAIL(
            "Client without email",
            // ToDo change expected response ---> negative
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITH_INVALID_EMAIL(
            "Client with invalid email",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITH_ALREADY_EXIST_EMAIL(
            "Client with already exist email",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITHOUT_FIRST_AND_LASTNAME(
            "Client without firstname and lastname",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITH_INVALID_FIO(
            "Client with invalid fio",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITHOUT_ALL(
            "Client without all data",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITHOUT_PHONE(
            "Client without phone",
            FinishRegistrationResponseDto.successResponse()),
    CLIENT_WITH_ALREADY_EXIST_PHONE(
            "Client with already exist phone",
            FinishRegistrationResponseDto.successResponse());

    private final String description;
    private final FinishRegistrationResponseDto expectedResponse;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();

    public StartRegistrationRequestDto getStartDto() {
        return complexDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        return complexDto.toCheckRegistration();
    }

    public FinishRegistrationRequestDto getFinishDto() {
        switch (this) {
            case CLIENT_WITHOUT_EMAIL:
                return complexDto.toFinishRegistration()
                        .setEmail(null);
            case CLIENT_WITH_INVALID_EMAIL:
                return complexDto.toFinishRegistration()
                        .setEmail("lalala.ru");
            case CLIENT_WITH_ALREADY_EXIST_EMAIL:
                return complexDto.toFinishRegistration()
                        .setEmail("shingelevich@gmail.com");
            case CLIENT_WITHOUT_FIRST_AND_LASTNAME:
                return complexDto.toFinishRegistration()
                        .setFirstName(null)
                        .setLastName(null);
            case CLIENT_WITH_INVALID_FIO:
                return complexDto.toFinishRegistration()
                        .setFirstName("La la")
                        .setMiddleName("Ла ла")
                        .setLastName("La 123");
            case CLIENT_WITHOUT_ALL:
                return new FinishRegistrationRequestDto();
            case CLIENT_WITHOUT_PHONE:
                return complexDto.toFinishRegistration()
                        .setPhone(null);
            case CLIENT_WITH_ALREADY_EXIST_PHONE:
                return complexDto.toFinishRegistration()
                        .setPhone("89855469239");
            default:
                throw new RuntimeException("Enum with type " + name() + " not supported");
        }
    }

}
