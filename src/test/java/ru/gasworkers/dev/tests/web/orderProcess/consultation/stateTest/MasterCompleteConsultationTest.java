package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTest;

import com.codeborne.selenide.Selenide;
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
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.PreconditionConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;

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
public class MasterCompleteConsultationTest extends BaseWebTest {

    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Test
    @DisplayName("Консультация - в состоянии masterCompleteConsultation")
    void masterCompleteConsultation(@WithClient(houses = {@WithHouse}) User client) {
        StateConsultation state = StateConsultation.MASTER_COMPLETE_CONSULTATION;
        UserRole userRole = UserRole.CLIENT;

        PreconditionConsultation preconditionConsultation = new PreconditionConsultation();
        PreconditionConsultation.Result result = preconditionConsultation.applyPrecondition(client, state);
// Get the StateInfo and CommonFieldsDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
//        ----------------------------  UI  --------------------------------
        step("WEB: авторизация Ролей ", () -> {
            step(userRole + " авторизация", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(String.valueOf(commonFields.getClientPhone()), "1234");
                clientPages.getHomePage().checkUrl();

            });
            step(userRole + " test run credentials ", () -> {
                Allure.addAttachment("Client creds", commonFields.getClientPhone() + ": " + "1234" + "/");
                Allure.addAttachment("Master creds", commonFields.getMasterEmail() + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });

        step(userRole + " кабинет в состоянии - в состоянии " + state, () -> {

            Consumer<SoftAssert> case1 = softAssert -> {
                step(userRole + " карточка последнего заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkState(clientPages.getDriverManager(), state, stateInfo.getLastOrderInfoDto());
                });
            };

            Consumer<SoftAssert> case2 = softAssert -> {
                step(userRole + " компонент баннер ВК в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().consultationNotification.checkState(state);
                });
            };

            Consumer<SoftAssert> case3 = softAssert -> {
                step(userRole + " кнопка на дом стр Заполнить профиль - в состоянии " + state, () -> {
                    clientPages.getHomePage().checkFillProfileButton();
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(userRole + " карточка заказа - в состоянии " + state, () -> {
                    clientPages.getOrderCardPage().open(String.valueOf(commonFields.getOrderId()));
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkStateConsultation(state, stateInfo.getOrdersIdResponseDto());
                });
            };

            Consumer<SoftAssert> case5 = softAssert -> {
                step(userRole + " уведомления - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    Selenide.sleep(3000);
                    clientPages.getHomePage().header.actionsBlock.notifications();
                    clientPages.getAllNotificationsPage().checkFinishLoading();
                    clientPages.getAllNotificationsPage().checkStateConsultation(state, stateInfo.getNotificationsDto());
                });
            };

            Consumer<SoftAssert> case6 = softAssert -> {
                step(userRole + " красное уведомление в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().redNotice.noNotice();
                });
            };
//            Consumer<SoftAssert> case7 = softAssert -> {
//                step(userRole + "  стр лендинга - в состоянии " + state, () -> {
//                    clientPages.getHomePage().open();
//                    clientPages.getHomePage().checkFinishLoading();
//                    clientPages.getHomePage().header.clickLogo();
//                    clientPages.getLandingPage().checkFinishLoading();
//                    clientPages.getLandingPage().checkStateConsultation(state);
//                });
//            };
            assertAll(Arrays.asList(case1,
                    case2, case3,
                    case4, case5, case6
//                    , case7
            ));
        });

    }
}



