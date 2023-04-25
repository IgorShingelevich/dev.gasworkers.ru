//package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;
//
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import ru.gasworkers.dev.utils.userBuilder.RandomClient;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class Set1RegistrationArgumentsProvider
////        implements ArgumentsProvider
//{
//
//    String
//            allPositiveCasesSet = "1-3, 10-12",
//            negative422CasesSet = "13-15, 28-33",
//    // cases  with error: No ParameterResolver registered for parameter [java.lang.Boolean arg4] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
//    noParameterResolverArg4CasesSet = "18, 21, 24, 27,  34-39",
//
//    // cases with error: No ParameterResolver registered for parameter [java.lang.String arg3] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
//    noParameterResolverArg3CasesSet = "40, 41",
//
//    // cases with error:No ParameterResolver registered for parameter [java.lang.String arg2] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
//    noParameterResolverArg2CasesSet = "42",
//
//    //cases with error: Error converting parameter at index 3: No implicit conversion to convert object of type java.lang.Boolean to type java.lang.String
//    //org.junit.jupiter.api.extension.ParameterResolutionException: Error converting parameter at index 3: No implicit conversion to convert
//    errorConvertingParameterAtIndex3 = "16, 17, 19, 20, 22, 23, 25, 26",
//
//    allCasesSet ="1-32";
//    private final int[] allowedCases = parseAllowedCases("7, 8, 9");
//
//    private static String generateRandomEmail() {
//        RandomClient randomClient = new RandomClient();
//        return randomClient.getEmail();
//    }
//
//    private static String generateRandomPhone() {
//        RandomClient randomClient = new RandomClient();
//        return randomClient.getPhone();
//    }
//
//    private static Stream<Arguments> registrationParametersDataProvider() {
//        return Stream.of(
//                // All combinations with phone (including empty string)
//                Arguments.of(1, "client", generateRandomEmail(), generateRandomPhone(), true),
//                Arguments.of(2, "client", generateRandomEmail(), generateRandomPhone(), false),
//                Arguments.of(3, "client", generateRandomEmail(), generateRandomPhone(), null),
//                Arguments.of(4, "client", generateRandomEmail(), "", true),
//                Arguments.of(5, "client", generateRandomEmail(), "", false),
//                Arguments.of(6, "client", generateRandomEmail(), "", null),
//                // All combinations with email (including empty string)
//                Arguments.of(7, "client", "", generateRandomPhone(), true),
//                Arguments.of(8, "client", "", generateRandomPhone(), false),
//                Arguments.of(9, "client", "", generateRandomPhone(), null),
//                // All combinations with phone as null
//                Arguments.of(10, "client", generateRandomEmail(), null, true),
//                Arguments.of(11, "client", generateRandomEmail(), null, false),
//                Arguments.of(12, "client", generateRandomEmail(), null, null),
//                // All combinations with email as null
//                Arguments.of(13, "client", null, generateRandomPhone(), true),
//                Arguments.of(14, "client", null, generateRandomPhone(), false),
//                Arguments.of(15, "client", null, generateRandomPhone(), null),
//                // All combinations without phone
//                Arguments.of(16, "client", generateRandomEmail(), "", true),
//                Arguments.of(17, "client", generateRandomEmail(), "", false),
//                Arguments.of(18, "client", generateRandomEmail(), "", null),
//                Arguments.of(19, "client", "", "", true),
//                Arguments.of(20, "client", "", "", false),
//                Arguments.of(21, "client", "", "", null),
//                // All combinations without email
//                Arguments.of(22, "client", "", generateRandomPhone(), true),
//                Arguments.of(23, "client", "", generateRandomPhone(), false),
//                Arguments.of(24, "client", "", generateRandomPhone(), null),
//                Arguments.of(25, "client", "", "", true),
//                Arguments.of(26, "client", "", "", false),
//                Arguments.of(27, "client", "", "", null),
//                // All combinations without phone and isPhoneSend
//                Arguments.of(28, "client", generateRandomEmail(), "", null),
//                Arguments.of(29, "client", "", "", null),
//                // All combinations without email and isPhoneSend
//                Arguments.of(30, "client", "", generateRandomPhone(), null),
//                Arguments.of(31, "client", "", "", null),
//                // All combinations without phone, email, and isPhoneSend
//                Arguments.of(32, "client", "", "", null)
//        );
//    }
//
////    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
//        return registrationParametersDataProvider().filter(arg -> {
//            int index = (int) arg.get()[0]; // Assuming the index is the first element of the arguments
//            for (int allowedCase : allowedCases) {
//                if (index == allowedCase) {
//                    return true;
//                }
//            }
//            return false;
//        });
//    }
//
//    private int[] parseAllowedCases(String allowedCasesExpression) {
//        String[] parts = allowedCasesExpression.split(",");
//        List<Integer> allowedCasesList = new ArrayList<>();
//        for (String part : parts) {
//            part = part.trim();
//            if (part.contains("-")) {
//                String[] range = part.split("-");
//                int start = Integer.parseInt(range[0].trim());
//                int end = Integer.parseInt(range[1].trim());
//                for (int i = start; i <= end; i++) {
//                    allowedCasesList.add(i);
//                }
//            } else {
//                allowedCasesList.add(Integer.parseInt(part));
//            }
//        }
//        return allowedCasesList.stream().mapToInt(Integer::intValue).toArray();
//    }
//}
