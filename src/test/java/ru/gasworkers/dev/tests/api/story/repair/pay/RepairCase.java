package ru.gasworkers.dev.tests.api.story.repair.pay;

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

    public LastOrderInfoResponseDto publishedLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedLastOrder.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipmentsId());
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

    public LastOrderInfoResponseDto hasOfferLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(commonFields.getActiveOffersCount());
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

    public LastOrderInfoResponseDto beforePaymentLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(commonFields.getActiveOffersCount());
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

    public OrdersInfoResponseDto publishedOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
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

    public OrdersInfoResponseDto hasOfferOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdHasOffer());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto beforePaymentOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId, Integer activeOffersCount, Integer offerId, Integer receiptId) throws IOException {
        //todo redo to dto argumetns
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

    public OrdersInfoResponseDto beforePaymentOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().getReceipt().setId(commonFields.getReceiptId());
        expectedTemplateResponse.getData().getReceipt().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceiptId());
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

    public OrdersInfoResponseDto dateSelectingOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceiptId());
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

    public LastOrderInfoResponseDto dateSelectingLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipmentsId());
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(commonFields.getActiveOffersCount());
        return expectedTemplateResponse;
    }

    public UserResponseDto publishedClient(CommonFieldsRepairDto commonFields) throws IOException {
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedUser.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getGuides().get(0).setId(commonFields.getClientGuides0Id());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().setUserNotificationsCount(commonFields.getClientNotificationsCount());
        return expectedTemplateResponse;
    }
}
