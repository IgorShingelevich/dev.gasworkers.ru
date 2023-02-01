package tests.master;

import com.codeborne.selenide.logevents.SelenideLogger;
import extension.browser.Browser;
import model.client.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.context.MasterPages;
import tests.BaseTest;
import utils.User;

import static io.qameta.allure.Allure.step;
import static model.Role.*;

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
        step("Мастер открывает заказ в статусе: " + OrderStatus.MASTER_DISPATCHED, () -> {
//    @CsvFileSource(resources = "resources/invalidEmails.csv", numLinesToSkip = 1, delimiter = '|')
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
