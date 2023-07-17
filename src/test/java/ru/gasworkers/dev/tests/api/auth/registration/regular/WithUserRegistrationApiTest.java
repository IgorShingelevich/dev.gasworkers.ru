package ru.gasworkers.dev.tests.api.auth.registration.regular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.user.UserApi;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.File;
import java.io.IOException;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.PRECONDITION)
@Story("предусловие регулярной регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class WithUserRegistrationApiTest extends BaseApiTest {

    private final UserApi userApi = new UserApi();
    private Integer id;


    @Test
    @DisplayName("Предусловие регулярной регистрации клиента")
    void preconditionRegularRegistrationClient(@WithClient User client) {
        String token = loginApi.getTokenPhone(client);

        step("убедиться, что модель пользователя соответствует ожидаемой", () -> {
            UserResponseDto newClientResponseDto = userApi.getUser(token)
                    .statusCode(200)
                    .extract().as(UserResponseDto.class);
            id = newClientResponseDto.getData().getId();
            assertResponsePartialNoAt(expectedUserResponseDto(client), newClientResponseDto);
        });
    }

    private UserResponseDto expectedUserResponseDto(User client) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/registration/regular/withUser/withUserUser.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(id);
        expectedTemplateResponse.getData().setPhone(Long.valueOf(client.getPhone()));
        expectedTemplateResponse.getData().setEmail(client.getEmail());
        expectedTemplateResponse.getData().setFirstName(client.getFirstName());
        expectedTemplateResponse.getData().setLastName(client.getLastName());
        expectedTemplateResponse.getData().setMiddleName(client.getMiddleName());
        expectedTemplateResponse.getData().setFullName(client.getFullName());
        return expectedTemplateResponse;
    }
}
