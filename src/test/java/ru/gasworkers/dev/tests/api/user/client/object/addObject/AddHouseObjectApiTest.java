package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Добавление объекта")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE_OBJECT)
@Tag(AllureTag.API)
public class AddHouseObjectApiTest extends BaseApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseObjectPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddHouseObjectPositiveCase testCase, @WithUser User client) {
        step("Add object", () -> {
            AddHouseObjectResponseDTO expectedResponse = AddHouseObjectResponseDTO.successResponse();
//            AddHouseObjectRequestDTO expectedResponse = AddHouseObjectBuilder.addObjectRequest();
            // Define the excluded fields specific to this test case
            List<String> excludedFields = Arrays.asList("data.created_at", "data.id");

            AddHouseObjectResponseDTO actualResponse = addObjectApi.addObject(testCase.getAddObjectDtoDTO())
                    .statusCode(200)
                    .extract().as(AddHouseObjectResponseDTO.class);
            assertResponsePartial(expectedResponse, actualResponse, excludedFields);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddHouseObjectNegativeCase.class)
    @DisplayName("Negative case:")
    void negativeTestCase(AddHouseObjectNegativeCase testCase, @WithUser User client) {
        step("Add object", () -> {
            AddHouseObjectResponseDTO expectedResponse = testCase.getExpectedResponse();
            AddHouseObjectResponseDTO actualResponse = addObjectApi.addObject(testCase.getAddHouseObjectDto())
                    .statusCode(422)
                    .extract().as(AddHouseObjectResponseDTO.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }
}
