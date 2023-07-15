package ru.gasworkers.dev.tests.api.auth.registration.selfEmployed;

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
import ru.gasworkers.dev.extension.user.WithSelfEmployed;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.File;
import java.io.IOException;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.PRECONDITION)
@Story("предусловие регистрации самозанятого")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.SELF_EMPLOYED)
@Tag(AllureTag.API)
public class WithSelfEmployedRegistrationApi extends BaseApiTest {
    private final UserApi userApi = new UserApi();
    private Integer id;

    @Test
    @DisplayName("Предусловие регулярной регистрации самозанятого мастера")
    public void preconditionSelfEmployedRegistration(@WithSelfEmployed User selfEmployed) throws IOException {
        String token = loginApi.getTokenPhone(selfEmployed);
        UserResponseDto newSelfEmployedResponseDto = userApi.getUser(token)
                .statusCode(200)
                .extract().as(UserResponseDto.class);
        id = newSelfEmployedResponseDto.getData().getId();
        assertResponsePartialNoAt(expectedUserResponseDto(selfEmployed), newSelfEmployedResponseDto);
        System.out.println(selfEmployed);
    }

    private UserResponseDto expectedUserResponseDto(User selfEmployed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/registration/selfEmployed/withSelfEmployed.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(id);
        expectedTemplateResponse.getData().setPhone(Long.valueOf(selfEmployed.getPhone()));
        expectedTemplateResponse.getData().setEmail(selfEmployed.getEmail());
        return expectedTemplateResponse;
    }
}
