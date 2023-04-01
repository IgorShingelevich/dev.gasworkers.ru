package ru.gasworkers.dev.tests.selfEmployed;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.UserBuilder;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

public class SelfEmployedFlowTest extends BaseTest {

    @Browser(role = SELF_EMPLOYED)
    SelfEmployedPages selfEmployedPages;

    UserBuilder master = new UserBuilder(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            "Зарегистрирован с 11 января 2023 года",
            "gwtest2903_2124_konovalova@master.002",
            "1234",
            null,
            70021613581L
    );

    @BeforeEach
    void masterLogin() {
        selfEmployedPages.getLoginPage().open();
        selfEmployedPages.getLoginPage().login(master.email, master.password);
    }

    @Owner("Igor Shingelevich")
    @Feature("Кабинет СМЗ")
    @Story("Незаполненный ( начальное состояние) кабинет")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ открывает кабинет в первый раз")
    @Test
    void checkInitialCabinetState() {
        step("Убедиться, что страница сертификаты и оборудование в начальном состоянии", () -> {
            selfEmployedPages.getHomePage().mode.checkDispatcherMode();
            selfEmployedPages.getHomePage().mode.switchDispatcher();
            selfEmployedPages.getHomePage().modeDispatcher.checkFinishLoading();
            selfEmployedPages.getHomePage().header.burger.openBurger();
            selfEmployedPages.getHomePage().header.burger.certificatesAndEquipment();
            selfEmployedPages.getCertificatesAndEquipmentPage().checkInitialState();
        });
        step("Убедиться, что вкладка общиен данные  профиля в начальном состоянии", () -> {
            selfEmployedPages.getCertificatesAndEquipmentPage().sidebarDispatcher.profile();
            selfEmployedPages.getProfilePage().nav.common();
            selfEmployedPages.getProfilePage().tabCommon.saveButton();
            selfEmployedPages.getProfilePage().tabCommon.checkEmptyFormValidationTriggeredState();
        });


    }

    @Test
    @Disabled
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ открывает заказ в состоянии Мастер в пути")
    public void clientCheckMasterDispatchedOrderSate() {

    }

    @Test
    @Disabled
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ редактирует чеклист")
    public void clientCheckFillUpCheckListState() {

    }

    @Disabled
    @Owner("Igor Shingelevich")
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ открывает заказ в состоянии без Отзыва Завершен")
    @Test
    void checkNotReviewedCompletedOrderState() {

    }

}
// todo all todo from MasterFowTest
// TODO submitReview flow
