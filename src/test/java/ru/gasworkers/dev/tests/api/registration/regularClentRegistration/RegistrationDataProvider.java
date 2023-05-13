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

    static File expectedResponse = new File("src/test/resources/api/registration/startRegistration200Response.json");

    public static Stream<Arguments> registrationDataProviderPositive() {
        List<String> userRoles = new RegistrationDataProvider().getUserRoles();
        List<Arguments> arguments = new ArrayList<>();
        for (String userRole : userRoles) {
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), true, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), false, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), null, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, true, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, false, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, generateRandomEmail(), null, null, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), true, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), false, expectedResponse)));
            arguments.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    userRole, null, generateRandomPhone(), null, expectedResponse)));
        }

        return arguments.stream();

    }

    private static Stream<Arguments> registrationDataProviderParamSetNegative() {
        return Stream.of(
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, true, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, false, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, null, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json")))
        );
    }

    private static Stream<Arguments> registrationDataProviderEmailValidationNegative() {
        List<String> invalidEmailList = new RegistrationDataProvider().invalidEmail();
        List<Arguments> argumentsList = new ArrayList<>();
        for (String invalidEmail : invalidEmailList) {
            File expectedResponseFile = new File("src/test/resources/api/registration/negativeResponse/emailValidation422.json");
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, null, null, expectedResponseFile)));
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, generateRandomPhone(), null, expectedResponseFile)));
        }
        return argumentsList.stream();
    }

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
