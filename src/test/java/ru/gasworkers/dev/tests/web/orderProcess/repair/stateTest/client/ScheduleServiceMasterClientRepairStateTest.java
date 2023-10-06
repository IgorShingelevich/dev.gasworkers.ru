package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.client;

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
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.context.ClientPages;
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
@Story(AllureStory.REPAIR_STATE_CLIENT)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB_REPAIR)
public class ScheduleServiceMasterClientRepairStateTest extends BaseWebTest {
    @Browser(role = UserRole.CLIENT)
    ClientPages clientPages;

    @Test
    @DisplayName("Ремонт - в  состоянии согласование даты и времени - назначенный мастер СД")
    void scheduleServiceRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.SCHEDULE_SERVICE_MASTER;
        UserRole userRole = UserRole.CLIENT;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + userRole + " авторизация", () -> {
            clientPages.getLoginPage().open();
            clientPages.getLoginPage().login(client.getEmail(), "1111");
            clientPages.getHomePage().checkUrl();
            clientPages.getHomePage().guide.skipButton();
            step(userRole + " учетные данные", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });
        step(userRole + " кабинет в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(clientPages.getDriverManager(), state, stateInfo.getLastOrderInfoDto());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(userRole + " карточка заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto());
                });
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step(userRole + " уведомления - в состоянии " + state, () -> {
//                    clientPages.getHomePage().open();
//                    clientPages.getHomePage().checkFinishLoading();
//                    Selenide.sleep(3000);
//                    clientPages.getHomePage().header.actionsBlock.notifications();
//                    clientPages.getAllNotificationsPage().checkFinishLoading();
                    clientPages.getAllNotificationsPage().open();
                    clientPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
                });
            };
            Consumer<SoftAssert> case4 = softAssert -> {
                step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().redNotice.noNotice();
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step(userRole + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
                    clientPages.getLandingPage().open();
                    clientPages.getLandingPage().checkFinishLoading();
                    clientPages.getLandingPage().noticeComponent.noNotifications();
                });
            };
            assertAll(Arrays.asList(case1, case2, case3, case4, case5));
        });
    }
}