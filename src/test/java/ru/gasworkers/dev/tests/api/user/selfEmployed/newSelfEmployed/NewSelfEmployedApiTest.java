package ru.gasworkers.dev.tests.api.user.selfEmployed.newSelfEmployed;

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
import ru.gasworkers.dev.api.users.selfEmployed.complete.dto.UserResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithSelfEmployed;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;
import java.util.List;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.ACCOUNT)
@Feature(AllureFeature.ACCOUNT_STATE)
@Story("Новый самозанятый")
@Story("Cамозанятый")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.ACCOUNT)
@Tag(AllureTag.SELF_EMPLOYED)
@Tag(AllureTag.API)
public class NewSelfEmployedApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();

    @Test
    @DisplayName("Success case: userModel matches expected response")
    public void test(@WithSelfEmployed User selfEmployed) throws IOException {
        String token = loginApi.getTokenPhone(selfEmployed);
        UserResponseDto actualResponse = userApi.getUser(token)
                .statusCode(200)
                .extract().as(UserResponseDto.class);
        Integer userId = actualResponse.getData().getId();
        String email = actualResponse.getData().getEmail();
        Long phone = actualResponse.getData().getPhone();
        UserResponseDto expectedResponse = UserResponseDto.newSelfEmployedResponse(userId, email, phone);
        assertResponsePartial(expectedResponse, actualResponse, List.of("data.created_at", "data.created_at", "data.registered_at", "data.email_verified_at"));
        assertResponsePartial2(expectedResponse, actualResponse, List.of("data.createdAt", "data.createdAt", "data.registeredAt", "data.emailVerifiedAt"));
        System.out.println(selfEmployed);
    }
}
