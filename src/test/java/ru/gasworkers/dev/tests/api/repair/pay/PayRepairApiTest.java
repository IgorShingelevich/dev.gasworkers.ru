package ru.gasworkers.dev.tests.api.repair.pay;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMasters;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class PayRepairApiTest extends BaseApiTest {

    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMasters companiesMasters = new CompaniesMasters();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();

    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";

    @Test
    @DisplayName("payed repair")
    void payedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        String tokenClient = loginApi.getTokenThrough(client);
        Integer orderId = step("убедиться что есть единственный последний заказ в статусе ожидания начала", () -> {
            LastOrderInfoResponseDto lastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
            String orderType = lastOrderResponse.getData().getType();
            String orderStatus = lastOrderResponse.getData().getStatus();
            Assertions.assertThat(orderStatus).isEqualTo("published");
            Assertions.assertThat(orderType).isEqualTo("repair");
            return lastOrderResponse.getData().getId();
        });

        String tokenDispatcher = step("диспетчер авторизуется", () -> {
            return loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password));
        });
        Integer masterId = step("диспетчер выбирает мастера ", () -> {
            return companiesMasters.getAcceptedMasters(tokenDispatcher)
                    .statusCode(200)
                    .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId();
        });
        /*CompaniesMastersListResponse mastersListResponse = companiesMasters.getAcceptedMasters(tokenDispatcher)
                .statusCode(200)
                .extract().as(CompaniesMastersListResponse.class);
        Integer masterId = mastersListResponse.getData().get(0).getId();*/

        Integer receiptId = step("диспетчер выбирает мастера ", () -> {
            return selectMasterApi.selectMaster(orderId, masterId, tokenClient)
                    .statusCode(200)
                    .extract().as(SelectMasterResponseDto.class).getData().getReceiptId();
        });

        step(" клиент карточка последнего заказа - убедиться что СК предложила мастера", () -> {
            LastOrderInfoResponseDto lastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
            String orderType = lastOrderResponse.getData().getType();
            String orderStatus = lastOrderResponse.getData().getStatus();
            Assertions.assertThat(orderStatus).isEqualTo("master-selected");
            Assertions.assertThat(orderType).isEqualTo("repair");
            return lastOrderResponse.getData().getId();
        });

    }

}
