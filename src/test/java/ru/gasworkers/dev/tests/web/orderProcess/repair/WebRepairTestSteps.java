package ru.gasworkers.dev.tests.web.orderProcess.repair;

import io.qameta.allure.Allure;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.web.BaseWebTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static io.qameta.allure.Allure.step;

public class WebRepairTestSteps extends BaseWebTest {
    ClientPages clientPages;

    void loginOnWeb(User client, Role role) {
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
    }

    void checkRedirectFromLastOrderToSelectServicePage(Role role, StateRepair state) {
        step(role + " карточка заказа редирект на карту - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.open();
            clientPages.getSelectServicePage().checkUrl();
        });
    }

    void checkLastOrderComponent(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " карточка последнего заказа - в состоянии " + state, () -> {
            clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
            clientPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
        });
    }

    void checkSelectServicePage(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " страница выбора услуги - в состоянии " + state, () -> {
            clientPages.getSelectServicePage().checkFinishLoadingRepair();
            clientPages.getSelectServicePage().checkState(state, stateInfo.getSuggestedServiceDto());
        });
    }

    void checkOrderCardPage(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " карточка заказа - в состоянии " + state, () -> {
            clientPages.getSelectServicePage().toOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkStateRepair(state, stateInfo.getOrdersIdResponseDto());
        });
    }

    void checkNotificationsPage(Role role, StateRepair state, StateInfo stateInfo) {
        step(role + " уведомления - в состоянии " + state, () -> {
            clientPages.getAllNotificationsPage().open();
            clientPages.getAllNotificationsPage().checkFinishLoading();
            clientPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
        });
    }

    void checkRedNotificationHomePage(Role role, StateRepair state) {
        step(role + " красное уведомление в лк - в состоянии " + state, () -> {
            clientPages.getHomePage().open();
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().redNotice.noNotice();
        });
    }

    void checkRedNotificationLandingPage(Role role, StateRepair state) {
        step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().noticeComponent.noNotifications();
        });
    }
}
