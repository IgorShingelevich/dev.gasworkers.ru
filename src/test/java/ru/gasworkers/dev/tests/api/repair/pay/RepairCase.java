package ru.gasworkers.dev.tests.api.repair.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;

import java.io.File;
import java.io.IOException;

public class RepairCase {
    ObjectMapper objectMapper = new ObjectMapper();

    public LastOrderInfoResponseDto publishedLastOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedLastOrder.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(equipmentsId);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto hasOfferLastOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOfferCount) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(activeOfferCount);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto beforePaymentLastOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOffersCount) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(activeOffersCount);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto publishedOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto hasOfferOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOfferCount, Integer offerId) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getOffer().setId(offerId);
        expectedTemplateResponse.getData().getOffer().setOrderId(orderId);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto beforePaymentOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOffersCount, Integer offerId, Integer receiptId) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getOffer().setId(offerId);
        expectedTemplateResponse.getData().getOffer().setOrderId(orderId);
        expectedTemplateResponse.getData().getReceipt().setId(receiptId);
        expectedTemplateResponse.getData().getReceipt().setOrderId(orderId);
        expectedTemplateResponse.getData().getReceipts().get(0).setId(receiptId);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto dateSelectingOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOffersCount, Integer offerId, Integer receiptId) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getOffer().setId(offerId);
        expectedTemplateResponse.getData().getOffer().setOrderId(orderId);
        expectedTemplateResponse.getData().getReceipts().get(0).setId(receiptId);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto dateSelectingLastOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOffersCount) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(activeOffersCount);
        return expectedTemplateResponse;
    }

    public UserResponseDto publishedClient(CommonFieldsRepairDto publishedCommonFieldsDto) throws IOException {
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedUser.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(publishedCommonFieldsDto.getClientId());
        expectedTemplateResponse.getData().setEmail(publishedCommonFieldsDto.getClientEmail());
        expectedTemplateResponse.getData().getGuides().get(0).setId(publishedCommonFieldsDto.getClientGuides0Id());
        expectedTemplateResponse.getData().setPhone(publishedCommonFieldsDto.getClientPhone());
        /*expectedTemplateResponse = UserResponseDto.builder()
                .data(UserResponseDto.Data.builder()
                        .id(publishedCommonFieldsDto.getClientId())
                        .email(publishedCommonFieldsDto.getClientEmail())
                        .guides(new ArrayList<>(Collections.singletonList(UserResponseDto.Data.Guides.builder()
                                .id(publishedCommonFieldsDto.getClientGuides0Id())
                                .build())))
                        .phone(publishedCommonFieldsDto.getClientPhone())
                        .build())
                .build();*/

        return expectedTemplateResponse;
    }
}
