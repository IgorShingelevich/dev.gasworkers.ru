package ru.gasworkers.dev.tests.api.helperApiTest;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import java.util.stream.Stream;

public abstract class BaseStartRegistrationTestCasesProvider  implements ArgumentsProvider{


    public static Stream<Arguments> registrationTestCases() {
        RandomClient randomClient = new RandomClient();
        RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();

        // Define specific email and phone variables for master role
        String emailMaster = randomSelfEmployedAndMaster.getEmail();
        String phoneMaster = randomSelfEmployedAndMaster.getPhone();

        // Define specific email and phone variables for client role
        String emailClient = randomClient.getEmail();
        String phoneClient = randomClient.getPhone();

        // Define specific email and phone variables for service role
        String emailService = "service@example.com";
        String phoneService = "555-1234";

        return Stream.of(
                // master test cases
                Arguments.of("master", emailMaster, phoneMaster, false),
                Arguments.of("master", emailMaster, phoneMaster, true),
                Arguments.of("master", emailMaster, "noArgs", false),
                Arguments.of("master", "noArgs", phoneMaster, false),
                Arguments.of("master", emailMaster, phoneMaster, null),
                Arguments.of("master", emailMaster, "noArgs", null),
                Arguments.of("master", emailMaster, null, false),
                Arguments.of("master", emailMaster, null, true),
                Arguments.of("master", null, phoneMaster, false),
                Arguments.of("master", null, phoneMaster, true),
                Arguments.of("master", null, null, false),
                Arguments.of("master", null, null, true),
                Arguments.of("master", null, phoneMaster, null),
                Arguments.of("master", emailMaster, null, null),
                Arguments.of("master", null, phoneMaster, null),
                Arguments.of("master", null, null, null),

                // client test cases
                Arguments.of("client", emailClient, phoneClient, false),
                Arguments.of("client", emailClient, phoneClient, true),
                Arguments.of("client", emailClient, phoneClient, null),
                Arguments.of("client", emailClient, "noArgs", false),
                Arguments.of("client", "noArgs", phoneClient, false),
                Arguments.of("client", emailClient, null, false),
                Arguments.of("client", emailClient, null, true),
                Arguments.of("client", null, phoneClient, false),
                Arguments.of("client", null, phoneClient, true),
                Arguments.of("client", null, null, false),
                Arguments.of("client", null, null, true),
                Arguments.of("client", emailClient, null, null),
                Arguments.of("client", null, phoneClient, null),
                Arguments.of("client", emailClient, null, null),
                Arguments.of("client", null, phoneClient, null),
                Arguments.of("client", null, null, null)

                // service company test cases
                /*Arguments.of("service", emailService, phoneService, false),
                Arguments.of("service", emailService, phoneService, true),
                Arguments.of("service", emailService, null, false),
                Arguments.of("service", emailService, null, true),
                Arguments.of("service", null, phoneService, false),
                Arguments.of("service", null, phoneService, true),
                Arguments.of("service", null, null, false),
                Arguments.of("service", null, null, true),
                Arguments.of("service", emailService, phoneService, null),
                Arguments.of("service", emailService, null, null),
                Arguments.of("service", null, phoneService, null),
                Arguments.of("service", emailService, null, null),
                Arguments.of("service", null, phoneService, null),
                Arguments.of("service", null, null, null)*/
        );
    }


}
