package ru.gasworkers.dev.tests.master;

import ru.gasworkers.dev.browser.Browser;
import ru.gasworkers.dev.model.client.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.User;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.*;

public class MasterFlowTest extends BaseTest {

    @Browser(role = MASTER)
    MasterPages masterPages;

    User master = new User(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            " test_gas_master_sssr1@rambler.ru",
            "123456",
            null,
            79917644241L
    );



    @BeforeEach
    void masterLogin() {
        masterPages.getLoginPage().open();
        masterPages.getLoginPage().login(master.email, master.password);
    }

    @DisplayName("Мастер открывает заказ: ")
    @Test
    void checkMasterDispatchedOrderStatus(){
        step("Мастер открывает заказ в статусе: " + OrderState.MASTER_DISPATCHED, () -> {
//    @CsvFileSource(resources = "resources/invalidEmailFormat.csv", numLinesToSkip = 1, delimiter = '|')
            masterPages.getNewOrdersPage().checkFinishLoading();
            // TODO new orders sorting
            masterPages.getNewOrdersPage().openOrderByNumber(3215);
            masterPages.getOrderCardPage().checkFinishLoading();
        });
    }
    @Test
    public void checkNewSms() {
        step("Проверить, что появилось новое сообщение", () -> {
            master.firstCodeFromNewSMS();

        });
    }


}
