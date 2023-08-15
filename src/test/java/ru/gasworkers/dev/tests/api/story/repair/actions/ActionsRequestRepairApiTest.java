package ru.gasworkers.dev.tests.api.story.repair.actions;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.user.UserApi;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsResponseDto;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsResponseDto;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterResponseDto;
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
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("save actions")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.API)
public class ActionsRequestRepairApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final OrdersApproveDateApi ordersApproveDateApi = new OrdersApproveDateApi();
    private final SaveCheckListApi saveCheckListApi = new SaveCheckListApi();
    private final OrdersMaterialValuesApi ordersMaterialValuesApi = new OrdersMaterialValuesApi();
    private final OrdersSaveMaterialValuesApi ordersSaveMaterialValuesApi = new OrdersSaveMaterialValuesApi();
    private final OrdersActionsApi ordersActionsApi = new OrdersActionsApi();
    private final OrdersSaveActionsApi ordersSaveActionsApi = new OrdersSaveActionsApi();
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrMaster1Email = "test_gas_master_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final String sssrMaster1Password = "1234";
    private final RepairTestCase repairCase = new RepairTestCase();
    private OrdersIdResponseDto actionsInvoiceIssuedOrderIdResponseAsMaster;
    private LastOrderInfoResponseDto actionsInvoiceIssuedLastOrderResponse;
    private OrdersIdResponseDto actionsInvoiceIssuedOrderIdResponseAsClient;

    @ParameterizedTest(name = "{0}")
    @EnumSource(ActionsIssuedRepairCase.class)
    @DisplayName("save actions repair")
    void saveActionsRepair(ActionsIssuedRepairCase testCase, @WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        commonFields.setTokenClient(loginApi.getTokenThrough(client));
        step("заказ на ремонт в состоянии published", () -> {
            step("клиент - модель пользователя в  состоянии published ", () -> {
                UserResponseDto actualResponse = userApi.getUser(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setClientId(actualResponse.getData().getId());
                commonFields.setClientEmail(actualResponse.getData().getEmail());
                commonFields.setClientGuides0Id(actualResponse.getData().getGuides().get(0).getId());
                commonFields.setClientPhone(actualResponse.getData().getPhone());
                commonFields.setClientNotificationsCount(actualResponse.getData().getUserNotificationsCount());
            });
            step("клиент карточка последнего заказа - убедиться что в  состоянии published", () -> {
                LastOrderInfoResponseDto actualResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                commonFields.setOrderId(actualResponse.getData().getId());
                commonFields.setOrderNumber(actualResponse.getData().getNumber());
                commonFields.setClientObjectId(actualResponse.getData().getClientObject().getId());
                commonFields.setEquipments0Id(actualResponse.getData().getEquipments().get(0).getId());
            });
        });

        step("диспетчер выбирает мастера ", () -> {
            step("диспетчер авторизуется", () -> {
                commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
            });
            step("диспетчер карточка заказа - убедиться что в состоянии published", () -> {
                System.out.println("publishedOrderIdAsDispatcherResponse");
                OrdersIdResponseDto actualResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setClientRefererCode(actualResponse.getData().getClient().getRefererCode());
                commonFields.setClientRoles0PivotModelId(actualResponse.getData().getClient().getRoles().get(0).getPivot().getModelId());
                commonFields.setOfferIdPublishedByIdDispatcher(actualResponse.getData().getOffers().get(0).getId());
            });
            step("диспетчер получает список доступных мастеров ", () -> {
                commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId());
            });
            step("диспетчер подтверждает выбор первого мастера ", () -> {
                selectMasterApi.selectMaster(commonFields.getOrderId(), commonFields.getMasterId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(SelectMasterResponseDto.class);
            });
        });

        step("заказ на ремонт в состоянии hasOffer", () -> {
            step(" клиент карточка последнего заказа - убедиться что в состоянии hasOffer", () -> {
                LastOrderInfoResponseDto actualResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                commonFields.setActiveOffersCount(actualResponse.getData().getClientObject().getActiveOffersCount());
            });
            step("клиент карточка заказа - убедиться что в состоянии hasOffer", () -> {
                OrdersInfoResponseDto actualResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                commonFields.setOfferIdHasOfferClient(actualResponse.getData().getOffer().getId());
            });
            step("диспетчер карточка заказа - убедиться что в состоянии hasOffer", () -> {
                System.out.println("actualHasOfferOrderIdResponse");
                OrdersIdResponseDto actualResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setMasters0CompletedOrdersCount(actualResponse.data.getMasters().get(0).getCompletedOrdersCount());
                repairCase.hasOfferOrderIdBGRepairAsDispatcher(commonFields);
            });
            step("клиент получает список доступных предложений", () -> {
                SuggestServicesResponseDto suggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = suggestedServiceResponse.getData().getServices();
                List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                for (SuggestServicesResponseDto.Service c : services) {
                    if (c.getPrice() != null) {
                        filteredServices.add(c);
                    }
                }
                commonFields.setServiceId(filteredServices.get(0).getId());
                commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());
            });
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderId(), commonFields.getServiceId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
            });
            step("клиент получает список банков на оплату", () -> {
                fspBankListApi.fspBankList(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
            });
            step("клиент карточка заказа - убедиться что перед оплатой", () -> {
                System.out.println("beforePaymentOrderIdResponseAsClient");
                OrdersInfoResponseDto actualResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                commonFields.setOfferIdBeforePayment(actualResponse.getData().getOffer().getId());
            });
            step("клиент оплачивает  выезд мастера", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
        });

        step("заказ на ремонт в  состоянии Согласование даты и времени", () -> {
        });
        step("заказ на ремонт в  состоянии wait-for-master-starting", () -> {
            step("диспетчер подтверждает дату и время", () -> {
                commonFields.setApproveDate(client.getApproveDate());
                OrdersApproveDateResponseDto actualResponse = ordersApproveDateApi.ordersApproveDate(repairCase.approveDateRequest(commonFields), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersApproveDateResponseDto.class);
            });
            step("диспетчер карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrderIdResponseAsDispatcher");
                ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setSelectedDate(client.getSelectedDate());
            });
            step("мастер авторизуется", () -> {
                commonFields.setTokenMaster(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrMaster1Email, sssrMaster1Password)));
            });
        });

        step("заказ на ремонт в состоянии master-start-working", () -> {
            step("мастер сохраняет чеклист", () -> {
                SaveCheckListResponseDto actualResponse = saveCheckListApi.saveCheckList(repairCase.saveCheckListRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(SaveCheckListResponseDto.class);
            });
        });

        step("заказ на ремонт в состоянии material invoice issued", () -> {
            step("мастер создает таблицу материалов", () -> {
                OrdersMaterialValuesResponseDto actualResponse = ordersMaterialValuesApi.materialValuesTable(repairCase.materialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersMaterialValuesResponseDto.class);
            });
            step("мастер сохраняет таблицу материалов", () -> {
                OrdersSaveMaterialValuesResponseDto actualResponse = ordersSaveMaterialValuesApi.saveMaterialValues(repairCase.saveMaterialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveMaterialValuesResponseDto.class);
            });
            step("мастер карточка заказа - убедиться что material invoice issued", () -> {
                System.out.println("materialInvoiceIssuedOrderIdResponseAsMaster");
                OrdersIdResponseDto actualResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setReceipts1Id(actualResponse.getData().getReceipts().get(1).getId());
            });
        });

        step("заказ на ремонт в состоянии materialInvoicePaid", () -> {
            step("клиент оплачивает счет на Материалы", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment1Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment1Id(actualResponse.getData().getPaymentId());
            });
        });

        step("заказ на ремонт в состоянии actions-invoice-issued", () -> {
            step("мастер создает таблицу проведенных работ по заказу", () -> {
                System.out.println("actionsTableResponse");
                OrdersActionsResponseDto actualResponse = ordersActionsApi.actionsTable(repairCase.actionsRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersActionsResponseDto.class);
            });
            step("мастер сохраняет таблицу проведенных работ по заказу", () -> {
                System.out.println("saveActionsResponse");
                OrdersSaveActionsResponseDto actualResponse = ordersSaveActionsApi.saveActions(testCase.getRequestDto(commonFields.getOrderId()), commonFields.getTokenMaster())
//                        .statusCode(422)
                        .extract().as(OrdersSaveActionsResponseDto.class);
            });

            step("Assertions for responses", () -> {
                Executable assertion1 = step("клиент карточка последнего заказа - убедиться что actions-invoice-issued", () -> {
                    System.out.println("actionsInvoiceIssuedLastOrderResponse");
                    actionsInvoiceIssuedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    LastOrderInfoResponseDto expectedResponse = repairCase.actionsInvoiceIssuedLastOrderInfoBGRepair(commonFields);
                    return assertResponsePartialNoAtExecutable(expectedResponse, actionsInvoiceIssuedLastOrderResponse);
                });

                Executable assertion2 = step("клиент карточка заказа - убедиться что actions-invoice-issued", () -> {
                    System.out.println("actionsInvoiceIssuedOrderIdResponseAsClient");
                    actionsInvoiceIssuedOrderIdResponseAsClient = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                    OrdersIdResponseDto expectedResponse = repairCase.actionsInvoiceIssuedOrderIdAsClientBGRepair(commonFields);
                    return assertResponsePartialNoATExcludeFieldsExecutable(expectedResponse, actionsInvoiceIssuedOrderIdResponseAsClient, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
                });

                Executable assertion3 = step("мастер карточка заказа - убедиться что actions-invoice-issued", () -> {
                    System.out.println("actionsInvoiceIssuedOrderIdResponseAsMaster");
                    actionsInvoiceIssuedOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
//                    commonFields.setReceipts2Id(actionsInvoiceIssuedOrderIdResponseAsMaster.getData().getReceipts().get(2).getId());
                    OrdersIdResponseDto expectedResponse = repairCase.actionsInvoiceIssuedOrderIdAsMasterBGRepair(commonFields);
                    return assertResponsePartialNoATExcludeFieldsExecutable(expectedResponse, actionsInvoiceIssuedOrderIdResponseAsMaster, List.of("data.offers"));
                });

//                assertAll(assertion1, assertion2, assertion3);
            });


        });


    }
}
