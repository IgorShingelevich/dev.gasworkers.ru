package ru.gasworkers.dev.tests.apiTest.helperApiTest;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
public class StartRegistrationTestCasesProvider {
    public static Stream<Arguments> registrationTestCases(String email, String phone) {
        return Stream.of(
                Arguments.of("Master", email, phone, false),
                Arguments.of("Master", email, phone, true),
                Arguments.of("Master", email, null, false),
                Arguments.of("Master", email, null, true),
                Arguments.of("Master", null, phone, false),
                Arguments.of("Client", email, null, null),
                Arguments.of("Client", null, phone, null),
                Arguments.of("Client", email, null, null),
                Arguments.of("Client", null, phone, null),
                Arguments.of("Client", null, null, null),
                Arguments.of("Service", email, phone, false),
                Arguments.of("Service", email, phone, true),
                Arguments.of("Service", email, null, null),
                Arguments.of("Service", null, phone, null),
                Arguments.of("Service", email, null, null),
                Arguments.of("Service", null, phone, null),
                Arguments.of("Service", null, null, null)
        );
    }
}
