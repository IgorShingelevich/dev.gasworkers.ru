package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.superDispatcher;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.PreconditionRepair;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR_STATE)
@Story(AllureStory.REPAIR_STATE_SUPER_DISPATCHER)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.SUPER_DISPATCHER)
@Tag(AllureTag.WEB_REPAIR)
public class HasOfferSuperDispatcherRepairStateTest extends BaseWebTest {
    @Browser(role = UserRole.DISPATCHER)
    DispatcherPages dispatcherPages;

    @Disabled
    @Test
    @DisplayName("Ремонт - в состоянии есть отклик СК")
    void hasOfferRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.HAS_SERVICE_OFFER;
        UserRole userRole = UserRole.DISPATCHER;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsRepairDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + userRole + " авторизация", () -> {
            dispatcherPages.getLoginPage().open();
            dispatcherPages.getLoginPage().login(client.getEmail(), "1111");
            dispatcherPages.getHomePage().checkUrl();
//            dispatcherPages.getHomePage().guide.skipButton();
            step(userRole + " учетные данные", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });

        step(userRole + " кабинет  в состоянии - в состоянии " + state, () -> {

            Consumer<SoftAssert> case0 = softAssert -> {
                step(userRole + " карта - в состоянии " + state, () -> {

                });
            };

            Consumer<SoftAssert> case1 = softAssert -> {
                step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().lastOrderComponent.checkFinishLoading();
//                    dispatcherPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
//                checkRedirectFromLastOrderToSelectService(userRole, state);
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step(userRole + " страница выбора услуги - в состоянии " + state, () -> {
//                    dispatcherPages.getSelectServicePage().checkFinishLoadingRepair();
//                    dispatcherPages.getSelectServicePage().checkState(state, stateInfo.getSuggestedServiceDto());
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(userRole + " карточка заказа - в состоянии " + state, () -> {
//                    dispatcherPages.getSelectServicePage().toOrderCard();
//                    dispatcherPages.getOrderCardPage().checkFinishLoading();
//                    dispatcherPages.getOrderCardPage().checkStateRepair(state, stateInfo.getOrdersIdResponseDto());
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step(userRole + " уведомления - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().open();
//                    dispatcherPages.getHomePage().checkFinishLoading();
//                    Selenide.sleep(3000);
//                    dispatcherPages.getHomePage().header.actionsBlock.notifications();
//                    dispatcherPages.getAllNotificationsPage().checkFinishLoading();
//                    dispatcherPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
                });
            };
            Consumer<SoftAssert> case6 = softAssert -> {
                step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().open();
//                    dispatcherPages.getHomePage().checkFinishLoading();
//                    dispatcherPages.getHomePage().redNotice.noNotice();
                });
            };
            Consumer<SoftAssert> case7 = softAssert -> {
                step(userRole + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
//                    dispatcherPages.getLandingPage().open();
//                    dispatcherPages.getLandingPage().checkFinishLoading();
//                    dispatcherPages.getLandingPage().noticeComponent.noNotifications();
                });
            };
            assertAll(Arrays.asList(case0, case1, case2, case3, case4, case5, case6, case7));
        });
    }

    private void checkRedirectFromLastOrderToSelectService(UserRole userRole, StateRepair state) {
        step(userRole + " карточка заказа редирект на карту - в состоянии " + state, () -> {
//            dispatcherPages.getHomePage().lastOrderComponent.checkFinishLoading();
//            dispatcherPages.getHomePage().lastOrderComponent.open();
//            dispatcherPages.getSelectServicePage().checkUrl();
        });
    }
}