package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;
import ru.gasworkers.dev.tests.web.BaseWebTest;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.PreconditionConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateInfo;

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
public class PublishedConsultationTest extends BaseWebTest {

    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final LoginApi loginApi = new LoginApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    @Test
    @DisplayName("Консультация - в состоянии clientWaitMaster")
    void clientWaitMaster(@WithClient(houses = {@WithHouse}) User client) {
        StateConsultation state = StateConsultation.CLIENT_WAIT_MASTER;
        Role role = Role.CLIENT;

        PreconditionConsultation preconditionConsultation = new PreconditionConsultation();
        PreconditionConsultation.Result result = preconditionConsultation.applyPrecondition(client, state);

// Get the StateInfo and CommonFieldsDto from the result
        StateInfo stateInfo = result.getStateInfoResult();
        CommonFieldsDto commonFields = result.getCommonFieldsResult();

        /*String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(AddEquipmentRequestDto.defaultBoilerEquipment(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create orders", () -> {
            return createOrdersApi.createOrder(CreateOrderRequestDto.builder()
                            .type("consultation")
                            .houseId(String.valueOf(houseId))
                            .build(), token)
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class).getData().getOrderId();
        });
        GetClientHousesResponseDto actualResponse = step("Get client objects", () -> {
            return getClientHousesApi.getHouse(token)
                    .statusCode(200)
                    .extract().as(GetClientHousesResponseDto.class);
        });
        GetClientHousesResponseDto.DataDto firstData = actualResponse.getData()[0];
        Integer firstEquipmentId = firstData.getEquipments()[0].getId();

        List<Integer> equipmentList = new ArrayList<>();
        for (GetClientHousesResponseDto.Equipment equipment : firstData.getEquipments()) {
            equipmentList.add(equipment.getId());
        }
        Integer actualObjectId = firstData.getId();
        System.out.println("equipmentList = " + equipmentList);
        System.out.println("firstEquipmentId = " + firstEquipmentId);
        System.out.println("actualObjectId = " + actualObjectId);

        SelectHouseResponseDto expectedResponse = step("Select object", () -> {
            return selectHouseApi.selectObject(SelectHouseRequestDto.builder()
                            .clientObjectId(houseId)
                            .orderId(orderId)
                            .equipment(equipmentList)
                            .build(), token)
                    .statusCode(200)
                    .extract().as(SelectHouseResponseDto.class);
        });
        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(OnlineMastersRequestDto.builder()
                            .orderId(orderId)
                            .search("rating")
                            .build(), token)
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
            Integer firstMasterId = currentMasterIds.get(0);
            System.out.println("First master id: " + firstMasterId);
            return currentMasterIds;
        });

        Integer timetableId = step("Pick master", () -> {
            return selectConsultationMasterApi.selectMaster(PickMasterRequestDto.builder()
                            .orderId(orderId)
                            .online(true)
                            .build(), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        Integer receiptId = step("Apply master", () -> {
            return applyMasterApi.applyMaster(ApplyMasterRequestDto.builder()
                            .orderId(orderId)
                            .timetableId(timetableId)
                            .description("test description")
                            .now(true)
                            .build(), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class).getData().getReceiptId();
        });
        SelectPaymentResponseDto selectPaymentResponse = step("Select payment", () -> {
            return selectPaymentApi.selectPayment(SelectPaymentRequestDto.builder()
                            .orderId(orderId)
                            .receiptId(receiptId)
                            .type("fps")
                            .build(), token)
                    .statusCode(200)
                    .extract().as(SelectPaymentResponseDto.class);
        });
        String payUrl = selectPaymentResponse.getData().getPayUrl();
        Integer paymentId = selectPaymentResponse.getData().getPaymentId();
        String masterEmail = step("Get master credentials", () -> {
            String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                    .statusCode(200)
                    .extract().as(LoginResponseDto.class).getData().getToken();
            String actualUserResponse = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, masterIdList.get(0)) //487
                    .statusCode(200)
                    .extract().asString();
            JsonObject responseObject = JsonParser.parseString(actualUserResponse).getAsJsonObject();
            JsonObject dataObject = responseObject.getAsJsonObject("data");
            return dataObject.get("email").getAsString();
        });
        System.out.println("masterEmail = " + masterEmail);
        String phone = ComplexRegistrationRequestDto.builder()
                .phone(client.getPhone())
                .build().getPhone();*/

//        ----------------------------  UI  --------------------------------
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(String.valueOf(commonFields.getClientPhone()), "1234");
                clientPages.getHomePage().checkUrl();

            });

            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", commonFields.getClientPhone() + ": " + "1234" + "/");
                Allure.addAttachment("Master creds", commonFields.getMasterEmail() + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });


        step(role + " кабинет в состоянии - в состоянии " + state, () -> {

            Consumer<SoftAssert> case1 = softAssert -> {
                step(role + " карточка последнего заказа - в состоянии " + state, () -> {
                    clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
                    clientPages.getHomePage().lastOrderComponent.checkState(state, stateInfo.getLastOrderInfoDto());
                });
            };

            Consumer<SoftAssert> case2 = softAssert -> {
                step(role + " компонент баннер ВК в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().conferenceNotification.checkState(state);
                });
            };

            Consumer<SoftAssert> case3 = softAssert -> {
                step(role + " кнопка на дом стр Заполнить профиль - в состоянии " + state, () -> {
                    clientPages.getHomePage().checkFillProfileButton();
                });
            };

            Consumer<SoftAssert> case4 = softAssert -> {
                step(role + " карточка заказа - в состоянии " + state, () -> {
                    clientPages.getOrderCardPage().open(String.valueOf(commonFields.getOrderId()));
                    clientPages.getOrderCardPage().checkFinishLoading();
                    clientPages.getOrderCardPage().checkState(state, stateInfo.getOrdersIdResponseDto());
                });
            };

            Consumer<SoftAssert> case5 = softAssert -> {
                step(role + " уведомления - в состоянии " + state, () -> {
                    clientPages.getAllNotificationsPage().open();
                    clientPages.getAllNotificationsPage().checkFinishLoading();
                    clientPages.getAllNotificationsPage().checkConsultationState(state, stateInfo.getNotificationsDto());
                });
            };

            Consumer<SoftAssert> case6 = softAssert -> {
                step(role + " красное уведомление в лк - в состоянии " + state, () -> {
                    clientPages.getHomePage().open();
                    clientPages.getHomePage().checkFinishLoading();
                    clientPages.getHomePage().redNotice.noNotice();
                });
            };
            Consumer<SoftAssert> case7 = softAssert -> {
                step(role + " красное уведомление на стр лендинга - в состоянии " + state, () -> {
                    clientPages.getLandingPage().open();
                    clientPages.getLandingPage().checkFinishLoading();
                    clientPages.getLandingPage().noticeComponent.noNotifications();
                });
            };
            assertAll(Arrays.asList(case1,
                    case2, case3,
                    case4, case5, case6, case7));
        });

    }
}



