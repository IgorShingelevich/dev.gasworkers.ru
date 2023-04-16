package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.*;
import ru.gasworkers.dev.api.*;
import ru.gasworkers.dev.api.registration.*;
import ru.gasworkers.dev.extension.browser.*;
import ru.gasworkers.dev.model.*;
import ru.gasworkers.dev.pages.context.*;
import ru.gasworkers.dev.tests.*;
import ru.gasworkers.dev.utils.userBuilder.*;

import java.util.*;

import static ru.gasworkers.dev.model.Role.*;

public class RepairThroughClientRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final ThroughClientRegistrationApi throughClientRegistrationApi = new ThroughClientRegistrationApi();
    public final RandomClient randomClient = new RandomClient();
    private final String
            orderType = OrderType.REPAIR.getThroughApi(),
            startDate = "2023-04-18T09:00:00.000Z",
            endDate = "2023-04-20T09:00:00.000Z",
            email = "2345345g543ch@gmail.com",
            description = "поломаный котел";
    private final Long
            phone = 70016354354L;
    private final Integer
            addressId = 1698,
            time = 1;
    private final List<Integer> files = null; // Arrays.asList(2873, 2874);
    private final List<Map<String, Object>> equipments = Collections.singletonList(new HashMap<String, Object>() {{
        put("type_id", 1);
        put("brand_id", 11);
        put("custom_brand", null);
        put("model_id", 563);
        put("custom_model", null);
        put("power", "25.8");
    }});

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.BG_REGISTRATION)
    @Story(AllureStory.REPAIR)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.BG_REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Фоновая Регистрация клиента Api на ремонт с указанием телефона, почты и дополнительных полей")
    public void throughRepairClientRegistration() {
        throughClientRegistrationApi.throughRegistration(
                orderType, addressId, equipments, email, phone, description, null, startDate, endDate, time);

        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, "1111");
    }
}
