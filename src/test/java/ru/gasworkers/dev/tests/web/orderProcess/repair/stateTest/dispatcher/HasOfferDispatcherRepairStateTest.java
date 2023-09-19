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
public class HasOfferDispatcherRepairStateTest extends BaseWebTest {
    @Browser(role = Role.DISPATCHER, browserSize = "1920x1080")
    DispatcherPages dispatcherPages;

    @Test
    @DisplayName("Ремонт - в состоянии есть отклик СК")
    void hasOfferRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.HAS_OFFER;
        Role role = Role.DISPATCHER;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsRepairDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
        preconditionRepair.readAllNotifications(commonFields.getTokenDispatcher());

//    ------------------------------------------------- UI -----------------------------------------------------------
        step("Web: " + role + " авторизация", () -> {
            dispatcherPages.getLoginPage().open();
            dispatcherPages.getLoginPage().login(commonFields.getDispatcherEmail(), commonFields.getDispatcherPassword());
            dispatcherPages.getHomePage().checkUrl();
            step(role + " учетные данные", () -> {
                Allure.addAttachment(role + " creds: ", commonFields.getDispatcherEmail() + ": " + commonFields.getDispatcherPassword());
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });

        step(role + " кабинет  в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(role + " стр карта - в состоянии " + state, () -> {
                    dispatcherPages.getHomePage().checkFinishLoading();
                    dispatcherPages.getDriverManager().screenshot(" стр карта - в состоянии " + state);
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(role + " карточка заказа - в состоянии " + state, () -> {
                    dispatcherPages.getOrderCardPage().open(String.valueOf(commonFields.getOrderId()));
                    dispatcherPages.getOrderCardPage().checkUrl(String.valueOf(commonFields.getOrderId()));
                    dispatcherPages.getDriverManager().screenshot(" стр карточка заказа - в состоянии " + state);
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(role + " уведомления - в состоянии " + state, () -> {
                    dispatcherPages.getAllNotificationsPage().open();
                    dispatcherPages.getAllNotificationsPage().checkUrl();
                    dispatcherPages.getAllNotificationsPage().checkFinishLoading();
                    dispatcherPages.getDriverManager().screenshot(" стр уведомления - в состоянии " + state);
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step(role + " красное уведомление на карте  - в состоянии " + state, () -> {

                });
            };
            Consumer<SoftAssert> case6 = softAssert -> {
                step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {

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