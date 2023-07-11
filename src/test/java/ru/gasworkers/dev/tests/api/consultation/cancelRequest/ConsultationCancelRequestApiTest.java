package ru.gasworkers.dev.tests.api.consultation.cancelRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.consultation.cancelRequest.ConsultationCancelRequestApi;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.users.client.clientsOrdersRequests.ClientOrdersRequestsApi;
import ru.gasworkers.dev.api.users.client.clientsOrdersRequests.ClientsOrdersRequestsResponse;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Отмена заявки на консультацию")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CONSULTATION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.MASTER)
@Tag(AllureTag.API)
public class ConsultationCancelRequestApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final ClientOrdersRequestsApi clientOrdersRequestsApi = new ClientOrdersRequestsApi();
    private final ConsultationCancelRequestApi consultationCancelRequestApi = new ConsultationCancelRequestApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(CancelRequestPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(CancelRequestPositiveCase testCase, @WithThroughUser(withOrderType = @WithOrderType(type = "consultation")) User client) {
        String token = loginApi.getTokenThrough(client);
        Integer orderId = clientOrdersRequestsApi.getInfoOrder(token)
                .statusCode(200)
                .extract().as(ClientsOrdersRequestsResponse.class).getData().get(0).getId();

        System.out.println("Order id: " + orderId);

        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
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
            return selectConsultationMasterApi.selectMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        Integer receiptId = step("Apply master", () -> {
            return applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class).getData().getReceiptId();
        });
        SelectPaymentResponseDto selectPaymentResponse = step("Select payment", () -> {
            return selectPaymentApi.selectPayment(testCase.getSelectPaymentDto(orderId, receiptId), token)
                    .statusCode(200)
                    .extract().as(SelectPaymentResponseDto.class);
        });
        step("Cancel request", () -> {

            consultationCancelRequestApi.cancelRequest(testCase.getCancelRequestDto(), orderId, token)
                    .statusCode(200);
        });
    }
}
