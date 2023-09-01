package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.PreconditionConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Видеоконсультация")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB_CONSULTATION)
public class ClientWaitMasterConsultationTest extends BaseWebTest {

    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Test
    @DisplayName("Консультация - в состоянии clientWaitMaster")
    void clientWaitMaster(@WithClient(houses = {@WithHouse}) User client) {
        StateConsultation state = StateConsultation.CLIENT_WAIT_MASTER;
        Role role = Role.CLIENT;

        PreconditionConsultation preconditionConsultation = new PreconditionConsultation();
        PreconditionConsultation.Result result = preconditionConsultation.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
//        ----------------------------  UI  --------------------------------
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(String.valueOf(commonFields.getClientPhone()), "1234");
                clientPages.getHomePage().checkUrl();

            });

            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", commonFields.getClientPhone() + ": " + "1234" + "/");
                Allure.addAttachment("Master creds", commonFields.getMasterEmail() + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });


        step(role + " кабинет в состоянии - в состоянии " + state, () -> {

            Consumer<SoftAssert> case1 = softAssert -> {
                step(role + " карточка последнего заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
                });
            };

            Consumer<SoftAssert> case2 = softAssert -> {
                step(role + " компонент баннер ВК в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().consultationNotification.checkState(state);
                });
            };

            Consumer<SoftAssert> case3 = softAssert -> {
                step(role + " кнопка на дом стр Заполнить профиль - в состоянии " + state, () -> {
                    clientPages.getHomePage().checkFillProfileButton();
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(role + " карточка заказа - в состоянии " + state, () -> {
                    clientPages.getOrderCardPage().open(String.valueOf(commonFields.getOrderId()));
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkStateConsultation(state, stateInfo.getOrdersIdResponseDto());
                });
            };

            Consumer<SoftAssert> case5 = softAssert -> {
                step(role + " уведомления - в состоянии " + state, () -> {
                    clientPages.getAllNotificationsPage().open();
                    clientPages.getAllNotificationsPage().checkFinishLoading();
                    clientPages.getAllNotificationsPage().checkStateConsultation(state, stateInfo.getNotificationsDto());
                });
            };

            Consumer<SoftAssert> case6 = softAssert -> {
                step(role + " красное уведомление в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().redNotice.noNotice();
                });
            };

            Consumer<SoftAssert> case7 = softAssert -> {
                step(role + "  стр лендинга - в состоянии " + state, () -> {
                    clientPages.getLandingPage().open();
                    clientPages.getLandingPage().checkFinishLoading();
                    clientPages.getLandingPage().clickUserProfileSignIn();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().header.clickLogo();
                    clientPages.getLandingPage().checkFinishLoading();
                    clientPages.getLandingPage().checkStateConsultation(state);
                });
            };

            assertAll(Arrays.asList(case1,
                    case2, case3,
                    case4, case5, case6, case7));
        });

    }
}



