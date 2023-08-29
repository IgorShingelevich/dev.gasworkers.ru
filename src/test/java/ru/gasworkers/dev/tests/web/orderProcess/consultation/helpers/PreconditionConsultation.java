package ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseRequestDto;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentRequestDto;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.sign.OrdersSendSignApi;
import ru.gasworkers.dev.api.orders.sign.OrdersSignApi;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
import ru.gasworkers.dev.api.users.notification.NotificationsApi;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsApi;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonRequestDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateInfo;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionConsultation extends BaseApiTest {

    public final StateInfo stateInfo = new StateInfo();
    private final RepairTestCase repairCase = new RepairTestCase();
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();


    private final NotificationsApi notificationsApi = new NotificationsApi();
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final OrdersApproveDateApi ordersApproveDateApi = new OrdersApproveDateApi();
    private final SaveCheckListApi saveCheckListApi = new SaveCheckListApi();
    private final OrdersMaterialValuesApi ordersMaterialValuesApi = new OrdersMaterialValuesApi();
    private final OrdersSaveMaterialValuesApi ordersSaveMaterialValuesApi = new OrdersSaveMaterialValuesApi();
    private final OrdersActionsApi ordersActionsApi = new OrdersActionsApi();
    private final OrdersSaveActionsApi ordersSaveActionsApi = new OrdersSaveActionsApi();
    private final OrdersCreateActApi ordersCreateActApi = new OrdersCreateActApi();
    private final OrdersSendSignApi ordersSendSignApi = new OrdersSendSignApi();
    private final OrdersSignApi ordersSignApi = new OrdersSignApi();
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final String sssrMaster1Email = "test_gas_master_sssr1@rambler.ru";
    private final String sssrMaster1Password = "1234";

    private StateInfo stateInfoResult;
    private CommonFieldsDto commonFieldsResult;

    public Result applyPrecondition(User client, StateConsultation stateConsultation) {
        return step("API: Видеоконсультация сейчас предусловие: " + stateConsultation, () -> {
            stateInfo.setCommonFields(commonFields);
            switch (stateConsultation) {
                case CLIENT_WAIT_MASTER:
                    applyClientWaitMasterPrecondition(stateConsultation, client, commonFields);
                    break;
                case MASTER_START_CONSULTATION:
                    applyMasterStartConsultationPrecondition(stateConsultation, client, commonFields);
                    break;
                case MASTER_FILLED_CONCLUSION:
                    applyMasterFilledConclusionPrecondition(stateConsultation, client, commonFields);
                    break;
                case COMPLETED:
                    applyCompletedPreconditionUser(stateConsultation, client, commonFields);
                    break;

                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
            getActualDtoSet(commonFields, stateConsultation);
            StateInfo result = stateInfo.actualDtoSet();
            readAllNotifications();
            return new Result(result, commonFields);
        });
    }


    private void readAllNotifications() {
        notificationsApi.readAllNotifications(commonFields.getTokenClient())
                .statusCode(200);
    }

    private void applyClientWaitMasterPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            commonFields.setTokenClient(loginApi.getTokenPhone(client));
            commonFields.setClientObjectId(clientHousesApi.houseId(client, commonFields.getTokenClient()));
            step("Add equipment", () -> {
                addEquipmentApi.addEquipment(AddEquipmentRequestDto.defaultBoilerEquipment(), commonFields.getClientObjectId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(AddEquipmentResponseDto.class);
            });
            commonFields.setOrderId(createOrdersApi.createOrder(CreateOrderRequestDto.builder()
                            .type("consultation")
                            .houseId(String.valueOf(commonFields.getClientObjectId()))
                            .build(), commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class).getData().getOrderId());

            GetClientHousesResponseDto actualResponse = step("Get client objects", () -> {
                return getClientHousesApi.getHouse(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(GetClientHousesResponseDto.class);
            });

            GetClientHousesResponseDto.DataDto firstData = actualResponse.getData()[0];
            Integer firstEquipmentId = firstData.getEquipments()[0].getId();

            List<Integer> equipmentList = new ArrayList<>();
            for (GetClientHousesResponseDto.Equipment equipment : firstData.getEquipments()) {
                equipmentList.add(equipment.getId());
            }
            commonFields.setObjectId(firstData.getId());
            System.out.println("equipmentList = " + equipmentList);
            System.out.println("firstEquipmentId = " + firstEquipmentId);
            System.out.println("actualObjectId = " + commonFields.getClientObjectId());

            step("Select object", () -> {
                selectHouseApi.selectObject(SelectHouseRequestDto.builder()
                                .clientObjectId(commonFields.getClientObjectId())
                                .orderId(commonFields.getOrderId())
                                .equipment(equipmentList)
                                .build(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectHouseResponseDto.class);
            });


            List<Integer> masterIdList = step("Get online masters", () -> {
                String responseString = onlineMastersApi.getOnlineMasters(OnlineMastersRequestDto.builder()
                                .orderId(commonFields.getOrderId())
                                .search("rating")
                                .build(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().asString();
                JsonObject responseObject = JsonParser.parseString(responseString).getAsJsonObject();
                JsonArray dataArray = responseObject.getAsJsonArray("data");
                List<Integer> currentMasterIds = new ArrayList<>();
                for (JsonElement element : dataArray) {
                    JsonObject masterObject = element.getAsJsonObject();
                    int masterId = masterObject.get("id").getAsInt();
                    currentMasterIds.add(masterId);
                }
                commonFields.setMasterId(currentMasterIds.get(0));
                System.out.println("First master id: " + commonFields.getMasterId());
                return currentMasterIds;
            });

            Integer timetableId = step("Pick master", () -> {
                return selectConsultationMasterApi.selectMaster(PickMasterRequestDto.builder()
                                        .orderId(commonFields.getOrderId())
                                        .online(true)
                                        .build(),
                                masterIdList.get(0), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
            });

            step("Apply master", () -> {
                commonFields.setReceipts0Id(applyMasterApi.applyMaster(ApplyMasterRequestDto.builder()
                                        .orderId(commonFields.getOrderId())
                                        .timetableId(timetableId)
                                        .description("test description")
                                        .now(true)
                                        .build(),
                                commonFields.getMasterId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(ApplyMasterResponseDto.class).getData().getReceiptId());
            });

            SelectPaymentResponseDto selectPaymentResponse = step("Select payment", () -> {
                return selectPaymentApi.selectPayment(SelectPaymentRequestDto.builder()
                                .orderId(commonFields.getOrderId())
                                .receiptId(commonFields.getReceipts0Id())
                                .type("fps")
                                .build(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
            });

            step("Get master credentials", () -> {
                commonFields.setTokenAdmin(loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken());
                commonFields.setMasterEmail(getUserWithAdminApi.getUserWithAdmin(commonFields.getTokenAdmin(), commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class).getData().getEmail());
            });

            commonFields.setClientPhone(Long.valueOf(client.getPhone()));
            String phone2 = ComplexRegistrationRequestDto.builder()
                    .phone(client.getPhone())
                    .build().getPhone();
            System.out.println("phone = " + client.getPhone());
            System.out.println("phone2 = " + phone2);


           /* step(Role.CLIENT + " заказ на ВК сейчас - в состоянии " + stateConsultation, () -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + stateConsultation, () -> {
                    System.out.println("publishedLastOrderInfo");
                    LastOrderInfoResponseDto lastOrderDto = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(lastOrderDto.getData().getId());
                    commonFields.setOrderNumber(lastOrderDto.getData().getNumber());
                    commonFields.setClientObjectId(lastOrderDto.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(lastOrderDto.getData().getEquipments().get(0).getId());
                    stateInfo.setLastOrderInfoDto(lastOrderDto);
                });
            });
            step(Role.CLIENT + " получает список доступных предложений", () -> {
                SuggestServicesResponseDto suggestServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = suggestServiceDto.getData().getServices();
                // Filter companies with non-null prices
                List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                for (SuggestServicesResponseDto.Service c : services) {
                    if (c.getPrice() != null) {
                        filteredServices.add(c);
                    }
                }
                stateInfo.setSuggestedServiceDto(suggestServiceDto);
            });*/
        });
    }

    private void applyMasterStartConsultationPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyClientWaitMasterPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.CLIENT + " заполнение профиля после Фоновой Регистрации", () -> {
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
            step(Role.DISPATCHER + " выбирает мастера ", () -> {
                step(Role.DISPATCHER + " авторизуется", () -> {
                    commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
                });
                step(Role.DISPATCHER + " получает список доступных мастеров ", () -> {
                    commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId());
                });
                step(Role.DISPATCHER + " подтверждает выбор первого мастера ", () -> {
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
            step(Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
                step(Role.CLIENT + " получает список доступных предложений", () -> {
                    SuggestServicesResponseDto suggestedServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(SuggestServicesResponseDto.class);
                    List<SuggestServicesResponseDto.Service> services = suggestedServiceDto.getData().getServices();
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
                    stateInfo.setSuggestedServiceDto(suggestedServiceDto);

                });
            });
        });
    }

    private void applyMasterFilledConclusionPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyMasterStartConsultationPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
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
            step("клиент оплачивает  активацию сделки", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
        });
    }

    private void applyCompletedPreconditionUser(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyMasterFilledConclusionPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.DISPATCHER + " подтверждает дату и время в состоянии" + stateConsultation, () -> {
                commonFields.setApproveDate(client.getApproveDate());
                OrdersApproveDateResponseDto actualResponse = ordersApproveDateApi.ordersApproveDate(repairCase.approveDateRequest(commonFields), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersApproveDateResponseDto.class);
            });
        });
    }

    private LastOrderInfoResponseDto getLastOrderInfoDto(CommonFieldsDto commonFields, StateConsultation state) {
        System.out.println(state + ": LastOrderInfo");
        return step("API: " + Role.CLIENT + " карточка последнего заказа - в состоянии " + state, () -> {
            return lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
        });
    }

    private OrdersIdResponseDto getOrdersIdDto(CommonFieldsDto commonFields, StateConsultation state) {
        System.out.println(state + ": OrdersId");
        return step("API: " + Role.CLIENT + " карточка заказа - в состоянии " + state, () -> {
            return ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(OrdersIdResponseDto.class);
        });
    }

    private NotificationsResponseDto getNotificationsDto(CommonFieldsDto commonFields, StateConsultation state) {
        System.out.println(state + ": Notifications");
        return step("API: " + Role.CLIENT + " уведомления - в состоянии " + state, () -> {
            return notificationsApi.getNotifications(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(NotificationsResponseDto.class);
        });
    }

    private void getActualDtoSet(CommonFieldsDto commonFields, StateConsultation stateConsultation) {
        stateInfo.setLastOrderInfoDto(getLastOrderInfoDto(commonFields, stateConsultation));
        stateInfo.setOrdersIdResponseDto(getOrdersIdDto(commonFields, stateConsultation));
        stateInfo.setNotificationsDto(getNotificationsDto(commonFields, stateConsultation));
    }

    @Getter
    @AllArgsConstructor
    public class Result {
        private StateInfo stateInfoResult;
        private CommonFieldsDto commonFieldsResult;
    }

}
