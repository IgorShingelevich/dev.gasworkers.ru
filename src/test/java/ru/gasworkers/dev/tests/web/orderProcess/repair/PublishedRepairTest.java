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
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.settings.UserSettingsApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    //dispatcher
    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    DispatcherPages dispatcherPages;
    //master
    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    MasterPages masterPages;

    LastOrderInfoResponseDto publishedLastOrderInfo;
    OrdersIdResponseDto publishedOrderIdResponse;
    OrdersInfoResponseDto hasOfferOrderInfo;
    SuggestServicesResponseDto suggestedServiceResponse;


    @Test
    @DisplayName("Ремонт - в состоянии published")
    void publishedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        CommonFieldsRepairDto commonFields = new CommonFieldsRepairDto();
        step("api precondition", () -> {
            commonFields.setTokenClient(loginApi.getTokenThrough(client));
            step(Role.CLIENT + " заказ на ремонт - в состоянии " + StateRepair.PUBLISHED, () -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    System.out.println("publishedLastOrderInfo");
                    publishedLastOrderInfo = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(publishedLastOrderInfo.getData().getId());
                    commonFields.setOrderNumber(publishedLastOrderInfo.getData().getNumber());
                    commonFields.setClientObjectId(publishedLastOrderInfo.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(publishedLastOrderInfo.getData().getEquipments().get(0).getId());
                });
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    publishedOrderIdResponse = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                });
            });
            step(Role.CLIENT + " получает список доступных предложений", () -> {
                suggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = suggestedServiceResponse.getData().getServices();
                // Filter companies with non-null prices
                List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                for (SuggestServicesResponseDto.Service c : services) {
                    if (c.getPrice() != null) {
                        filteredServices.add(c);
                    }
                }
              /*  assertThat(filteredServices).isNotEmpty();
                commonFields.setServiceId(filteredServices.get(0).getId());
                commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());*/
            });
        });

//    ------------------------------------------------- UI -----------------------------------------------------------
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(client.getEmail(), "1111");
                clientPages.getHomePage().checkUrl();
                clientPages.getHomePage().guide.skipButton();
            });
           /* step("авторизация Диспетчера", () -> {
                dispatcherPages.getLoginPage()
                        .open()
                        .login(sssrDispatcher1Email, "1234");
                dispatcherPages.getHomePage().checkUrl();
            });
            step("авторизация Мастера", () -> {
                masterPages.getLoginPage()
                        .open()
                        .login(commonFields.getMasterEmail(), "1234");
                masterPages.getHomePage().checkUrl();
            });
            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                Allure.addAttachment("Master creds", sssrDispatcher1Email + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });*/
        });

        step(Role.CLIENT + " кабинет в состоянии - в состоянии " + StateRepair.PUBLISHED, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(StateRepair.PUBLISHED, publishedLastOrderInfo);
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
                    clientPages.getSelectServicePage().checkState(StateRepair.PUBLISHED, suggestedServiceResponse);
                });
            };
            Consumer<SoftAssert> case4 = softAssert -> {
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    clientPages.getSelectServicePage().toOrderCard();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(StateRepair.PUBLISHED, publishedOrderIdResponse);

                });
            };

            Consumer<SoftAssert> case5 = softAssert -> {
                step("диспетчер карта - в состоянии published", () -> {
                });
            };
            Consumer<SoftAssert> case6 = softAssert -> {
                step("мастер список заказов - в состоянии published", () -> {
                });
            };
            assertAll(Arrays.asList(case1, case2, case3, case4));

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
        });
    }
}