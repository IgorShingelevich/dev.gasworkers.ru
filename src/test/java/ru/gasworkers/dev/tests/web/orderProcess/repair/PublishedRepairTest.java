package ru.gasworkers.dev.tests.web.orderProcess.repair;

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
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

import java.util.Arrays;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB_REPAIR)
public class PublishedRepairTest extends BaseApiTest {
    @Browser(role = Role.CLIENT)
    ClientPages clientPages;
    @Test
    @DisplayName("Ремонт - в состоянии published")
    void publishedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        CommonFieldsRepairDto commonFields = new CommonFieldsRepairDto();
        PreconditionRepair preconditionRepair = new PreconditionRepair();
        StateInfo stateInfo = preconditionRepair.applyPrecondition(client, StateRepair.PUBLISHED, commonFields);
//    ------------------------------------------------- UI -----------------------------------------------------------
        step("WEB " + Role.CLIENT + " авторизация", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(client.getEmail(), "1111");
                clientPages.getHomePage().checkUrl();
                clientPages.getHomePage().guide.skipButton();
        });
        step(Role.CLIENT + " кабинет в состоянии - в состоянии " + StateRepair.PUBLISHED, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(StateRepair.PUBLISHED, stateInfo.getPublishedLastOrderInfo());
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(Role.CLIENT + " карточка заказа редирект на карту - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getSelectServicePage().checkUrl();
                });
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step(Role.CLIENT + " страница выбора услуги - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getSelectServicePage().checkFinishLoadingRepair();
                    clientPages.getSelectServicePage().checkState(StateRepair.PUBLISHED, stateInfo.getPublishedSuggestedServiceResponse());
                });
            };
            Consumer<SoftAssert> case4 = softAssert -> {
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getSelectServicePage().toOrderCard();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(StateRepair.PUBLISHED, stateInfo.getPublishedOrderIdResponse());

                });
            };
            assertAll(Arrays.asList(case1, case2, case3, case4));
        });
    }
}

 /*  // Get the soft assertions from the page class
            List<Consumer<SoftAssert>> softAssertionsFromPageClass = clientPages.getOrderCardPage().checkStateList(StateRepair.PUBLISHED, publishedOrderIdResponse);
            List<Consumer<SoftAssert>> allSoftAssertions = new ArrayList<>();
            allSoftAssertions.addAll(Arrays.asList(case1, case2, case3));
            allSoftAssertions.addAll(softAssertionsFromPageClass);

            SoftAssert softAssert = new SoftAssert();
            for (Consumer<SoftAssert> assertion : allSoftAssertions) {
                assertion.accept(softAssert);
            }
            softAssert.assertAll();
*/
