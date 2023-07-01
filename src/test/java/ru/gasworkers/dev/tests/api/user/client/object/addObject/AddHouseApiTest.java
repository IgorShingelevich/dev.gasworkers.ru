package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;
import java.util.List;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Добавление объекта")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT_OBJECT)
@Tag(AllureTag.API)
public class AddHouseApiTest extends BaseApiTest {

    private final HouseApi houseApi = new HouseApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddHousePositiveCase testCase, @WithUser User client) throws IOException {
        String token = loginApi.successLogin(client);
        AddHouseObjectResponseDTO actualResponse = houseApi.addHouse(testCase.getInputDto(), token)
                .statusCode(200)
                .extract().as(AddHouseObjectResponseDTO.class);
        assertResponsePartial(testCase.getExpectedResponse(), actualResponse, List.of("data.created_at", "data.id"));
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseNegativeDtoCase.class)
    @DisplayName("Negative dto case:")
    void negativeDtoTestCase(AddHouseNegativeDtoCase testCase, @WithUser User client) throws IOException {
        // With valid token
        String token = loginApi.successLogin(client);
        AddHouseObjectResponseDTO actualResponse = houseApi.addHouse(testCase.getInputDto(), token)
                .statusCode(422)
                .extract().as(AddHouseObjectResponseDTO.class);
        assertResponse(testCase.getExpectedResponse(), actualResponse);
    }


    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseNegativeAuthCase.class)
    @DisplayName("Negative auth case:")
    void negativeAuthTestCase(AddHouseNegativeAuthCase testCase, @WithUser User client) {
        houseApi.addHouse(testCase.getInputDto(), testCase.getToken())
                .statusCode(401);
        // ToDo Add response body assert
    }

}
