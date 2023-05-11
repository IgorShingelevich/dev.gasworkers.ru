package ru.gasworkers.dev.tests.apiTest.administration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.administration.deleteUserByIDApi;

public class userDeleteByIDApiTest {
    public final deleteUserByIDApi deleteUserByIDApi = new deleteUserByIDApi();
     @BeforeEach
        public void setUp() {
            ApiTestConfig.configureRestAssured();
        }

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.ADMINISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION),  @Tag(AllureTag.ADMINISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Удаление пользователя по ID  Api")
    public void userDeleteByIDApiTest() {
        deleteUserByIDApi.deleteUserById("2226");
    }




}
