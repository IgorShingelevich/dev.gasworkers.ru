package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.companies.CompaniesSearchExecutorApi;
import ru.gasworkers.dev.api.orders.OLDselectMaster.OLDSelectMasterApi;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsResponseDto;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsResponseDto;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.cancel.cancelById.CancelOrderByIdApi;
import ru.gasworkers.dev.api.orders.cancel.cancelById.CancelOrderByIdRequestDto;
import ru.gasworkers.dev.api.orders.cancel.cancelOfferClient.CancelOfferClientApi;
import ru.gasworkers.dev.api.orders.cancel.moneyBack.MoneyBackApi;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListResponseDto;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActApi;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActResponseDto;
import ru.gasworkers.dev.api.orders.dispatcherPricing.DispatcherPricingAPI;
import ru.gasworkers.dev.api.orders.dispatcherPricing.DispatcherPricingRequestDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.makeOffer.MakeOfferApi;
import ru.gasworkers.dev.api.orders.makeOffer.MakeOfferRequestDto;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.sign.OrdersSendSignApi;
import ru.gasworkers.dev.api.orders.sign.OrdersSignApi;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSendSignResponseDto;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSignResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.orders.transfer.OrderTransferRequestDto;
import ru.gasworkers.dev.api.orders.transfer.OrdersTransferApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersResponseDto;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
import ru.gasworkers.dev.api.users.notification.NotificationsApi;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsApi;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonRequestDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionRepair extends BaseApiTest {

    public final StateInfo stateInfo = new StateInfo();
    private final RepairTestCase repairCase = new RepairTestCase();
    private final NotificationsApi notificationsApi = new NotificationsApi();
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final DispatcherPricingAPI dispatcherPricing = new DispatcherPricingAPI();
    private final MakeOfferApi makeOffer = new MakeOfferApi();
    private final OLDSelectMasterApi OLDSelectMasterApi = new OLDSelectMasterApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final CompaniesSearchExecutorApi companiesSearchExecutorApi = new CompaniesSearchExecutorApi();
    private final OrdersTransferApi ordersTransferApi = new OrdersTransferApi();
    private final OrdersApproveDateApi ordersApproveDateApi = new OrdersApproveDateApi();
    private final SaveCheckListApi saveCheckListApi = new SaveCheckListApi();
    private final OrdersMaterialValuesApi ordersMaterialValuesApi = new OrdersMaterialValuesApi();
    private final OrdersSaveMaterialValuesApi ordersSaveMaterialValuesApi = new OrdersSaveMaterialValuesApi();
    private final OrdersActionsApi ordersActionsApi = new OrdersActionsApi();
    private final CancelOfferClientApi cancelOfferClientApi = new CancelOfferClientApi();
    private final MoneyBackApi moneyBackApi = new MoneyBackApi();
    private final CancelOrderByIdApi cancelOrderByIdApi = new CancelOrderByIdApi();
    private final OrdersSaveActionsApi ordersSaveActionsApi = new OrdersSaveActionsApi();
    private final OrdersCreateActApi ordersCreateActApi = new OrdersCreateActApi();
    private final OrdersSendSignApi ordersSendSignApi = new OrdersSendSignApi();
    private final OrdersSignApi ordersSignApi = new OrdersSignApi();
    private final String
            superDispatcherEmail = "gw_test_superdispatcher@rambler.ru",
            superDispatcherPassword = "1234",
            sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru",
            sssrDispatcher1Password = "1234",
            sssrMaster1Email = "test_gas_master_sssr1@rambler.ru",
            sssrMaster1Password = "1234",
            sssrExecutorQuery = "sssr";

    CommonFieldsDto commonFields = new CommonFieldsDto();
    private StateInfo stateInfoResult;
    private CommonFieldsDto commonFieldsResult;

    public Result applyPrecondition(User client, StateRepair stateRepair) {
        return step("API: ремонт предусловие: " + stateRepair, () -> {
            stateInfo.setCommonFields(commonFields);
            switch (stateRepair) {
                case PUBLISHED:
                    applyPublishedPrecondition(stateRepair, client, commonFields);
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                    applyCancelClientPublishedPrecondition(stateRepair, client, commonFields);
                    break;
                case HAS_SUPER_OFFER_SD_PROCESS: // super dispatcher  made an offer, next supposed to  be SCHEDULE_SUPER_OFFER
                    applyHasSuperOfferPrecondition(stateRepair, client, commonFields);
                    break;
                case HAS_SERVICE_OFFER: // direct service  company processing
                    applyHasServiceOfferPrecondition(stateRepair, client, commonFields);
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS: // yellow state
                    applyClientPaidSuperActivationPrecondition(stateRepair, client, commonFields);
                    break;
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                    applySuperDispatcherAssignServicePrecondition(stateRepair, client, commonFields);
                    break;
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS: // service scheduled master next need to schedule time
                    applyServiceScheduleMasterPrecondition(stateRepair, client, commonFields);
                    break;
                case WAIT_SERVICE_MASTER_SD_PROCESS:  // dispatcher scheduled time, next supposed to be MASTER_START_WORK
                    applyWaitServiceMasterPreconditionUser(stateRepair, client, commonFields);
                    break;
                case MASTER_START_WORK:
                    applyMasterStartWorkPrecondition(stateRepair, client, commonFields);
                    break;
                case MATERIAL_INVOICE_ISSUED:
                    applyMaterialInvoiceIssuedPrecondition(stateRepair, client, commonFields);
                    break;
                case MATERIAL_INVOICE_PAID:
                    applyMaterialInvoicePaidPrecondition(stateRepair, client, commonFields);
                    break;
                case ACTIONS_INVOICE_ISSUED:
                    applyActionsInvoiceIssuedPrecondition(stateRepair, client, commonFields);
                    break;
                case ACTIONS_INVOICE_PAID:
                    applyActionsInvoicePaidPrecondition(stateRepair, client, commonFields);
                    break;
                case MASTER_SIGN_ACT:
                    applyMasterSignActPrecondition(stateRepair, client, commonFields);
                    break;
                case CLIENT_SIGN_ACT:
                    applyClientSignActPrecondition(stateRepair, client, commonFields);
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
            actualDto(commonFields, stateRepair);
            StateInfo result = stateInfo.actualDtoSet();
            readAllNotificationsClient();
            return new Result(result, commonFields);
        });
    }


    private void readAllNotificationsClient() {
        notificationsApi.readAllNotifications(commonFields.getTokenClient())
                .statusCode(200);
    }

    public void readAllNotifications(String token) {
        notificationsApi.readAllNotifications(token)
                .statusCode(200);
    }

    private void applyPublishedPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        StateRepair actualStateRepair = StateRepair.PUBLISHED;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.CLIENT + " авторизуется", () -> {
                commonFields.setTokenClient(loginApi.getTokenThrough(client));
            });
            step(UserRole.DISPATCHER + " авторизуется", () -> {
                commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
                step(UserRole.DISPATCHER + " получает список  доступных  мастеров", () -> {
                    getCompaniesMastersDto(commonFields.getTokenDispatcher());
                });
            });
            step(UserRole.SUPER_DISPATCHER + " авторизуется", () -> {
                commonFields.setTokenSuperDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(superDispatcherEmail, superDispatcherPassword)));
                step(UserRole.SUPER_DISPATCHER + " получает список  доступных  мастеров", () -> {
                    getCompaniesMastersDto(commonFields.getTokenSuperDispatcher());
                });
            });


            step(UserRole.CLIENT + " заказ на ремонт - в состоянии " + actualStateRepair, () -> {
                step(UserRole.CLIENT + " карточка последнего заказа - в состоянии " + actualStateRepair, () -> {
                    System.out.println("publishedLastOrderInfo");
                    LastOrderInfoResponseDto lastOrderDto = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderNumber(lastOrderDto.getData().getId());
                    commonFields.setOrderNumberFull(lastOrderDto.getData().getNumber());
                    commonFields.setClientObjectId(lastOrderDto.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(lastOrderDto.getData().getEquipments().get(0).getId());
                    stateInfo.setLastOrderInfoDto(lastOrderDto);
                });
            });

            step(UserRole.CLIENT + " заполнение профиля после Фоновой Регистрации", () -> {
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

            step(UserRole.CLIENT + " получает список доступных предложений", () -> {
                // sleep 3 sec
                Thread.sleep(3000);
                System.out.println("suggestServices");
                SuggestServicesResponseDto suggestServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderNumber(), commonFields.getTokenClient())
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
            });
        });
    }

    private void applyCancelClientPublishedPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyPublishedPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.CANCEL_CLIENT_PUBLISHED;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.CLIENT + " отменяет заказ", () -> {
                System.out.println("cancelOrderById");
                cancelOrderByIdApi.cancelOrderById(CancelOrderByIdRequestDto.defaultCancelRequest(),
                                commonFields.getOrderNumber(), commonFields.getTokenClient())
                        .statusCode(200);
            });
        });
    }

    private void applyHasServiceOfferPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyPublishedPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.HAS_SERVICE_OFFER;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.DISPATCHER + " расценивает офер и  выбирает мастера", () -> {
                step(UserRole.DISPATCHER + " получает список доступных мастеров ", () -> {
                    commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersResponseDto.class).getData().get(0).getId());
                });
                step(UserRole.DISPATCHER + " расценивает офер", () -> {
                    dispatcherPricing.dispatcherPricing(DispatcherPricingRequestDto.builder()
                                            .firstAcceptPrice(3000)
                                            .fullRepairPrice("3999")
                                            .masterId(commonFields.getMasterId())
                                            .build(),
                                    commonFields.getOrderNumber(), commonFields.getTokenDispatcher())
                            .statusCode(200);
                });
                step(UserRole.DISPATCHER + " делает офер", () -> {
                    makeOffer.makeOffer(MakeOfferRequestDto.builder()
                                            .orderId(commonFields.getOrderNumber())
                                            .build(),
                                    commonFields.getTokenDispatcher())
                            .statusCode(200);

                });
            });

            /*step(UserRole.DISPATCHER + " получает список доступных мастеров ", () -> {
                commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(CompaniesMastersResponseDto.class).getData().get(0).getId());
            });
            step("Получение учетных данных мастера назначенной СК за  роль администратора", () -> {
                String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken();
                System.out.println("getUserWithAdmin: " + commonFields.getMasterId());
                UserResponseDto masterDto = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setMasterEmail(masterDto.getData().getEmail());
            });*/

            step(UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
                step(UserRole.CLIENT + " получает список доступных предложений", () -> {
                    System.out.println("suggestServices");
                    SuggestServicesResponseDto suggestedServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderNumber(), commonFields.getTokenClient())
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

    private void applyHasSuperOfferPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyPublishedPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.HAS_SUPER_OFFER_SD_PROCESS;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.SUPER_DISPATCHER + " расценивает офер и  выбирает мастера", () -> {
                step(UserRole.SUPER_DISPATCHER + " получает список доступных мастеров ", () -> {
                    commonFields.setSuperMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenSuperDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersResponseDto.class).getData().get(0).getId());
                });
                step(UserRole.SUPER_DISPATCHER + " расценивает офер", () -> {
                    dispatcherPricing.dispatcherPricing(DispatcherPricingRequestDto.builder()
                                            .firstAcceptPrice(3999)
                                            .fullRepairPrice("4999")
                                            .masterId(commonFields.getSuperMasterId())
                                            .build(),
                                    commonFields.getOrderNumber(), commonFields.getTokenSuperDispatcher())
                            .statusCode(200);
                });
                step(UserRole.SUPER_DISPATCHER + " делает офер", () -> {
                    makeOffer.makeOffer(MakeOfferRequestDto.builder()
                                            .orderId(commonFields.getOrderNumber())
                                            .build(),
                                    commonFields.getTokenSuperDispatcher())
                            .statusCode(200);

                });
            });

            /*step(UserRole.DISPATCHER + " получает список доступных мастеров ", () -> {
                commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(CompaniesMastersResponseDto.class).getData().get(0).getId());
            });
            step("Получение учетных данных мастера назначенной СК за  роль администратора", () -> {
                String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken();
                System.out.println("getUserWithAdmin: " + commonFields.getMasterId());
                UserResponseDto masterDto = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setMasterEmail(masterDto.getData().getEmail());
            });*/

            step(UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
                step(UserRole.CLIENT + " получает список доступных предложений", () -> {
                    System.out.println("suggestServices");
                    SuggestServicesResponseDto suggestedServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderNumber(), commonFields.getTokenClient())
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
                    commonFields.setSuperServiceId(filteredServices.get(0).getId());
                    commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());
                    stateInfo.setSuggestedServiceDto(suggestedServiceDto);

                });
            });
        });

    }


    private void applyClientPaidSuperActivationPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyHasSuperOfferPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step("клиент выбирает предложение от СД", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderNumber(), commonFields.getSuperServiceId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
//                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(commonFields.getReceipts0Id()).build();
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
                Integer availableBanks = actualResponse.getData().size();
                System.out.println("availableBanks = " + availableBanks);
            });
            step("клиент оплачивает  активацию сделки", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderNumber(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
        });
    }

    private void applySuperDispatcherAssignServicePrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyClientPaidSuperActivationPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
           /* step(UserRole.SUPER_DISPATCHER + "  ищет  СК СССР для заказа по поиску", () -> {
                commonFields.setExecutorCompanyId(companiesSearchExecutorApi.searchExecutor(CompaniesSearchExecutorRequestDto.builder()
                                        .query(sssrExecutorQuery)
                                        .build(),
                                commonFields.getTokenSuperDispatcher())
                        .statusCode(200)
                        .extract().as(CompaniesSearchExecutorResponseDto.class).getData().get(0).getId());
            });*/
            step(UserRole.SUPER_DISPATCHER + " передает полностью заказ в СК СССР", () -> {
                ordersTransferApi.transfer(OrderTransferRequestDto.fullTransferRequest(commonFields.getOrderNumber(), 39), commonFields.getTokenSuperDispatcher())
                        .statusCode(200);
            });
        });
    }


    private void applyServiceScheduleMasterPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applySuperDispatcherAssignServicePrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.SERVICE_SCHEDULED_MASTER_SD_PROCESS;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.DISPATCHER + " назначает мастера для заказа", () -> {

                step(UserRole.DISPATCHER + " получает список доступных мастеров ", () -> {
                    commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersResponseDto.class).getData().get(0).getId());
                });

                step(UserRole.DISPATCHER + " расценивает офер", () -> {
                    dispatcherPricing.dispatcherPricing(DispatcherPricingRequestDto.builder()
                                            .firstAcceptPrice(5999)
                                            .fullRepairPrice("6999")
                                            .masterId(commonFields.getMasterId())
                                            .build(),
                                    commonFields.getOrderNumber(), commonFields.getTokenDispatcher())
                            .statusCode(200);
                });
               /* step(UserRole.DISPATCHER + " делает офер", () -> {
                    makeOffer.makeOffer(MakeOfferRequestDto.builder()
                                            .orderId(commonFields.getOrderNumber())
                                            .build(),
                                    commonFields.getTokenDispatcher())
                            .statusCode(200);

                });*/
            });

        });
    }

    private void applyWaitServiceMasterPreconditionUser(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyServiceScheduleMasterPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.WAIT_SERVICE_MASTER_SD_PROCESS;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.DISPATCHER + " подтверждает дату и время в состоянии" + actualStateRepair, () -> {
                commonFields.setApproveDate(client.getApproveDate());
                OrdersApproveDateResponseDto actualResponse = ordersApproveDateApi.ordersApproveDate(repairCase.approveDateRequest(commonFields), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersApproveDateResponseDto.class);
            });
        });
    }

    private void applyMasterStartWorkPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyWaitServiceMasterPreconditionUser(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.MASTER_START_WORK;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.MASTER + "  авторизуется", () -> {
                commonFields.setTokenMaster(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrMaster1Email, sssrMaster1Password)));
            });
            step(UserRole.MASTER + " сохраняет чеклист", () -> {
                SaveCheckListResponseDto actualResponse = saveCheckListApi.saveCheckList(repairCase.saveCheckListRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(SaveCheckListResponseDto.class);
                SaveCheckListResponseDto expectedResponse = SaveCheckListResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
        });
    }

    private void applyMaterialInvoiceIssuedPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyMasterStartWorkPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.MATERIAL_INVOICE_ISSUED;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.MASTER + " создает таблицу материалов - в состоянии " + actualStateRepair, () -> {
                OrdersMaterialValuesResponseDto actualResponse = ordersMaterialValuesApi.materialValuesTable(repairCase.materialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersMaterialValuesResponseDto.class);
                OrdersMaterialValuesResponseDto expectedResponse = OrdersMaterialValuesResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            step(UserRole.MASTER + " сохраняет таблицу материалов - в состоянии " + actualStateRepair, () -> {
                OrdersSaveMaterialValuesResponseDto actualResponse = ordersSaveMaterialValuesApi.saveMaterialValues(repairCase.saveMaterialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveMaterialValuesResponseDto.class);
                OrdersSaveMaterialValuesResponseDto expectedResponse = OrdersSaveMaterialValuesResponseDto.oneMaterialResponse();
                assertResponse(expectedResponse, actualResponse);
            });
        });
    }

    private void applyMaterialInvoicePaidPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyMaterialInvoiceIssuedPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.MATERIAL_INVOICE_PAID;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.CLIENT + " оплачивает счет на Материалы - в состоянии " + actualStateRepair, () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderNumber(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
        });
    }

    private void applyActionsInvoiceIssuedPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyMaterialInvoicePaidPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.ACTIONS_INVOICE_ISSUED;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.MASTER + " создает таблицу проведенных работ по заказу - в состоянии " + actualStateRepair, () -> {
                OrdersActionsResponseDto actualResponse = ordersActionsApi.actionsTable(repairCase.actionsRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersActionsResponseDto.class);
                OrdersActionsResponseDto expectedResponse = OrdersActionsResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            step(UserRole.MASTER + " мастер сохраняет таблицу проведенных работ по заказу - в состоянии " + actualStateRepair, () -> {
                OrdersSaveActionsResponseDto actualResponse = ordersSaveActionsApi.saveActions(repairCase.saveActionsRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveActionsResponseDto.class);
                OrdersSaveActionsResponseDto expectedResponse = OrdersSaveActionsResponseDto.oneActionResponse();
                assertResponse(expectedResponse, actualResponse);
            });
        });
    }

    private void applyActionsInvoicePaidPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyActionsInvoiceIssuedPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.ACTIONS_INVOICE_PAID;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.CLIENT + " оплачивает  счет на Работы", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderNumber(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment2Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment2Id(actualResponse.getData().getPaymentId());
            });
        });
    }

    private void applyMasterSignActPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyActionsInvoicePaidPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.MASTER_SIGN_ACT;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.MASTER + " подписывает Акт", () -> {
                OrdersCreateActResponseDto actualResponse = ordersCreateActApi.signAct(repairCase.createActRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersCreateActResponseDto.class);
                OrdersCreateActResponseDto expectedResponse = OrdersCreateActResponseDto.defaultResponse();
                assertResponse(expectedResponse, actualResponse);
            });
        });
    }

    private void applyClientSignActPrecondition(StateRepair stateRepair, User client, CommonFieldsDto commonFields) {
        applyMasterSignActPrecondition(stateRepair, client, commonFields);
        StateRepair actualStateRepair = StateRepair.CLIENT_SIGN_ACT;
        step("API: предусловие - " + UserRole.CLIENT + " заказ на ремонт в состоянии " + actualStateRepair, () -> {
            step(UserRole.CLIENT + " запрос на подпись Акта", () -> {
                OrdersSendSignResponseDto actualResponse = ordersSendSignApi.sendSign(repairCase.sendSignRequest(commonFields), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersSendSignResponseDto.class);
                OrdersSendSignResponseDto expectedResponse = OrdersSendSignResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            step(UserRole.CLIENT + " подписывает Акт", () -> {
                OrdersSignResponseDto actualResponse = ordersSignApi.sign(repairCase.signRequest(commonFields), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersSignResponseDto.class);
                OrdersSignResponseDto expectedResponse = OrdersSignResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
        });
    }

    private LastOrderInfoResponseDto getLastOrderInfoDto(CommonFieldsDto commonFields, StateRepair state) {
        System.out.println(state + ": lastOrderInfo");
        return step("API: " + UserRole.CLIENT + " карточка последнего заказа - в состоянии " + state, () -> {
            return lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
        });
    }

    private OrdersIdResponseDto getOrdersIdDto(CommonFieldsDto commonFields, StateRepair state) {
        System.out.println(state + ": ordersId");
        return step("API: " + UserRole.CLIENT + " карточка заказа - в состоянии " + state, () -> {
            return ordersIdApi.orderId(commonFields.getOrderNumber(), commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(OrdersIdResponseDto.class);
        });
    }

    private NotificationsResponseDto getNotificationsDto(CommonFieldsDto commonFields, StateRepair state) {
        System.out.println(state + ": notifications");
        return step("API: " + UserRole.CLIENT + " уведомления - в состоянии " + state, () -> {
            return notificationsApi.getNotifications(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(NotificationsResponseDto.class);
        });
    }

    private CompaniesMastersResponseDto getCompaniesMastersDto(String token) {
        System.out.println("companiesMasters");
        return step("API: " + UserRole.DISPATCHER + " список доступных мастеров ", () -> {
            return companiesMastersApi.getAcceptedMasters(token)
                    .statusCode(200)
                    .extract().as(CompaniesMastersResponseDto.class);
        });
    }

    private void actualDto(CommonFieldsDto commonFields, StateRepair stateRepair) {
        stateInfo.setLastOrderInfoDto(getLastOrderInfoDto(commonFields, stateRepair));
        stateInfo.setOrdersIdResponseDto(getOrdersIdDto(commonFields, stateRepair));
        stateInfo.setNotificationsDto(getNotificationsDto(commonFields, stateRepair));
        stateInfo.setSuperCompaniesMastersResponseDto(getCompaniesMastersDto(commonFields.getTokenSuperDispatcher()));
        stateInfo.setDesignatedCompaniesMastersResponseDto(getCompaniesMastersDto(commonFields.getTokenDispatcher()));
    }

    @Getter
    @AllArgsConstructor
    public class Result {
        private StateInfo stateInfoResult;
        private CommonFieldsDto commonFieldsResult;
    }

}
