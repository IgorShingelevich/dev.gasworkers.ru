package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.*;
import org.json.JSONArray;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.*;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.CheckThroughRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.MaintenanceThroughClientRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class MaintenanceThroughClientRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

//    private final Map<String, String> headers = // your headers map from RestAssuredExample
    private final MaintenanceThroughClientRegistrationApi maintenanceThroughClientRegistrationApi = new MaintenanceThroughClientRegistrationApi();
    private final CheckThroughRegistrationCodeApi checkThroughRegistrationCodeApi = new CheckThroughRegistrationCodeApi();

    public final RandomClient randomClient = new RandomClient();
    private final String
            orderType = OrderType.MAINTENANCE.getThroughApi(),
            startDate = "2023-04-18T09:00:00.000Z",
            endDate = "2023-04-20T09:00:00.000Z",
            email = randomClient.getEmail(),
            phone = randomClient.getPhone();
    private final Long
            phoneLong = Long.parseLong(randomClient.getPhone());
    private final Integer
            addressId = 1703;

    private final JSONArray equipments = new JSONArray("[{\"type_id\":1,\"brand_id\":11,\"custom_brand\":null,\"model_id\":563,\"custom_model\":null,\"power\":\"25.8\"}]");


    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.BG_REGISTRATION)
    @Story(AllureStory.REPAIR)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.BG_REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Through Client Registration API for Maintenance with Phone and Email")
    public void throughMaintenanceClientRegistration() {
        maintenanceThroughClientRegistrationApi.throughRegistration(
                addressId,
                equipments.toString(),
                email,
                phone,
                orderType,
                startDate,
                endDate,
                1
        );
        checkThroughRegistrationCodeApi.checkCode(111111, email, phone);

        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, "1111");
    }
}
