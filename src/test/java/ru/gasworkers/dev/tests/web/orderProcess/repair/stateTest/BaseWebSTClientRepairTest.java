package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

public class BaseWebSTClientRepairTest extends BaseWebTest {


    protected void loginDynamicClient(User client, UserRole userRole, ClientPages clientPages) {
        step("Web: " + userRole + " авторизация", () -> {
            clientPages.getLoginPage().open();
            Selenide.sleep(2000);
            clientPages.getLoginPage().login(client.getEmail(), "1111");
            clientPages.getHomePage().checkUrl();
            clientPages.getHomePage().guide.skipButton();
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

    //    @NotNull
    protected Consumer<SoftAssert> lasOrderCardCheck(UserRole userRole, StateRepair state, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> case1 = softAssert -> {
            step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
                clientPages.getHomePage().open(clientToken);
                Selenide.sleep(2000);
                clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                clientPages.getHomePage().lastOrderComponent.checkState(clientPages.getDriverManager(), state, stateInfo.getLastOrderInfoDto(), clientPages);
            });
        };
        return case1;
    }

    //    @NotNull
    protected Consumer<SoftAssert> selectServicePageCheck(UserRole userRole, StateRepair state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> case3 = softAssert -> {
            step(userRole + " страница выбора услуги - в состоянии " + state, () -> {
                clientPages.getSelectServicePage().openRepair(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getSelectServicePage().checkState(state, stateInfo.getSuggestedServiceDto());
            });
        };
        return case3;
    }

    //    @NotNull
    protected Consumer<SoftAssert> redirectToSelectServicePageCheck(UserRole userRole, StateRepair state, String clientToken, ClientPages clientPages) {
        Consumer<SoftAssert> case2 = softAssert -> {
            step(userRole + " карточка заказа редирект на карту - в состоянии " + state, () -> {
                clientPages.getHomePage().open(clientToken);
                Selenide.sleep(2000);
                clientPages.getHomePage().checkRedirectOrderCardToSelectService(state, clientPages);

            });
        };
        return case2;
    }

    //    @NotNull
    protected Consumer<SoftAssert> redNoticeLandingPageCheck(UserRole userRole, StateRepair state, String clientToken, ClientPages clientPages) {
        Consumer<SoftAssert> case7 = softAssert -> {
            clientPages.getLandingPage().open(clientToken);
            Selenide.sleep(2000);

            switch (state) {
                case ACTIONS_INVOICE_ISSUED:
                case MATERIAL_INVOICE_ISSUED:
                    step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                        clientPages.getLandingPage().noticeComponent.redNotice.checkInvoiceIssuedNotice();
                    });
                    break;
                case PUBLISHED:
                case HAS_SERVICE_OFFER:
                case HAS_SUPER_OFFER_SD_PROCESS:
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_PAID:
                    step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                        clientPages.getLandingPage().noticeComponent.redNotice.noNotice();
                    });
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        };
        return case7;
    }

    //    @NotNull
    protected Consumer<SoftAssert> redNoticeHomePageCheck(UserRole userRole, StateRepair state, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> case6 = softAssert -> {
            //red notice all across the client pages context
            clientPages.getHomePage().open(stateInfo.getCommonFields().getTokenClient());
            Selenide.sleep(2000);
            switch (state) {
                case ACTIONS_INVOICE_ISSUED:
                case MATERIAL_INVOICE_ISSUED:
                    step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                        clientPages.getHomePage().checkFinishLoading();
                        clientPages.getHomePage().redNotice.checkInvoiceIssuedNotice();
                    });
                    break;
                case PUBLISHED:
                case HAS_SERVICE_OFFER:
                case HAS_SUPER_OFFER_SD_PROCESS:
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_PAID:
                    step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                        clientPages.getHomePage().redNotice.noNotice();
                    });
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        };
        return case6;
    }

    //    @NotNull
    protected Consumer<SoftAssert> allNotificationPageCheck(UserRole userRole, StateRepair state, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> case5 = softAssert -> {
            step(userRole + " уведомления - в состоянии " + state, () -> {
                clientPages.getAllNotificationsPage().open(clientToken);
                Selenide.sleep(2000);
                clientPages.getAllNotificationsPage().checkStateRepair(state, stateInfo.getNotificationsDto());
            });
        };
        return case5;
    }

    //    @NotNull
    protected Consumer<SoftAssert> orderCardTabDocsCheck(UserRole userRole, StateRepair state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseD = softAssert -> {
            step(userRole + " карточка заказа - вкладка Документы - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().openRedirected(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabDocsStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto(), stateInfo.getTotalPriceResponseDto());
            });
        };
        return caseD;
    }

    //    @NotNull
    protected Consumer<SoftAssert> orderCardTabInfoMasterCheck(UserRole userRole, StateRepair state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseC = softAssert -> {
            step(userRole + " карточка заказа - вкладка Информация о заказе - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().openRedirected(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabInfoMasterStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseC;
    }

    //    @NotNull
    protected Consumer<SoftAssert> orderCardTabCommonCheck(UserRole userRole, StateRepair state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseB = softAssert -> {
            step(userRole + " карточка заказа - вкладка Общее - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().openRedirected(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkTabCommonStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseB;
    }

    //    @NotNull
    protected Consumer<SoftAssert> orderCardGeneralCheck(UserRole userRole, StateRepair state, CommonFieldsDto commonFields, String clientToken, StateInfo stateInfo, ClientPages clientPages) {
        Consumer<SoftAssert> caseA = softAssert -> {
            step(userRole + " карточка заказа - генеральная информация - в состоянии " + state, () -> {
                clientPages.getOrderCardPage().openRedirected(commonFields.getOrderNumber(), clientToken);
                Selenide.sleep(2000);
                clientPages.getOrderCardPage().checkGeneralStateRepair(userRole, state, stateInfo.getOrdersIdResponseDto());
            });
        };
        return caseA;
    }
}
