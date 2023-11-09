package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
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
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.PreconditionConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;

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
public class DraftOnlineMastersConsultationTest extends BaseWebSTClientConsultationTest {

    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Test
    @DisplayName("Консультация - в состоянии draftOnlineMastersConsultation")
    void draftOnlineMastersConsultation(@WithClient(houses = {@WithHouse}) User client) {
        StateConsultation state = StateConsultation.DRAFT_ONLINE_MASTERS;
        UserRole userRole = UserRole.CLIENT;

        PreconditionConsultation preconditionConsultation = new PreconditionConsultation();
        PreconditionConsultation.Result result = preconditionConsultation.applyPrecondition(client, state);
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
        String clientToken = stateInfo.getCommonFields().getTokenClient();
//        ----------------------------  UI  --------------------------------
        loginDynamicClient(client, userRole, clientPages);
        webAttachments(client, userRole, commonFields, clientToken);
        step(userRole + " кабинет в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = lastOrderCardCheck(userRole, state, stateInfo, clientPages);
            Consumer<SoftAssert> case2 = bannerConsultationHomePageCheck(userRole, state, clientPages);
            Consumer<SoftAssert> case3 = fillProfileButtonHomePageCheck(userRole, state, clientPages);
            Consumer<SoftAssert> case4 = orderCardGeneralCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case5 = orderCardTabCommonCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case6 = orderCardTabInfoMasterCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case7 = orderCardTabDocsCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case8 = allNotificationPageCheck(userRole, state, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case9 = redNoticeHomePageCheck(userRole, state, stateInfo, clientPages);
            assertAll(Arrays.asList(case1, case2, case3, case4, case5, case6, case7, case8, case9));
        });
    }
}



