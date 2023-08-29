package ru.gasworkers.dev.tests.web.orderProcess.repair;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
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
import ru.gasworkers.dev.tests.web.BaseWebTest;

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
public class HasOfferRepairTest extends BaseWebTest {
    @Browser(role = Role.CLIENT)
    ClientPages clientPages;

    @Test
    @DisplayName("Ремонт - в состоянии есть отклик СК")
    void hasOfferRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.HAS_OFFER;
        Role role = Role.CLIENT;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);
// Get the StateInfo and CommonFieldsRepairDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        WebRepairTestSteps webStep = new WebRepairTestSteps();
//    ------------------------------------------------- UI -----------------------------------------------------------
        webStep.loginOnWeb(client, role);

        step(role + " кабинет  в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                webStep.checkLastOrderComponent(role, state, stateInfo);
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                webStep.checkRedirectFromLastOrderToSelectServicePage(role, state);
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                webStep.checkSelectServicePage(role, state, stateInfo);
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                webStep.checkOrderCardPage(role, state, stateInfo);
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                webStep.checkNotificationsPage(role, state, stateInfo);
            };
            Consumer<SoftAssert> case6 = softAssert -> {
                webStep.checkRedNotificationHomePage(role, state);
            };
            Consumer<SoftAssert> case7 = softAssert -> {
                webStep.checkRedNotificationLandingPage(role, state);
            };
            assertAll(Arrays.asList(case1, case2, case3, case4, case5, case6, case7));
        });
    }

     /*void loginOnWeb(User client, Role role) {
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
    }*/

     /*void checkRedNotificationLandingPage(Role role, StateRepair state) {
        step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().noticeComponent.noNotifications();
        });
    }*/

     /*void checkRedNotificationHomePage(Role role, StateRepair state) {
        step(role + " красное уведомление в лк - в состоянии " + state, () -> {
            clientPages.getHomePage().open();
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().redNotice.noNotice();
        });
    }*/

    /* void checkNotificationsPage(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " уведомления - в состоянии " + state, () -> {
            clientPages.getAllNotificationsPage().open();
            clientPages.getAllNotificationsPage().checkFinishLoading();
            clientPages.getAllNotificationsPage().checkState(state, stateInfo.getNotificationsDto());
        });
    }*/

    /* void checkOrderCard(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " карточка заказа - в состоянии " + state, () -> {
            clientPages.getSelectServicePage().toOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkState(state, stateInfo.getOrdersIdResponseDto());
        });
    }*/



   /* private void checkLastOrderComponent(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " карточка последнего заказа - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
        });
    }*/

    /*private void checkRedirectFromLastOrderToSelectService(Role role, StateRepair state) {
        step(role + " карточка заказа редирект на карту - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.open();
            clientPages.getSelectServicePage().checkUrl();
        });
    }*/
}