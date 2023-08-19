package ru.gasworkers.dev.tests.web.orderProcess.repair;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB_REPAIR)
public class WaitMasterRepairTest extends BaseApiTest {
    @Browser(role = Role.CLIENT)
    ClientPages clientPages;
    @Test
    @DisplayName("Ремонт - в  состоянии мастер в пути")
    void waitMasterRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        StateInfo stateInfo = preconditionRepair.applyPrecondition(client, StateRepair.WAIT_MASTER);
//    ------------------------------------------------- UI -----------------------------------------------------------
        step(Role.CLIENT + " авторизация", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(client.getEmail(), "1111");
                clientPages.getHomePage().checkUrl();
                clientPages.getHomePage().guide.skipButton();
                step("Test run credentials ", () -> {
                    Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                    Allure.addAttachment("RunStartTime: ", date);
                });
            });
        });
        step(Role.CLIENT + " кабинет в состоянии - в состоянии " + StateRepair.WAIT_MASTER, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.WAIT_MASTER, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(StateRepair.WAIT_MASTER, stateInfo.getWaitMasterLastOrderInfo());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.WAIT_MASTER, () -> {
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(StateRepair.WAIT_MASTER, stateInfo.getWaitMasterOrderIdResponse());
                });
            };
            assertAll(Arrays.asList(case1, case2));
        });
    }
}