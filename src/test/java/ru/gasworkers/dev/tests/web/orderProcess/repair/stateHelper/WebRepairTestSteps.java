package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.web.BaseWebTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static io.qameta.allure.Allure.step;

public class WebRepairTestSteps extends BaseWebTest {
    ClientPages clientPages;

    void loginOnWeb(User client, UserRole userRole) {
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
    }

    void checkRedirectFromLastOrderToSelectServicePage(UserRole userRole, StateRepair state) {
        step(userRole + " карточка заказа редирект на карту - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.open();
            clientPages.getSelectServicePage().checkUrl();
        });
    }

    void checkLastOrderComponent(UserRole userRole, StateRepair state, StateInfo stateInfo) {
        step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.checkState(clientPages.getDriverManager(), state, stateInfo.getLastOrderInfoDto());
        });
    }

    void checkSelectServicePage(UserRole userRole, StateRepair state, StateInfo stateInfo) {
        step(userRole + " страница выбора услуги - в состоянии " + state, () -> {
            clientPages.getSelectServicePage().checkFinishLoadingRepair();
            clientPages.getSelectServicePage().checkState(state, stateInfo.getSuggestedServiceDto());
        });
    }

    void checkOrderCardPage(UserRole userRole, StateRepair state, StateInfo stateInfo) {
        step(userRole + " карточка заказа - в состоянии " + state, () -> {
            clientPages.getSelectServicePage().toOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkAllStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto(), stateInfo.getTotalPriceResponseDto());
        });
    }

    void checkNotificationsPage(UserRole userRole, StateRepair state, StateInfo stateInfo) {
        step(userRole + " уведомления - в состоянии " + state, () -> {
            clientPages.getHomePage().open(stateInfo.getCommonFields().getTokenClient());
            clientPages.getHomePage().checkFinishLoading();
            Selenide.sleep(3000);
            clientPages.getHomePage().header.actionsBlock.notifications();
            clientPages.getAllNotificationsPage().checkFinishLoading();
            clientPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
        });
    }

    void checkRedNotificationHomePage(UserRole userRole, StateRepair state) {
        step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
            clientPages.getHomePage().open(clientPages.getHomePage().getToken());
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().redNotice.noNotice();
        });
    }

    void checkRedNotificationLandingPage(UserRole userRole, StateRepair state) {
        step(userRole + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().noticeComponent.noNotifications();
        });
    }
}
