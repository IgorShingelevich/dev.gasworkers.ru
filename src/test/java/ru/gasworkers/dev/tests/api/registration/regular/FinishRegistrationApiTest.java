package ru.gasworkers.dev.tests.api.registration.regular;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;

import static io.qameta.allure.Allure.step;

public class FinishRegistrationApiTest {

    private final StartRegularRegistrationApi startRegistrationApi = new StartRegularRegistrationApi();
    private final CheckRegularRegistrationCodeApi checkRegistrationApi = new CheckRegularRegistrationCodeApi();
    // private final FinishRegistrationCodeApi finishRegistrationApi = new CheckRegularRegistrationCodeApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ComplexRegistrationCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("asfasfas")
    public void asfafasf(ComplexRegistrationCase testCase) {
        step("Start registration", () ->
                startRegistrationApi.startRegistration(testCase.getStartRequest())
                        .statusCode(200));

        step("Check registration", () ->
                checkRegistrationApi.checkRegularRegistrationCode(testCase.getCheckRequest())
                        .statusCode(200));

        step("Finish registration", () -> {
            // Expected response = чтение из файла json
            // FinishResponse actualResponse = finishRegistrationApi.finishRegistration(testCase.getFinishRequest)
            // .statusCode(200)
            // .as(FinishResponse.class)

            // Assert
        });
    }

}
