package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

import org.junit.jupiter.params.provider.Arguments;
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ru.gasworkers.dev.model.apiModel.UserType.CLIENT;

public class RegistrationDataProvider {

    static File
            startRegistration200 = new File("src/test/resources/api/registration/regular/start/startRegistration200.json"),
            startRegistrationNoEmailOrPhone422 = new File("src/test/resources/api/registration/regular/start/negative/startRegistrationNoEmailOrPhone422.json"),
            startRegistrationNoUserType422 = new File("src/test/resources/api/registration/regular/start/negative/startRegistrationNoUserType422.json"),
            startRegistrationEmailValidation422 = new File("src/test/resources/api/registration/regular/start/negative/startRegistrationEmailValidation422.json"),
            checkRegistrationCode200 = new File("src/test/resources/api/registration/regular/check/checkRegistrationCode200.json");

    private static Stream<Arguments> checkRegistrationDataProviderPositive() {
        Stream<Arguments> startRegistrationData = startRegistrationDataProviderPositive();
        List<Arguments> arguments = new ArrayList<>();

        startRegistrationData.forEach(startRegistrationArgs -> {
            arguments.add(Arguments.of(startRegistrationArgs.get()[0], checkRegistrationCode200));
        });
        System.out.println("arguments = " + arguments);

        return arguments.stream();
    }


    private static Stream<Arguments> startRegistrationDataProviderPositive() {
        List<String> userRoles = new RegistrationDataProvider().getUserRoles();
        List<Arguments> arguments = new ArrayList<>();
        for (String userRole : userRoles) {
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), true, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), false, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), null, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, true, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, false, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, null, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), true, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), false, startRegistration200)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), null, startRegistration200)));
        }

        return arguments.stream();

    }

    private static Stream<Arguments> startRegistrationDataProviderParamSetNegative() {
        return Stream.of(
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, true, startRegistrationNoEmailOrPhone422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, false, startRegistrationNoEmailOrPhone422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, null, startRegistrationNoEmailOrPhone422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), true, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), false, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), null, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, true, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, false, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, null, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), true, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), false, startRegistrationNoUserType422)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), null, startRegistrationNoUserType422)));

    }

    private static Stream<Arguments> startRegistrationDataProviderEmailValidationNegative() {
        List<String> invalidEmailList = new RegistrationDataProvider().invalidEmail();
        List<Arguments> argumentsList = new ArrayList<>();
        for (String invalidEmail : invalidEmailList) {
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, null, null, startRegistrationEmailValidation422)));
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, generateRandomPhone(), null, startRegistrationEmailValidation422)));
        }
        return argumentsList.stream();
    }

//    private static Stream<Arguments> checkCodeDataProviderPositive() {
//    }

    private List<String> getUserRoles() {
        List<String> userRoles = new ArrayList<>();
        userRoles.add(UserType.CLIENT.toString());
        userRoles.add(UserType.MASTER.toString());
        userRoles.add(UserType.SERVICE.toString());
        return userRoles;
    }

    private List<String> invalidEmail() {
        List<String> invalidEmail = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ru/gasworkers/dev/tests/registration/usualRegistration/usualClientRegistration/resources/registration/invalidEmailFormat.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 3) {
                    String email = data[0].trim();
                    invalidEmail.add(email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return invalidEmail;
    }

    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }
}
