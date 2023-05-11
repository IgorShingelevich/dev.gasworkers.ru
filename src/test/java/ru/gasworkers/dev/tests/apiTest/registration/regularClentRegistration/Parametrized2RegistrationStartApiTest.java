package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi1;
import ru.gasworkers.dev.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parametrized2RegistrationStartApiTest extends BaseTest {
    public final RegularStartRegistrationApi1 regularStartRegistrationApi1 = new RegularStartRegistrationApi1();
    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    /*@ParameterizedTest
    @ArgumentsSource(SetCasesRegistrationDataProvider.class)
    // plase variable for
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.COMBINATORIAL), @Tag(AllureTag.API)})
    @DisplayName("Регистрация пользователя Api кейс: {0} для роли: {1} с email: {2} и phone: {3} и isPhoneSend: {4}")
    void testRegularStartRegistration(int index, String userType, String email, String phone, Boolean isPhoneSend) throws JsonProcessingException {
        Response response = regularStartRegistrationApi1.regularStartRegistration(userType, email, phone, isPhoneSend);
        assertEquals(200, response.statusCode());
        assertEquals("0", response.jsonPath().getString("status"));
        assertEquals("Успешная регистрация", response.jsonPath().getString("message"));
    }*/

}