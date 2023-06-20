package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.users.client.object.addObject.AddObjectApi;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddObjectResponseDTO;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Завершение регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.ADD_CLIENT_OBJECT)
@Tag(AllureTag.API)
public class AddObjectApiTest extends BaseApiTest {
    private final AddObjectApi addObjectApi = new AddObjectApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddObjectPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddObjectPositiveCase testCase, @WithUser User client) {
        step("Add object", () -> {
            AddObjectResponseDTO expectedResponse = AddObjectResponseDTO.successResponse();

            // Define the excluded fields specific to this test case
            List<String> excludedFields = Arrays.asList("data.created_at", "data.id");

            AddObjectResponseDTO actualResponse = addObjectApi.addObject(testCase.getAddObjectDtoDTO())
                    .statusCode(200)
                    .extract().as(AddObjectResponseDTO.class);
            assertResponsePartial(expectedResponse, actualResponse, excludedFields);
        });
    }
}
