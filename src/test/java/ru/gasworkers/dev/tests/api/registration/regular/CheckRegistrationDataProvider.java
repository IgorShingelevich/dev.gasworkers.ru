package ru.gasworkers.dev.tests.api.registration.regular;

import org.junit.jupiter.params.provider.Arguments;
import ru.gasworkers.dev.api.registration.dto.registration.CheckRegularRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CheckRegistrationDataProvider {
    static Integer code = 111111;

    public static Stream<Arguments> checkRegistrationDataProviderPositive() {
        RegistrationDataProvider registrationDataProvider = new RegistrationDataProvider();
        List<Arguments> arguments = new ArrayList<>();

        String userRole = registrationDataProvider.getUserRoles().get(0);
        String email = registrationDataProvider.generateRandomClientEmail();
        String phone = registrationDataProvider.generateRandomClientPhone();

        arguments.add(Arguments.of(
                StartRegistrationRequestDto.newInstance(userRole, email, phone, true)
        ));

        return arguments.stream();
    }


    public static Stream<Arguments> checkRegistrationDataProviderAllRoles() {
        RegistrationDataProvider registrationDataProvider = new RegistrationDataProvider();
        List<String> userRoles = registrationDataProvider.getUserRoles();
        List<Arguments> arguments = new ArrayList<>();

        for (String userRole : userRoles) {
            String email = registrationDataProvider.generateRandomClientEmail();
            String phone = registrationDataProvider.generateRandomClientPhone();

            arguments.add(Arguments.of(
                    StartRegistrationRequestDto.newInstance(userRole, email, phone, true)
            ));
        }

        return arguments.stream();
    }



    public static Stream<Arguments> checkRegistrationDataProviderPositiveV2() {
        StartRegistrationRequestDto startRequestDto = new StartRegistrationRequestDto();


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

}
