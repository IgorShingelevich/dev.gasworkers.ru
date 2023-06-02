package ru.gasworkers.dev.tests.api.registration.regular;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.registration.regular.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.FinishRegistrationApi;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.CLIENT;

public class FinishRegistrationApiTest extends BaseApiTest {

    @Browser(role = CLIENT)
    ClientPages clientPages;

    private final StartRegistrationApi startRegistrationApi = new StartRegistrationApi();
    private final CheckRegistrationCodeApi checkRegistrationApi = new CheckRegistrationCodeApi();
     private final FinishRegistrationApi finishRegistrationApi = new FinishRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ComplexRegistrationCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Финиш регулярной регистрации client master service 200 OK")
    public void finishRegistrationPositiveApiTest(ComplexRegistrationCase testCase) {
        step("Start registration", () ->
                startRegistrationApi.startRegistration(testCase.getStartRequest())
                        .statusCode(200));

        step("Check registration", () ->
                checkRegistrationApi.checkRegularRegistrationCode(testCase.getCheckRequest())
                        .statusCode(200));

        step("Finish registration", () -> {
            FinishRegistrationResponseDto expectedResponse = FinishRegistrationResponseDto.successResponse();
            FinishRegistrationResponseDto actualResponse = finishRegistrationApi.finishRegularRegistration(testCase.getFinishRequest())
                    .statusCode(200)
                    .extract().as(FinishRegistrationResponseDto.class);

            assertResponse(expectedResponse, actualResponse);
        });
        step("UI check", () -> {
            String email = testCase.getStartRequest().getEmail();
            String password = "1234";


            clientPages.getLoginPage().open();
            clientPages.getLoginPage().loginEmail(email, password);
            clientPages.getHomePage().checkInitialState();
        });
    }

}
