package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import org.junit.jupiter.params.provider.Arguments;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.stream.Stream;

public class Set1RegistrationDataProvider {
    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }

    static Stream<Arguments> registrationParametersDataProvider() {
        return Stream.of(
                // All combinations with phone (including empty string)
                Arguments.of(1, "client", generateRandomEmail(), generateRandomPhone(), true),
                Arguments.of(2, "client", generateRandomEmail(), generateRandomPhone(), false),
                Arguments.of(3, "client", generateRandomEmail(), generateRandomPhone(), null),
                Arguments.of(4, "client", generateRandomEmail(), "", true),
                Arguments.of(5, "client", generateRandomEmail(), "", false),
                Arguments.of(6, "client", generateRandomEmail(), "", null),
                // All combinations with email (including empty string)
                Arguments.of(7, "client", "", generateRandomPhone(), true),
                Arguments.of(8, "client", "", generateRandomPhone(), false),
                Arguments.of(9, "client", "", generateRandomPhone(), null),
                // All combinations with phone as null
                Arguments.of(10, "client", generateRandomEmail(), null, true),
                Arguments.of(11, "client", generateRandomEmail(), null, false),
                Arguments.of(12, "client", generateRandomEmail(), null, null),
                // All combinations with email as null
                Arguments.of(13, "client", null, generateRandomPhone(), true),
                Arguments.of(14, "client", null, generateRandomPhone(), false),
                Arguments.of(15, "client", null, generateRandomPhone(), null),
                // All combinations without phone
                Arguments.of(16, "client", generateRandomEmail(), "", true),
                Arguments.of(17, "client", generateRandomEmail(), "", false),
                Arguments.of(18, "client", generateRandomEmail(), "", null),
                Arguments.of(19, "client", "", "", true),
                Arguments.of(20, "client", "", "", false),
                Arguments.of(21, "client", "", "", null),
                // All combinations without email
                Arguments.of(22, "client", "", generateRandomPhone(), true),
                Arguments.of(23, "client", "", generateRandomPhone(), false),
                Arguments.of(24, "client", "", generateRandomPhone(), null),
                Arguments.of(25, "client", "", "", true),
                Arguments.of(26, "client", "", "", false),
                Arguments.of(27, "client", "", "", null),
                // All combinations without phone and isPhoneSend
                Arguments.of(28, "client", generateRandomEmail(), "", null),
                Arguments.of(29, "client", "", "", null),
                // All combinations without email and isPhoneSend
                Arguments.of(30, "client", "", generateRandomPhone(), null),
                Arguments.of(31, "client", "", "", null),
                // All combinations without phone, email, and isPhoneSend
                Arguments.of(32, "client", "", "", null)
        );
    }
}
