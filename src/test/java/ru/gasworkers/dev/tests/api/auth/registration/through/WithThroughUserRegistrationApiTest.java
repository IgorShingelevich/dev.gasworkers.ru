package ru.gasworkers.dev.tests.api.auth.registration.through;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.user.UserApi;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.PRECONDITION)
@Story("предусловие фоновой регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class WithThroughUserRegistrationApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();
    File repairAndMaintenanceJson = new File("src/test/resources/api/registration/through/repairWithTroughUser.json");
    File consultationJson = new File("src/test/resources/api/registration/through/consultationWithThroughUser.json");
    private Integer id;
    private Integer guide0Id;

    @Disabled
    @Test
    @DisplayName("Предусловие фоновой регистрации  на ремонт")
    void throughRepairPrecondition(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        String clientToken = loginApi.getTokenThrough(client);
        step("убедиться, что модель пользователя соответствует ожидаемой", () -> {
            UserResponseDto newClientResponseDto = userApi.getUser(clientToken)
                    .statusCode(200)
                    .extract().as(UserResponseDto.class);
            id = newClientResponseDto.getData().getId();
            guide0Id = newClientResponseDto.getData().getGuides().get(0).getId();
            File repairAndMaintenanceJson = new File("src/test/resources/api/registration/through/repairWithTroughUser.json");
            assertResponsePartialNoATExcludeFields(expectedUserResponseDto(repairAndMaintenanceJson, client), newClientResponseDto, List.of("data.userNotificationsCount"));
//            assertResponsePartialNoAt(expectedUserResponseDto(repairAndMaintenanceJson, client), newClientResponseDto);
        });
    }

    @Test
    @DisplayName("Предусловие фоновой регистрации на  ТО")
    void throughMaintenancePrecondition(@WithThroughUser(withOrderType = @WithOrderType(type = "maintenance")) User client) {
        String clientToken = loginApi.getTokenThrough(client);
        step("убедиться, что модель пользователя соответствует ожидаемой", () -> {
            UserResponseDto newClientResponseDto = userApi.getUser(clientToken)
                    .statusCode(200)
                    .extract().as(UserResponseDto.class);
            id = newClientResponseDto.getData().getId();
            guide0Id = newClientResponseDto.getData().getGuides().get(0).getId();
            assertResponsePartialNoATExcludeFields(expectedUserResponseDto(repairAndMaintenanceJson, client), newClientResponseDto, List.of("data.userNotificationsCount"));
//            assertResponsePartialNoAt(expectedUserResponseDto(repairAndMaintenanceJson, client), newClientResponseDto);
        });
    }

    @Test
    @DisplayName("Предусловие фоновой регистрации на  видео консультацию")
    void throughConsultationPrecondition(@WithThroughUser(withOrderType = @WithOrderType(type = "consultation")) User client) {
        String clientToken = loginApi.getTokenThrough(client);
        step("убедиться, что модель пользователя соответствует ожидаемой", () -> {
            UserResponseDto newClientResponseDto = userApi.getUser(clientToken)
                    .statusCode(200)
                    .extract().as(UserResponseDto.class);
            id = newClientResponseDto.getData().getId();
            guide0Id = newClientResponseDto.getData().getGuides().get(0).getId();
            assertResponsePartialNoATExcludeFields(expectedUserResponseDto(consultationJson, client), newClientResponseDto, List.of("data.userNotificationsCount"));
//            assertResponsePartialNoAt(expectedUserResponseDto(consultationJson, client), newClientResponseDto);
        });
    }

    private UserResponseDto expectedUserResponseDto(File expectedJson, User client) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(expectedJson, UserResponseDto.class);
        expectedTemplateResponse.getData().setId(id);
        expectedTemplateResponse.getData().getGuides().get(0).setId(guide0Id);
        expectedTemplateResponse.getData().setPhone(client.getPhoneAsLong());
        expectedTemplateResponse.getData().setEmail(client.getEmail());
        return expectedTemplateResponse;
    }
}
