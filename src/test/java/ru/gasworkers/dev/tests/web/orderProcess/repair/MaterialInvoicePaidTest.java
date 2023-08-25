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
public class MaterialInvoicePaidTest extends BaseApiTest {
    @Browser(role = Role.CLIENT)
    ClientPages clientPages;

    @Test
    @DisplayName("Ремонт - в  состоянии клиент оплатил счет на материалы")
    void materialInvoicePaid
            (@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.MATERIAL_INVOICE_PAID;
        Role role = Role.CLIENT;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsRepairDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + role + " авторизация", () -> {
            clientPages.getLoginPage().open();
            clientPages.getLoginPage().login(client.getEmail(), "1111");
            clientPages.getHomePage().checkUrl();
            clientPages.getHomePage().guide.skipButton();
            step(role + " учетные данные", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });
        step(role + " кабинет в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(role + " карточка последнего заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(role + " карточка заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(state, stateInfo.getOrdersIdResponseDto());
                });
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step(role + " уведомления - в состоянии " + state, () -> {
                    clientPages.getAllNotificationsPage().open();
                    clientPages.getAllNotificationsPage().checkFinishLoading();
                    clientPages.getAllNotificationsPage().checkState(state, stateInfo.getNotificationsDto());
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(role + " красное уведомление в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().redNotice.noNotice();
                });
            };

            Consumer<SoftAssert> case5 = softAssert -> {
                step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
                    clientPages.getLandingPage().open();
                    clientPages.getLandingPage().checkFinishLoading();
                    clientPages.getLandingPage().noticeComponent.noNotifications();
                });
            };
            assertAll(Arrays.asList(case1, case2, case3, case4, case5));
        });
    }
}