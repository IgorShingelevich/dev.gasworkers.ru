/*
package ru.gasworkers.dev.tests.api.registration.regular;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.io.File;
import java.io.IOException;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckClientRegularRegistrationCodeParamApiTest {

    RandomClient randomClient = new RandomClient();
    String phone = randomClient.getPhone();
    String email = randomClient.getEmail();
    String type = UserType.CLIENT.toString();
    private final StartRegularRegistrationApi startRegularRegistrationApi = new StartRegularRegistrationApi();
    static File  checkRegistrationCode200 = new File("src/test/resources/api/registration/regular/check/checkRegistrationCode200.json");
    private final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    @Test
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName(" Проверка кода  регулярной регистрации клиента (позитивный кейс)")
    public void clientCheckRegularRegistrationPositiveApiTest() throws IOException {
        startRegularRegistrationApi.startRegistration(type, email, phone, true);
        String expectedResponse = FileUtils.readFileToString(checkRegistrationCode200, "UTF-8");
        String actualResponse = checkRegularRegistrationCodeApi.checkRegularRegistrationCode(111111, type, email, phone)
                .statusCode(200)
                    .extract().body().asString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        }






    }

*/
