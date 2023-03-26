package ru.gasworkers.dev.tests.selfEmployed;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.master.ReadyForVideoState;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.UserBuilder;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.MASTER;
import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

public class SelfEmployedFlowTest extends BaseTest {

    @Browser(role = SELF_EMPLOYED)
    SelfEmployedPages selfEmployedPages;

    UserBuilder master = new UserBuilder(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            "Зарегистрирован с 11 января 2023 года",
            "gwtest2603_2314_rožkova@master.002",
            "1234",
            null,
            79917644241L
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
    void checkInitialCabinetState(){

        step("Убедиться, что страница сертификаты и оборудование в начальном состоянии", () -> {
            selfEmployedPages.getHomePage().modeSwitcher.checkMasterMode();
            selfEmployedPages.getHomePage().masterMode.sidebarMaster.certificatesAndEquipment();
            selfEmployedPages.getCertificatesAndEquipmentPage().checkInitialState();
        });

    }

    @Test
    @Disabled
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ открывает заказ в состоянии Мастер в пути")
    public void clientCheckMasterDispatchedOrderSate () {

    }

    @Test
    @Disabled
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ редактирует чеклист")
    public void clientCheckFillUpCheckListState () {

    }

    @Disabled
    @Owner("Igor Shingelevich")
    @Feature("Кабинет СМЗ")
    @Story("Просмотр заказа ТО")
    @Tags({@Tag("regression"), @Tag("СМЗ"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("СМЗ открывает заказ в состоянии без Отзыва Завершен")
    @Test
    void checkNotReviewedCompletedOrderState(){

    }

}
// todo all todo from MasterFowTest
// TODO submitReview flow
