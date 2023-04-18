package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parametrized2RegistrationStartApiTest extends BaseTest {
    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    private static Stream<Arguments> registrationParameters() {
        RandomClient randomClient = new RandomClient();
        String emailClient = randomClient.getEmail();
        String phoneClient = randomClient.getPhone();

        return Stream.of(
                // Add all the combinations of parameters you want to test
                Arguments.of("Client", emailClient, phoneClient, false),
                Arguments.of("Client", emailClient, phoneClient, true),
                Arguments.of("Client", emailClient, null, false),
                Arguments.of("Client", emailClient, null, true),
                Arguments.of("Client", null, phoneClient, false),
                Arguments.of("Client", null, phoneClient, true),
                Arguments.of("Client", emailClient, null, null),
                Arguments.of("Client", null, phoneClient, null),
                Arguments.of("Client", emailClient, phoneClient, null),
                Arguments.of("Client", emailClient, null, false),
                Arguments.of("Client", null, phoneClient, true),
                Arguments.of("Client", emailClient, null, true),
                Arguments.of("Client", null, phoneClient, false),
                Arguments.of("Client", null, null, null),
                Arguments.of("Client", emailClient, phoneClient),
                Arguments.of("Client", emailClient, null),
                Arguments.of("Client", null, phoneClient),
                Arguments.of("Client", emailClient),
                Arguments.of("Client", null),
                Arguments.of("Client")
        );

    }

    @ParameterizedTest
    @MethodSource("registrationParameters")
    void testRegularStartRegistration(String userType, String email, String phone, Boolean isPhoneSend) {
        RegularStartRegistrationApi registrationApi = new RegularStartRegistrationApi();
        Response response = registrationApi.regularStartRegistration(userType, email, phone, isPhoneSend);

        // Add assertions to check the expected behavior
        assertEquals(200, response.statusCode());
        assertEquals("0", response.jsonPath().getString("status"));
        assertEquals("Успешная регистрация", response.jsonPath().getString("message"));
    }
}
