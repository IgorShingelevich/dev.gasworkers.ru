package ru.gasworkers.dev.tests.api.user.client.house.addHouse;

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
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.dto.HousesResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_HOUSE)
@Story("Добавление объекта")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE)
@Tag(AllureTag.API)
public class AddClientHousesApiTest extends BaseApiTest {

    private final ClientHousesApi clientHousesApi = new ClientHousesApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddHousePositiveCase testCase, @WithClient(houses = {@WithHouse}) User client) throws IOException {
        /*String token = loginApi.getToken(client);
        HousesResponseDto actualResponse = clientHousesApi.addHouse(testCase.getInputDto(), token)
                .statusCode(200)
                .extract().as(HousesResponseDto.class);
        assertResponsePartial(testCase.getExpectedResponse(), actualResponse, List.of("data.created_at", "data.id"));*/
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase2(AddHousePositiveCase testCase, @WithClient(houses = {
            @WithHouse(addressId = 2121, companyId = 1, title = "Custom House"),
            @WithHouse(addressId = 2121, companyId = 1, title = "Another Custom House")
    }) User client) {
        /*String token = loginApi.getToken(client);
        HousesResponseDto actualResponse = clientHousesApi.addHouse(testCase.getInputDto(), token)
                .statusCode(200)
                .extract().as(HousesResponseDto.class);
        assertResponsePartial(testCase.getExpectedResponse(), actualResponse, List.of("data.created_at", "data.id"));*/
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseNegativeDtoCase.class)
    @DisplayName("Negative dto case:")
    void negativeDtoTestCase(AddHouseNegativeDtoCase testCase, @WithClient User client) throws IOException {
        // With valid token
        String token = loginApi.getTokenPhone(client);
        HousesResponseDto actualResponse = clientHousesApi.addHouse(testCase.getInputDto(), token)
                .statusCode(422)
                .extract().as(HousesResponseDto.class);
        assertResponse(testCase.getExpectedResponse(), actualResponse);
    }


    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseNegativeAuthCase.class)
    @DisplayName("Negative auth case:")
    void negativeAuthTestCase(AddHouseNegativeAuthCase testCase, @WithClient User client) {
        clientHousesApi.addHouse(testCase.getInputDto(), testCase.getToken())
                .statusCode(401);
        // ToDo Add response body assert
    }

}
