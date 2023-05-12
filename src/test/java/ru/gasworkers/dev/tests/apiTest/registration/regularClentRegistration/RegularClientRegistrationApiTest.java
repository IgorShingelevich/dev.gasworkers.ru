package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.regularRegistration.RegularStartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class RegularClientRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();
    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();

    private final RandomClient randomClient = new RandomClient();
    private final String
            userType = UserType.CLIENT.toString(),
            email = randomClient.getEmail(),
            phone = randomClient.getPhone(),
            firstName = randomClient.getName(),
            lastName = randomClient.getSurname(),
            patronymicName = randomClient.getPatronymicName(),
            password = randomClient.getPassword(),
            code = randomClient.getConfirmationCode(),
            gender = Gender.MALE.toString(),
            employedStatus = Employed_status.SELF_EMPLOYED.toString(); // status of employment for client not defined in api

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api ")
    public void clientRegistrationApiTest() throws JsonProcessingException {
        regularStartRegistrationApi.regularStartRegistration( userType,  email,  phone,  true);
    }



}
