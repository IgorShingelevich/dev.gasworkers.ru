package ru.gasworkers.dev.tests.api.user.client.object;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.users.client.object.AddObjectApi;
import ru.gasworkers.dev.api.users.client.object.ObjectFactory;
import ru.gasworkers.dev.api.users.client.object.dto.AddObjectRequestDTO;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.user.client.BaseClientApiTest;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.OBJECT)
@Feature(AllureFeature.ADD_OBJECT)
@Story("Добавление объекта")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.OBJECT)
@Tag(AllureTag.API)
@Tag(AllureTag.CLIENT)

public class AddObjectApiTest extends BaseClientApiTest {
// new instance of AddObjectApi
    private final AddObjectApi addObjectApi = new AddObjectApi();

    @Test
    @DisplayName("Succsess case: ")
    void positiveAddObject(@WithUser User client) {
        // Create a default AddObjectRequestDTO using the ObjectFactory
        AddObjectRequestDTO defaultObject = ObjectFactory.createDefaultHouse();

        // Send the API request and get the response
        ValidatableResponse response = addObjectApi.addObject(defaultObject);

        // Assert the response
        response.statusCode(200);

        // Add more assertions if needed
        // Assertions.assertEquals(expectedValue, actualValue);
    }

}
