//package ru.gasworkers.dev.tests.api.auth.registration.through;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Owner;
//import io.qameta.allure.Story;
//import org.junit.jupiter.api.*;
//import ru.gasworkers.dev.allure.AllureEpic;
//import ru.gasworkers.dev.allure.AllureFeature;
//import ru.gasworkers.dev.allure.AllureStory;
//import ru.gasworkers.dev.allure.AllureTag;
//import ru.gasworkers.dev.api.ApiTestConfig;
//import ru.gasworkers.dev.api.auth.registration.through.OLD.ThroughClientRegistrationApi;
//import ru.gasworkers.dev.extension.browser.Browser;
//import ru.gasworkers.dev.model.OrderType;
//import ru.gasworkers.dev.pages.context.ClientPages;
//import ru.gasworkers.dev.tests.BaseTest;
//import ru.gasworkers.dev.utils.userBuilder.RandomClient;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static ru.gasworkers.dev.model.Role.CLIENT;
//
//public class RepairThroughClientRegistrationApiTest extends BaseTest {
//    @Browser(role = CLIENT)
//    ClientPages clientPages;
//
//    @BeforeEach
//    public void setUp() {
//        ApiTestConfig.configureRestAssured();
//    }
//
//    public final ThroughClientRegistrationApi throughClientRegistrationApi = new ThroughClientRegistrationApi();
//    public final RandomClient randomClient = new RandomClient();
//    private final String
//            orderType = OrderType.REPAIR.getThroughApi(),
//            startDate = "2023-04-18T09:00:00.000Z",
//            endDate = "2023-04-20T09:00:00.000Z",
//            email = "2345345g543ch@gmail.com",
//            description = "поломаный котел";
//    private final Long
//            phone = 70016354354L;
//    private final Integer
//            addressId = 1698,
//            time = 1;
//    private final List<Integer> files = null; // Arrays.asList(2873, 2874);
//    private final List<Map<String, Object>> equipments = Collections.singletonList(new HashMap<String, Object>() {{
//        put("type_id", 1);
//        put("brand_id", 11);
//        put("custom_brand", null);
//        put("model_id", 563);
//        put("custom_model", null);
//        put("power", "25.8");
//    }});
//    @Disabled
//    @Test
//    @Owner("Igor Shingelevich")
//    @Epic(AllureEpic.REGISTRATION)
//    @Feature(AllureFeature.BG_REGISTRATION)
//    @Story(AllureStory.REPAIR)
//    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.BG_REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
//    @DisplayName("Фоновая Регистрация клиента Api на ремонт с указанием телефона, почты и дополнительных полей")
//    public void throughRepairClientRegistration() {
//        throughClientRegistrationApi.throughRegistration(
//                orderType, addressId, equipments, email, phone, description, null, startDate, endDate, time);
//
//        clientPages.getLoginPage().open();
//        clientPages.getLoginPage().login(email, "1111");
//    }
//}
