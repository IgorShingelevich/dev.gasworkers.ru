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
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
import ru.gasworkers.dev.api.users.settings.UserSettingsApi;
import ru.gasworkers.dev.api.users.settings.UserSettingsCommonRequestDto;
import ru.gasworkers.dev.api.users.settings.UserSettingsCommonResponseDto;
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
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB_REPAIR)
public class ScheduleDateRepairTest extends BaseApiTest {
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
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

    LastOrderInfoResponseDto scheduleTimeLastOrderResponse;
    OrdersIdResponseDto scheduleTimeOrderIdResponseDispatcher;
    OrdersIdResponseDto scheduleTimeOrderIdResponseClient;


    @Test
    @DisplayName("Ремонт - в  состоянии согласование даты и времени")
    void scheduleDateRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        CommonFieldsRepairDto commonFields = new CommonFieldsRepairDto();
        step("api precondition", () -> {
            commonFields.setTokenClient(loginApi.getTokenThrough(client));
            step("клиент заказ на ремонт клиента в  состоянии published", () -> {
                step("клиент карточка последнего заказа", () -> {
                    System.out.println("publishedLastOrderInfo");
                    LastOrderInfoResponseDto publishedLastOrderInfo = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(publishedLastOrderInfo.getData().getId());
                    commonFields.setOrderNumber(publishedLastOrderInfo.getData().getNumber());
                    commonFields.setClientObjectId(publishedLastOrderInfo.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(publishedLastOrderInfo.getData().getEquipments().get(0).getId());
                });
                step("клиент - заполнение профиля после Фоновой Регистрации", () -> {
                    UserSettingsCommonResponseDto actualResponse = userSettingsApi.setCommon(UserSettingsCommonRequestDto.defaultBGClientRequest(client), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(UserSettingsCommonResponseDto.class);
                    commonFields.setPassportId(actualResponse.getData().getPassport().getId());
                    commonFields.setClientId(actualResponse.getData().getId());
                    UserSettingsCommonResponseDto expectedResponse = UserSettingsCommonResponseDto.defaultBGUserResponse(client, commonFields);
                    expectedResponse.getData().getGuides().get(0).setId(actualResponse.getData().getGuides().get(0).getId());
                    expectedResponse.getData().getPassport().setIssuedDate(actualResponse.getData().getPassport().getIssuedDate());
                    //todo assert клиент - модель пользователя в  состоянии после заполнения профиля
                    // todo change all  dto to include  user details
                });
            });

            step("диспетчер выбирает мастера ", () -> {
                step("диспетчер авторизуется", () -> {
                    commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
                });
                step("диспетчер получает список доступных мастеров ", () -> {
                    commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId());
                });
                step("диспетчер подтверждает выбор первого мастера ", () -> {
                    selectMasterApi.selectMaster(commonFields.getOrderId(), commonFields.getMasterId(), commonFields.getTokenDispatcher())
                            .statusCode(200);
                });
            });
            step("Получение учетных данных  выбранного мастера за  роль администратора", () -> {
                String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken();
                System.out.println("getUserWithAdmin: " + commonFields.getMasterId());
                UserResponseDto masterDto = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setMasterEmail(masterDto.getData().getEmail());
            });

            step("клиент заказ на ремонт клиента в состоянии есть отклик СК", () -> {
                step(" клиент карточка последнего заказа - есть отклик СК", () -> {
                    System.out.println("hasOfferLastOrderInfo");
                    LastOrderInfoResponseDto hasOfferLastOrderInfo = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(hasOfferLastOrderInfo.getData().getId());
                });
                step("клиент карточка заказа - есть отклик СК", () -> {
                    System.out.println("hasOfferOrderInfo");
                    OrdersInfoResponseDto hasOfferOrderInfo = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersInfoResponseDto.class);
                    commonFields.setOfferIdHasOfferClient(hasOfferOrderInfo.getData().getOffer().getId());
                });
                step("клиент получает список доступных предложений", () -> {
                    SuggestServicesResponseDto suggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
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
                    assertThat(filteredServices).isNotEmpty();
                    commonFields.setServiceId(filteredServices.get(0).getId());
                    commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());
                });
                step("клиент выбирает предложение", () -> {
                    SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderId(), commonFields.getServiceId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(SelectServiceCompanyResponseDto.class);
                    commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
                    SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(commonFields.getReceipts0Id()).build();
                });
                step("клиент получает список банков на оплату", () -> {
                    FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(FspBankListResponseDto.class);
                    Integer availableBanks = actualResponse.getData().size();
                    System.out.println("availableBanks = " + availableBanks);
                });
            });
            step("заказ на ремонт - в  состоянии согласование даты и времени", () -> {
                step("клиент оплачивает  выезд мастера", () -> {
                    SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(SelectPaymentResponseDto.class);
                    commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                    commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
                });
                step("клиент карточка заказа - в  состоянии согласование даты и времени", () -> {
                    System.out.println("scheduleTimeOrderIdResponseAsClient");
                    scheduleTimeOrderIdResponseClient = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                });
                step(" клиент карточка последнего заказа - в  состоянии согласование даты и времени", () -> {
                    System.out.println("scheduleTimeLastOrderResponseAsClient");
                    scheduleTimeLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                });
                step("диспетчер карточка заказа - в  состоянии согласование даты и времени", () -> {
                    System.out.println("scheduleTimeOrderIdResponseAsDispatcher");
                    commonFields.setApproveDate(client.getApproveDate());
                    scheduleTimeOrderIdResponseDispatcher = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                });
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
          /*  step("авторизация Диспетчера", () -> {
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

        step(Role.CLIENT + " кабинет в состоянии - в состоянии " + StateRepair.SCHEDULE_DATE, () -> {
            Consumer<SoftAssert> case1 = softAssert -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.SCHEDULE_DATE, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(StateRepair.SCHEDULE_DATE, scheduleTimeLastOrderResponse);
                });
            };
            Consumer<SoftAssert> case2 = softAssert -> {
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.SCHEDULE_DATE, () -> {
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(StateRepair.SCHEDULE_DATE, scheduleTimeOrderIdResponseClient);
                });
            };
            Consumer<SoftAssert> case3 = softAssert -> {
                step("диспетчер карточка заказа - в  состоянии согласование даты и времени", () -> {
                });
            };
            Consumer<SoftAssert> case4 = softAssert -> {
                step("диспетчер карта - в  состоянии согласование даты и времени", () -> {
                });
            };
            Consumer<SoftAssert> case5 = softAssert -> {
                step("мастер список заказов - в  состоянии согласование даты и времени", () -> {
                });
            };
            assertAll(Arrays.asList(case1, case2, case3));
        });
    }
}