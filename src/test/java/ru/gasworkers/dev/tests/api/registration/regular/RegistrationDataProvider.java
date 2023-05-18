package ru.gasworkers.dev.tests.api.registration.regular;

import org.junit.jupiter.params.provider.Arguments;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationRequestDto;
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

    private static Stream<Arguments> startRegistrationDataProviderPositive() {
        List<String> userRoles = new RegistrationDataProvider().getUserRoles();
        List<Arguments> arguments = new ArrayList<>();
        for (String userRole : userRoles) {
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), true)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), false)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), generateRandomPhone(), null)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), null, true)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), null, false)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, generateRandomEmail(), null, null)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, null, generateRandomPhone(), true)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, null, generateRandomPhone(), false)));
            arguments.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    userRole, null, generateRandomPhone(), null)));
        }

        return arguments.stream();

    }

    private static Stream<Arguments> startRegistrationDataProviderTypeParamMissingNegative() {
        return Stream.of(
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), true)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), false)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), null)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), null, true)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), null, false)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, generateRandomEmail(), null, null)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, null, generateRandomPhone(), true)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, null, generateRandomPhone(), false)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        null, null, generateRandomPhone(), null)));

    }

    private static Stream<Arguments> startRegistrationDataProviderEmailAndPhoneParamMissingNegative() {
        return Stream.of(
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        CLIENT.toString(), null, null, true)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        CLIENT.toString(), null, null, false)),
                Arguments.of(StartRegistrationRequestDto.newInstance(
                        CLIENT.toString(), null, null, null)));

    }

    private static Stream<Arguments> startRegistrationDataProviderEmailValidationNegative() {
        List<String> invalidEmailList = new RegistrationDataProvider().invalidEmail();
        List<Arguments> argumentsList = new ArrayList<>();
        for (String invalidEmail : invalidEmailList) {
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), invalidEmail, null, null)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), invalidEmail, generateRandomPhone(), null)));
        }
        return argumentsList.stream();
    }

    private static Stream<Arguments> startRegistrationDataProviderPhoneValidationNegative() {
        List<String> invalidPhonelList = new RegistrationDataProvider().invalidPhone();
        List<Arguments> argumentsList = new ArrayList<>();
        for (String invalidPhone : invalidPhonelList) {
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), generateRandomEmail(), invalidPhone.toString(), null)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), null, invalidPhone.toString(), null)));
        }
        return argumentsList.stream();
    }

    private static Stream<Arguments> startRegistrationDataProviderPhoneAlreadyExistNegative() {
        List<Arguments> argumentsList = new ArrayList<>();
         {
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), generateRandomEmail(), "77777777777", null)));
             argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                     CLIENT.toString(), generateRandomEmail(), "77777777777", false)));
             argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                     CLIENT.toString(), generateRandomEmail(), "77777777777", true)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), null, "77777777777", null)));
                argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                        CLIENT.toString(), null, "77777777777", false)));
                argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                        CLIENT.toString(), null, "77777777777", true)));
             argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                     CLIENT.toString(), "&&&&&&&&", "77777777777", true)));
             argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                     CLIENT.toString(), "shingelevich@gmail.com", "77777777777", true)));

        }
        return argumentsList.stream();
    }

    private static Stream<Arguments> startRegistrationDataProviderExistingEmailAndPhoneNegative() {
        List<Arguments> argumentsList = new ArrayList<>();
        {
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), "shingelevich@gmail.com", "77777777777", true)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), "shingelevich@gmail.com", "77777777777", false)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    CLIENT.toString(), "shingelevich@gmail.com", "77777777777", null)));
        }
        return argumentsList.stream();
    }

    private static Stream<Arguments> startRegistrationDataProviderNoTypeExistingEmailAndPhoneNegative() {
        List<Arguments> argumentsList = new ArrayList<>();
        {
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    null,"shingelevich@gmail.com", "77777777777", null)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    null,"shingelevich@gmail.com", "77777777777", true)));
            argumentsList.add(Arguments.of(StartRegistrationRequestDto.newInstance(
                    null,"shingelevich@gmail.com", "77777777777", false)));
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

    private static List<String> invalidPhone() {
        List<String> invalidPhone = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ru/gasworkers/dev/tests/registration/usualRegistration/usualClientRegistration/resources/registration/invalidPhoneFormat.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 3) {
                    String email = data[0].trim();
                    invalidPhone.add(email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidPhone;
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
