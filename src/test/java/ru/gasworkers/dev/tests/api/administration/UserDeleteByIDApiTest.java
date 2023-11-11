package ru.gasworkers.dev.tests.api.administration;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.administration.DeleteUserByIDApi;
@Disabled

public class UserDeleteByIDApiTest {
    public final DeleteUserByIDApi deleteUserByIDApi = new DeleteUserByIDApi();

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    @Disabled
    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.ADMINISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.ADMINISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Удаление пользователя по ID  Api")
    public void userDeleteByIDApiTest() {
        deleteUserByIDApi.deleteUserById("2226");
    }


}
