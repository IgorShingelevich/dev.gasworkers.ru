package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.dispatcher;

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
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
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
@Story(AllureStory.REPAIR_STATE_DISPATCHER)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.DISPATCHER)
@Tag(AllureTag.WEB_REPAIR)
public class ScheduleDateDispatcherRepairStateTest extends BaseWebTest {
    @Browser(role = UserRole.DISPATCHER)
    DispatcherPages dispatcherPages;

    @Disabled
    @Test
    @DisplayName("Ремонт - в  состоянии согласование даты и времени")
    void scheduleDateRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS;
        UserRole userRole = UserRole.DISPATCHER;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsRepairDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
        preconditionRepair.readAllNotifications(commonFields.getTokenDispatcher());

//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + userRole + " авторизация", () -> {
            dispatcherPages.getLoginPage().open();
            dispatcherPages.getLoginPage().login(commonFields.getDispatcherEmail(), commonFields.getDispatcherPassword());
            dispatcherPages.getHomePage().checkUrl();
            step(userRole + " учетные данные", () -> {
                Allure.addAttachment(userRole + " creds: ", commonFields.getDispatcherEmail() + ": " + commonFields.getDispatcherPassword());
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });

        step(userRole + " кабинет  в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(userRole + " стр карта - в состоянии " + state, () -> {
                    dispatcherPages.getHomePage().checkUrl();
                    dispatcherPages.getDriverManager().screenshot(" стр карта - в состоянии " + state);
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(userRole + " карточка заказа - в состоянии " + state, () -> {
                    dispatcherPages.getOrderCardPage().open(String.valueOf(commonFields.getOrderNumber()));
                    dispatcherPages.getOrderCardPage().checkUrl(String.valueOf(commonFields.getOrderNumber()));
                    dispatcherPages.getDriverManager().screenshot(" стр карточка заказа - в состоянии " + state);
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(userRole + " уведомления - в состоянии " + state, () -> {
                    dispatcherPages.getAllNotificationsPage().open();
                    dispatcherPages.getAllNotificationsPage().checkUrl();
                    dispatcherPages.getAllNotificationsPage().checkFinishLoading();
                    dispatcherPages.getDriverManager().screenshot(" стр уведомления - в состоянии " + state);
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step(userRole + " красное уведомление на карте  - в состоянии " + state, () -> {

                });
            };
            Consumer<SoftAssert> case6 = softAssert -> {
                step(userRole + " красное уведомление на стр лендинга - в состоянии " + state, () -> {

                });
            };
            assertAll(Arrays.asList(case1,
                    case2,
//                    case3,
                    case4
//                    , case5, case6
            ));
        });
    }

}