package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SetCasesRegistrationDataProvider implements ArgumentsProvider  {
    public final Set1RegistrationDataProvider dataProvider = new Set1RegistrationDataProvider();


    private final int[] allowedCases = parseAllowedCases("1-32");

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return dataProvider.registrationParametersDataProvider().filter(arg -> {
            int index = (int) arg.get()[0];
            for (int allowedCase : allowedCases) {
                if (index == allowedCase) {
                    return true;
                }
            }
            return false;
        });
    }

    private int[] parseAllowedCases(String allowedCasesExpression) {
        String[] parts = allowedCasesExpression.split(",");
        List<Integer> allowedCasesList = new ArrayList<>();
        for (String part : parts) {
            part = part.trim();
            if (part.contains("-")) {
                String[] range = part.split("-");
                int start = Integer.parseInt(range[0].trim());
                int end = Integer.parseInt(range[1].trim());
                for (int i = start; i <= end; i++) {
                    allowedCasesList.add(i);
                }
            } else {
                allowedCasesList.add(Integer.parseInt(part));
            }
        }
        return allowedCasesList.stream().mapToInt(Integer::intValue).toArray();
    }



}
