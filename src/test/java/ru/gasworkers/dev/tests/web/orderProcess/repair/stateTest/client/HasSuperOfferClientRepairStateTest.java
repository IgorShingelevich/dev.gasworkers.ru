package ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.client;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
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
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.PreconditionRepair;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateTest.BaseWebSTClientRepairTest;

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
public class HasSuperOfferClientRepairStateTest extends BaseWebSTClientRepairTest {
    @Browser(role = UserRole.CLIENT)
    ClientPages clientPages;

    @Test
    @DisplayName("Ремонт - в состоянии есть отклик СД")
    void hasSuperOfferRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        StateRepair state = StateRepair.HAS_SUPER_OFFER_SD_PROCESS;
        UserRole userRole = UserRole.CLIENT;
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        PreconditionRepair.Result result = preconditionRepair.applyPrecondition(client, state);
        String clientToken = result.getCommonFieldsResult().getTokenClient();
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();
//    ------------------------------------------------- UI -----------------------------------------------------------
        loginDynamicClient(client, userRole, clientPages);
        webAttachments(client, userRole, commonFields, clientToken);
        step(userRole + " кабинет в состоянии - в состоянии " + state, () -> {
            Consumer<SoftAssert> case1 = lasOrderCardCheck(userRole, state, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case2 = redirectToSelectServicePageCheck(userRole, state, clientToken, clientPages);
            Consumer<SoftAssert> case3 = selectServicePageCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case4 = ordersIdGeneralCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case5 = ordersIdTabCommonCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case6 = ordersIdTabInfoMasterCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case7 = ordersIdTabDocsCheck(userRole, state, commonFields, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case8 = alNotificationPageCheck(userRole, state, clientToken, stateInfo, clientPages);
            Consumer<SoftAssert> case9 = redNoticeHomePageCheck(userRole, state, stateInfo, clientPages);
            Consumer<SoftAssert> case10 = redNoticeLandingPageCheck(userRole, state, clientToken, clientPages);
            assertAll(Arrays.asList(case1, case2, case3, case4, case5, case6, case7, case8, case9, case10, case8, case9, case10));
        });
    }
}