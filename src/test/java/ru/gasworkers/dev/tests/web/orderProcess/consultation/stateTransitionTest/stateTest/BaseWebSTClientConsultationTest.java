package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateTest;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

public class BaseWebSTClientConsultationTest extends BaseWebTest {

    protected void loginDynamicClient(User client, UserRole userRole, ClientPages clientPages) {
        step("Web: " + userRole + " авторизация", () -> {
            clientPages.getLoginPage().open();
            Selenide.sleep(2000);
//            clientPages.getLoginPage().login(client.getEmail(), "1234");
            clientPages.getLoginPage().login(String.valueOf(client.getPhone()), "1234");
            clientPages.getHomePage().checkUrl();
//            clientPages.getHomePage().guide.skipButton();
        });
    }

    protected void webAttachments(User client, UserRole userRole, CommonFieldsDto commonFields, String clientToken) {
        step(userRole + " учетные данные", () -> {
            Allure.addAttachment("Client creds: ", client.getEmail() + ": " + "1111" + "/");
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            Allure.addAttachment("Order id: ", String.valueOf(commonFields.getOrderNumber()));
            Allure.addAttachment("Client token: ", clientToken);
            Allure.addAttachment("RunStartTime: ", date);
        });
    }

    protected Consumer<SoftAssert> orderCardGeneralCheck(UserRole userRole, StateConsultation state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseA = softAssert -> {
            step(userRole + " карточка заказа - генеральная информация - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().open(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkGeneralStateConsultation(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseA;
    }

    protected Consumer<SoftAssert> orderCardTabCommonCheck(UserRole userRole, StateConsultation state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseB = softAssert -> {
            step(userRole + " карточка заказа - вкладка Общее - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().open(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabCommonStateConsultation(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseB;
    }

    protected Consumer<SoftAssert> orderCardTabInfoMasterCheck(UserRole userRole, StateConsultation state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseC = softAssert -> {
            step(userRole + " карточка заказа - вкладка Инфо о мастере - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().open(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabInfoMasterStateConsultation(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseC;
    }

    protected Consumer<SoftAssert> orderCardTabDocsCheck(UserRole userRole, StateConsultation state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseD = softAssert -> {
            step(userRole + " карточка заказа - вкладка Документы - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().open(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabDocsStateConsultation(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseD;
    }

    protected Consumer<SoftAssert> allNotificationPageCheck(UserRole userRole, StateConsultation state, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseE = softAssert -> {
            step(userRole + " уведомления - в состоянии " + state, () -> {
                clientPages.getAllNotificationsPage().open(clientToken);
                Selenide.sleep(2000);
                clientPages.getAllNotificationsPage().checkStateConsultation(state, stateInfo.getNotificationsDto());
            });
        };
        return caseE;
    }

    protected Consumer<SoftAssert> redNoticeHomePageCheck(UserRole userRole, StateConsultation state, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseF = softAssert -> {
            step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                clientPages.getHomePage().open(stateInfo.getCommonFields().getTokenClient());
                clientPages.getHomePage().checkFinishLoading();
                clientPages.getHomePage().redNotice.noNotice();
            });
        };
        return caseF;
    }

    protected Consumer<SoftAssert> fillProfileButtonHomePageCheck(UserRole userRole, StateConsultation state, ClientPages clientPages) {
        Consumer<SoftAssert> caseG = softAssert -> {
            step(userRole + " кнопка на дом стр Заполнить профиль - в состоянии " + state, () -> {
                clientPages.getHomePage().checkFillProfileButton();
            });
        };
        return caseG;
    }

    protected Consumer<SoftAssert> bannerConsultationHomePageCheck(UserRole userRole, StateConsultation state, ClientPages clientPages) {
        Consumer<SoftAssert> case2 = softAssert -> {
            step(userRole + " компонент баннер ВК в лк - в состоянии " + state, () -> {
                clientPages.getHomePage().consultationNotification.checkState(state);
            });
        };
        return case2;
    }

    protected Consumer<SoftAssert> lastOrderCardCheck(UserRole userRole, StateConsultation state, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> case1 = softAssert -> {
            step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
                clientPages.getHomePage().lastOrderComponent.checkState(clientPages.getDriverManager(), state, stateInfo.getLastOrderInfoDto(), clientPages);

            });
        };
        return case1;
    }
}
