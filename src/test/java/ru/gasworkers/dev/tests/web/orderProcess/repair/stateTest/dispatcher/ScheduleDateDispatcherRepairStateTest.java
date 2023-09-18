package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.dispatcher;

import io.qameta.allure.*;
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
import ru.gasworkers.dev.model.Role;
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
@Feature(AllureFeature.REPAIR)
@Story(AllureStory.WEB_STATE_REPAIR)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.DISPATCHER)
@Tag(AllureTag.WEB_REPAIR)
public class ScheduleDateDispatcherRepairStateTest extends BaseWebTest {
    @Browser(role = Role.DISPATCHER)
    DispatcherPages dispatcherPages;

    @Test
    @DisplayName("Ремонт - в  состоянии согласование даты и времени")
    void scheduleDateRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.SCHEDULE_DATE;
        Role role = Role.DISPATCHER;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + role + " авторизация", () -> {
            dispatcherPages.getLoginPage().open();
            dispatcherPages.getLoginPage().login(client.getEmail(), "1111");
            dispatcherPages.getHomePage().checkUrl();
//            dispatcherPages.getHomePage().guide.skipButton();
            step(role + " учетные данные", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });
        step(role + " кабинет в состоянии - в состоянии " + state, () -> {

            Consumer<SoftAssert> case0 = softAssert -> {
                step(role + " карта - в состоянии " + state, () -> {

                });
            };

            Consumer<SoftAssert> case1 = softAssert -> {
                step(role + " карточка последнего заказа - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().lastOrderComponent.checkFinishLoading();
//                    dispatcherPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(role + " карточка заказа - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().lastOrderComponent.checkFinishLoading();
//                    dispatcherPages.getHomePage().lastOrderComponent.open();
//                    dispatcherPages.getOrderCardPage().checkFinishLoading();
//                    dispatcherPages.getOrderCardPage().checkStateRepair(state, stateInfo.getOrdersIdResponseDto());
                });
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step(role + " уведомления - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().open();
//                    dispatcherPages.getHomePage().checkFinishLoading();
//                    Selenide.sleep(3000);
//                    dispatcherPages.getHomePage().header.actionsBlock.notifications();
//                    dispatcherPages.getAllNotificationsPage().checkFinishLoading();
//                    dispatcherPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
                });
            };
            Consumer<SoftAssert> case4 = softAssert -> {
                step(role + " красное уведомление в лк - в состоянии " + state, () -> {
//                    dispatcherPages.getHomePage().open();
//                    dispatcherPages.getHomePage().checkFinishLoading();
//                    dispatcherPages.getHomePage().redNotice.noNotice();
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
//                    dispatcherPages.getLandingPage().open();
//                    dispatcherPages.getLandingPage().checkFinishLoading();
//                    dispatcherPages.getLandingPage().noticeComponent.noNotifications();
                });
            };
            assertAll(Arrays.asList(case0, case1, case2, case3, case4, case5));
        });
    }
}