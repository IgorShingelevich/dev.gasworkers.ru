package ru.gasworkers.dev.tests.api.user.selfEmployed.complete;

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
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.users.selfEmployed.complete.CompleteSelfEmployedRoleApi;
import ru.gasworkers.dev.api.users.selfEmployed.complete.dto.UserResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.ACCOUNT)
@Feature(AllureFeature.ACCOUNT_STATE)
@Story("Новый самозанятый [ определение роли ]")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.ACCOUNT)
@Tag(AllureTag.SELF_EMPLOYED)
@Tag(AllureTag.API)
public class CompleteSelfEmployedRoleApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LoginApi loginApi = new LoginApi();
    private final CompleteSelfEmployedRoleApi completeSelfEmployedRoleApi = new CompleteSelfEmployedRoleApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(CompletePositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(CompletePositiveCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () ->
                registrationApi.checkRegistration(testCase.getCheckDto())
                        .statusCode(200));

        step("Finish registration", () -> {
            registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(200);
        });
        String token = step("Login", () -> {
            return loginApi.login(testCase.getLoginDto())
                    .statusCode(200)
                    .extract().as(LoginResponseDto.class)
                    .getData().getToken();
        });
        step("Check role", () -> {
            UserResponseDto actualResponse = completeSelfEmployedRoleApi.completeSelfEmployedRole(testCase.getCompleteRoleDto(), token)
                    .statusCode(200)
                    .extract().as(UserResponseDto.class);
            String selfEmployedStatusAfterComplete = "self-employed";
            Integer id = actualResponse.getData().getId();
            String email = actualResponse.getData().getEmail();
            Long phone = actualResponse.getData().getPhone();
            Boolean isIp = actualResponse.getData().getIsIp();
            Boolean isHaveContract = actualResponse.getData().getIsHaveContract();
            Integer dataGuidesId = actualResponse.getData().getGuides().get(0).getGuideId();
            UserResponseDto expectedResponse = UserResponseDto.completeSelfEmployedResponse(id, email, phone, isIp, isHaveContract, selfEmployedStatusAfterComplete, dataGuidesId);
            assertResponsePartial(expectedResponse, actualResponse, List.of("data.email_verified_at", "data.registered_at", "data.created_at", "data.joined_at"));
            //todo:  "email_verified_at": null
//            assertResponsePartial2(expectedResponse, actualResponse, List.of("data.email_verifiedAt", "data.registeredAt", "data.createdAt", "data.joinedAt"));
        });
    }
}
