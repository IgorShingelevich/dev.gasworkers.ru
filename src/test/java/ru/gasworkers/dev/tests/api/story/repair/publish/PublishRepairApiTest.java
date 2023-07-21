package ru.gasworkers.dev.tests.api.story.repair.publish;

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
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Публикация заявки на ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REPAIR)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class PublishRepairApiTest extends BaseApiTest {

    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final String dispatcherEmail = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String dispatcherPassword = "1234";


    @Test
    @DisplayName("Client publish repair")
    void clientPublishRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        String token = loginApi.getTokenThrough(client);
        LastOrderInfoResponseDto lastOrderResponse = lastOrderInfoApi.getLastOrderInfo(token)
                .statusCode(200)
                .extract().as(LastOrderInfoResponseDto.class);
        String actualOrderType = lastOrderResponse.getData().getType();
        String actualStatus = lastOrderResponse.getData().getStatus();
        Integer activeOfferCount = lastOrderResponse.getData().getClientObject().getActiveOffersCount();
        assertThat(actualOrderType).isEqualTo("repair");
        assertThat(actualStatus).isEqualTo("published");
        assertThat(activeOfferCount).isEqualTo(0);
        System.out.println("published state: " + actualStatus + " order type: " + actualOrderType + " active offers: " + activeOfferCount);
    }

    @Test
    @DisplayName("Client has offer repair")
    void clientHasOfferRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        String token = loginApi.getTokenThrough(client);
        LastOrderInfoResponseDto lastOrderResponse = lastOrderInfoApi.getLastOrderInfo(token)
                .statusCode(200)
                .extract().as(LastOrderInfoResponseDto.class);
        Integer orderId = lastOrderResponse.getData().getId();
        String actualOrderType = lastOrderResponse.getData().getType();
        String actualStatus = lastOrderResponse.getData().getStatus();
        Integer activeOfferCount = lastOrderResponse.getData().getClientObject().getActiveOffersCount();
        assertThat(actualOrderType).isEqualTo("repair");
        assertThat(actualStatus).isEqualTo("published");
        assertThat(activeOfferCount).isEqualTo(1);
        System.out.println("order id: " + orderId + " ,published state: " + actualStatus + " ,order type: " + actualOrderType + " ,active offers: " + activeOfferCount);
    }
}
