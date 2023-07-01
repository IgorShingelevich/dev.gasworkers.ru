package ru.gasworkers.dev.tests.api.administration.getUserWithAdmin;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.registration.authorisation.LoginApi;
import ru.gasworkers.dev.api.auth.registration.authorisation.dto.LoginResponseDTO;
import ru.gasworkers.dev.tests.api.BaseApiTest;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.AUTHORIZATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.ADMINISTRATION)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.API)
public class GetUserWithAdminApiTest extends BaseApiTest {

    private final LoginApi loginApi = new LoginApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetUserWithAdminPositiveCase.class)
    @DisplayName("Success case")
    void getUserWithAdmin(GetUserWithAdminPositiveCase testCase) {
        LoginResponseDTO actualResponse = loginApi.login(testCase.getLoginDto())
                .statusCode(200)
                .extract().as(LoginResponseDTO.class);
        LoginResponseDTO.DataDto loginData = actualResponse.getData();
        String tokenAdmin = loginData.getToken();
        System.out.println(" tokenAdmin = " + tokenAdmin);

        getUserWithAdminApi.getUserWithAdmin(tokenAdmin, 340) //487
                .statusCode(200);

    }
}
