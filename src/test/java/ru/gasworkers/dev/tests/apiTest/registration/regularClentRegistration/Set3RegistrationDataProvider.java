package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.stream.Stream;

public class Set3RegistrationDataProvider implements ArgumentsProvider {

    private static Stream<Arguments> registrationDataProvider() {
        return Stream.of(
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), false),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), null),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), "noArg", true),
                createArguments(UserType.CLIENT.toString(), "noArg", generateRandomPhone(), true),
                createArguments("noArg", generateRandomEmail(), generateRandomPhone(), true),
                createArguments(null, generateRandomEmail(), generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), null, true),
                createArguments(UserType.CLIENT.toString(), null, generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), null, null, false)
        );
    }

    private static Arguments createArguments(String userType, String email, String phone, Boolean isPhoneSend) {
        return Arguments.of(userType, email, phone, isPhoneSend);
    }

    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }

    /**
     * Provide a {@link Stream} of {@link Arguments} to be passed to a
     * {@code @ParameterizedTest} method.
     *
     * @param context the current extension context; never {@code null}
     * @return a stream of arguments; never {@code null}
     */
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return registrationDataProvider();
    }
}


