package ru.gasworkers.dev.tests.apiTest.helperApiTest;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import java.util.stream.Stream;

public class BaseStartRegistrationTestCasesProvider  {


    public static Stream<Arguments> registrationTestCases() {
        RandomClient randomClient = new RandomClient();
        RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();

        // Define specific email and phone variables for Master role
        String emailMaster = randomSelfEmployedAndMaster.getEmail();
        String phoneMaster = randomSelfEmployedAndMaster.getPhone();

        // Define specific email and phone variables for Client role
        String emailClient = randomClient.getEmail();
        String phoneClient = randomClient.getPhone();

        // Define specific email and phone variables for Service role
        String emailService = "service@example.com";
        String phoneService = "555-1234";

        return Stream.of(
                // Master test cases
                Arguments.of("Master", emailMaster, phoneMaster, false),
                Arguments.of("Master", emailMaster, phoneMaster, true),
                Arguments.of("Master", emailMaster, null, false),
                Arguments.of("Master", emailMaster, null, true),
                Arguments.of("Master", null, phoneMaster, false),
                Arguments.of("Master", null, phoneMaster, true),
                Arguments.of("Master", null, null, false),
                Arguments.of("Master", null, null, true),
                Arguments.of("Master", emailMaster, phoneMaster, null),
                Arguments.of("Master", emailMaster, null, null),
                Arguments.of("Master", null, phoneMaster, null),
                Arguments.of("Master", emailMaster, null, null),
                Arguments.of("Master", null, phoneMaster, null),
                Arguments.of("Master", null, null, null),

                // Client test cases
                Arguments.of("Client", emailClient, phoneClient, false),
                Arguments.of("Client", emailClient, phoneClient, true),
                Arguments.of("Client", emailClient, null, false),
                Arguments.of("Client", emailClient, null, true),
                Arguments.of("Client", null, phoneClient, false),
                Arguments.of("Client", null, phoneClient, true),
                Arguments.of("Client", null, null, false),
                Arguments.of("Client", null, null, true),
                Arguments.of("Client", emailClient, phoneClient, null),
                Arguments.of("Client", emailClient, null, null),
                Arguments.of("Client", null, phoneClient, null),
                Arguments.of("Client", emailClient, null, null),
                Arguments.of("Client", null, phoneClient, null),
                Arguments.of("Client", null, null, null),

                // Service company test cases
                Arguments.of("Service", emailService, phoneService, false),
                Arguments.of("Service", emailService, phoneService, true),
                Arguments.of("Service", emailService, null, false),
                Arguments.of("Service", emailService, null, true),
                Arguments.of("Service", null, phoneService, false),
                Arguments.of("Service", null, phoneService, true),
                Arguments.of("Service", null, null, false),
                Arguments.of("Service", null, null, true),
                Arguments.of("Service", emailService, phoneService, null),
                Arguments.of("Service", emailService, null, null),
                Arguments.of("Service", null, phoneService, null),
                Arguments.of("Service", emailService, null, null),
                Arguments.of("Service", null, phoneService, null),
                Arguments.of("Service", null, null, null)
        );
    }


}
