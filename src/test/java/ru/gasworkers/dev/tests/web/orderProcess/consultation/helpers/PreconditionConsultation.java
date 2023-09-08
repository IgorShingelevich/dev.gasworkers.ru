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
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.consultation.CodeByOrder.CodeByOrderConsultationApi;
import ru.gasworkers.dev.api.consultation.CodeByOrder.CodeByOrderConsultationRequest;
import ru.gasworkers.dev.api.consultation.CodeByOrder.CodeByOrderConsultationResponse;
import ru.gasworkers.dev.api.consultation.complete.CompleteConsultationApi;
import ru.gasworkers.dev.api.consultation.complete.CompleteConsultationRequest;
import ru.gasworkers.dev.api.consultation.complete.CompleteConsultationResponse;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.consultation.resume.ResumeConsultationApi;
import ru.gasworkers.dev.api.consultation.resume.ResumeConsultationRequest;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
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
import ru.gasworkers.dev.api.orders.sign.OrdersSendSignApi;
import ru.gasworkers.dev.api.orders.sign.OrdersSignApi;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.notification.NotificationsApi;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsApi;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateInfo;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

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
    private final CodeByOrderConsultationApi codeByOrderConsultationApi = new CodeByOrderConsultationApi();
    private final CompleteConsultationApi completeConsultationApi = new CompleteConsultationApi();
    private final ResumeConsultationApi resumeConsultationApi = new ResumeConsultationApi();


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
                case DRAFT_ONLINE_MASTERS:
                    applyDraftConsultationPrecondition(stateConsultation, client, commonFields);
                    break;
                case CLIENT_WAIT_MASTER:
                    applyClientWaitMasterPrecondition(stateConsultation, client, commonFields);
                    break;
                case MASTER_START_CONSULTATION:
                    applyMasterStartConsultationPrecondition(stateConsultation, client, commonFields);
                    break;
                case CLIENT_JOIN_CONSULTATION:
                    applyClientJoinConsultationPrecondition(stateConsultation, client, commonFields);
                    break;
                case MASTER_COMPLETE_CONSULTATION:
                    applyMasterCompleteConclusionPrecondition(stateConsultation, client, commonFields);
                    break;
                case MASTER_FILLED_RESUME:
                    applyMasterFilledResumePrecondition(stateConsultation, client, commonFields);
                    break;
                case ORDER_COMPLETED:
                    applyOrderCompletedPreconditionUser(stateConsultation, client, commonFields);
                    break;

                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
            getActualDtoSet(commonFields, stateConsultation);
            StateInfo result = stateInfo.actualDtoSet();
//            readAllNotifications();
            return new Result(result, commonFields);
        });
    }


    private void readAllNotifications() {
        notificationsApi.readAllNotifications(commonFields.getTokenClient())
                .statusCode(200);
    }

    private void applyDraftConsultationPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {

            commonFields.setTokenClient(loginApi.getTokenPhone(client));
            commonFields.setClientObjectId(clientHousesApi.houseId(client, commonFields.getTokenClient()));
            commonFields.setClientPhone(Long.valueOf(client.getPhone()));

            step(Role.CLIENT + "  add equipment", () -> {
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

            step(Role.CLIENT + "  select object for order", () -> {
                selectHouseApi.selectObject(SelectHouseRequestDto.builder()
                                .clientObjectId(commonFields.getClientObjectId())
                                .orderId(commonFields.getOrderId())
                                .equipment(equipmentList)
                                .build(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectHouseResponseDto.class);
            });
            List<Integer> masterIdList = step(Role.CLIENT + "  get online masters", () -> {
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
        });
    }

    private void applyClientWaitMasterPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyDraftConsultationPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            Integer timetableId = step(Role.CLIENT + " pick master", () -> {
                return selectConsultationMasterApi.selectMaster(PickMasterRequestDto.builder()
                                        .orderId(commonFields.getOrderId())
                                        .online(true)
                                        .build(),
                                commonFields.getMasterId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
            });
            step(Role.CLIENT + "  apply master", () -> {
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
            step(Role.ADMIN + " get master credentials", () -> {
                commonFields.setTokenAdmin(loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken());
                commonFields.setMasterEmail(getUserWithAdminApi.getUserWithAdmin(commonFields.getTokenAdmin(), commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class).getData().getEmail());
            });
        });
    }

    private void applyMasterStartConsultationPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyClientWaitMasterPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.MASTER + " авторизуется", () -> {
                commonFields.setTokenMaster(loginApi.getUserToken(LoginRequestDto.asUserEmail(commonFields.getMasterEmail(), "1234")));
            });
            step(Role.MASTER + " начинает ВК", () -> {
                System.out.println("codeByOrder masterStartConsultation");
                CodeByOrderConsultationResponse actualResponse = codeByOrderConsultationApi.codeByOrder(CodeByOrderConsultationRequest.builder()
                                .orderId(commonFields.getOrderId())
                                .build(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(CodeByOrderConsultationResponse.class);
            });
        });
    }

    private void applyClientJoinConsultationPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyMasterStartConsultationPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.CLIENT + " присоединяется к  ВК", () -> {
                System.out.println("codeByOrder clientJoinConsultation");
                CodeByOrderConsultationResponse actualResponse = codeByOrderConsultationApi.codeByOrder(CodeByOrderConsultationRequest.builder()
                                .orderId(commonFields.getOrderId())
                                .build(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(CodeByOrderConsultationResponse.class);
            });
        });
    }

    private void applyMasterCompleteConclusionPrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyClientJoinConsultationPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.MASTER + " завершает ВК", () -> {
                // Wait for 3 seconds  for starting record on  backend
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("completeConsultation");
                CompleteConsultationResponse actualResponse = completeConsultationApi.complete(CompleteConsultationRequest.builder()
                                .orderId(commonFields.getOrderId())
                                .build(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(CompleteConsultationResponse.class);
            });
        });
    }

    private void applyMasterFilledResumePrecondition(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyMasterCompleteConclusionPrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {
            step(Role.MASTER + " заполняет резюме", () -> {
                System.out.println("resumeConsultation");
                resumeConsultationApi.resume(ResumeConsultationRequest.builder()
                                .orderId(String.valueOf(commonFields.getOrderId()))
                                .description("test resume description")
                                .build(), commonFields.getTokenMaster())
                        .statusCode(200);
            });
        });
    }

    private void applyOrderCompletedPreconditionUser(StateConsultation stateConsultation, User client, CommonFieldsDto commonFields) {
        applyMasterFilledResumePrecondition(stateConsultation, client, commonFields);
        step("API: предусловие - " + Role.CLIENT + " заказ на ВК сейчас в состоянии " + stateConsultation, () -> {

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
