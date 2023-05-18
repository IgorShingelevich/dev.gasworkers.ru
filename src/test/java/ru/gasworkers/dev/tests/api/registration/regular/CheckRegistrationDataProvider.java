package ru.gasworkers.dev.tests.api.registration.regular;

import org.junit.jupiter.params.provider.Arguments;
import ru.gasworkers.dev.api.registration.dto.registration.CheckRegularRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationRequestDto;

import java.util.stream.Stream;

public class CheckRegistrationDataProvider {
    static Integer code = 123456;

    private static Stream<Arguments> checkRegistrationDataProviderPositive(StartRegistrationRequestDto startRequestDto) {
        String type = startRequestDto.getType();
        String email = startRequestDto.getEmail();
        String phone = startRequestDto.getPhone();

        return Stream.of(
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, type, email, phone)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, null, email, phone)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, type, null, phone)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, type, email, null)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, null, null, phone)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, null, email, null)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, type, null, null)),
                Arguments.of(CheckRegularRegistrationRequestDto.newInstance(
                        code, null, null, null)));

    }

   /* public static Stream<Arguments> checkRegistrationDataProviderPositive() {
        StartRegistrationRequestDto startRequestDto = StartRegistrationRequestDto.builder()
                .type("exampleType")
                .email("exampleEmail")
                .phone("examplePhone")
                .isPhoneSend(true)
                .build();

        return checkRegistrationDataProviderPositive(startRequestDto);
    }*/
}
