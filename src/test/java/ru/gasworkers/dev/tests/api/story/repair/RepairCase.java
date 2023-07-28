package ru.gasworkers.dev.tests.api.story.repair;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsRequestDto;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsRequestDto;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateRequestDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListRequestDto;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActRequestDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesRequestDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesRequestDto;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSendSignRequestDto;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSignRequestDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;

import java.io.File;
import java.io.IOException;

public class RepairCase {
    ObjectMapper objectMapper = new ObjectMapper();

    public UserResponseDto publishedClient(CommonFieldsRepairDto commonFields) throws IOException {
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedUser.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getGuides().get(0).setId(commonFields.getClientGuides0Id());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().setUserNotificationsCount(commonFields.getClientNotificationsCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto publishedLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto publishedOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto publishedOrderIdAsDispatcherBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersIdAsDispatcher.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto hasOfferLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto hasOfferOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdHasOfferClient());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto hasOfferOrderIdBGRepairAsDispatcher(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrderIdAsDispatcher.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto beforePaymentLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto beforePaymentOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentOrdersInfoAsClient.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().getReceipt().setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipt().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        return expectedTemplateResponse;
    }


    public OrdersInfoResponseDto dateSelectingOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto dateSelectingLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto dateSelectingOrderIdBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersIdAsDispatcher.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersApproveDateRequestDto approveDateRequest(CommonFieldsRepairDto commonFields) {
        return OrdersApproveDateRequestDto.builder()
                .orderId(commonFields.getOrderId())
                .selectedDate(commonFields.getApproveDate())
                .build();
    }


    public OrdersIdResponseDto waitMasterOrderIdAsDispatcherBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsDispatcher.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto waitMasterLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public OrdersIdResponseDto waitMasterOrderIdAsClientBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsClient.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto waitMasterOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public SaveCheckListRequestDto saveCheckListRequest(CommonFieldsRepairDto commonFields) {
        return SaveCheckListRequestDto.successfulRequest(commonFields.getOrderId());


    }

    public OrdersIdResponseDto masterStartWorkingOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/masterStartWorking/masterStartWorkingOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto masterStartWorkingLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/masterStartWorking/masterStartWorkingLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public OrdersMaterialValuesRequestDto materialValuesRequest(CommonFieldsRepairDto commonFields) {
        return OrdersMaterialValuesRequestDto.builder()
                .orderId(commonFields.getOrderId())
                .build();
    }

    public OrdersSaveMaterialValuesRequestDto saveMaterialValuesRequest(CommonFieldsRepairDto commonFields) {
        return OrdersSaveMaterialValuesRequestDto.oneMaterialRequest(commonFields.getOrderId());
    }

    public OrdersIdResponseDto materialInvoiceIssuedOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoiceIssued/materialInvoiceIssuedOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto materialInvoiceIssuedLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoiceIssued/materialInvoiceIssuedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersActionsRequestDto actionsRequest(CommonFieldsRepairDto commonFields) {
        return OrdersActionsRequestDto.builder()
                .orderId(commonFields.getOrderId())
                .build();
    }

    public LastOrderInfoResponseDto materialInvoicePaidLastOrderInfoBUGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoicePaid/materialInvoicePaidLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto materialInvoicePaidOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoicePaid/materialInvoicePaidOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersSaveActionsRequestDto saveActionsRequest(CommonFieldsRepairDto commonFields) {
        return OrdersSaveActionsRequestDto.oneActionRequest(commonFields.getOrderId());
    }

    public OrdersIdResponseDto actionsInvoiceIssuedOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actionsInvoiceIssuedLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoiceIssuedOrderIdAsClientBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedOrderIdAsClient.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actionsInvoicePaidLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoicePaidOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoicePaidOrderIdAsClientBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidOrderIdClient.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actSignedMasterOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actSignedMasterLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actSignedMasterOrderIdAsClientBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterOrderIdAsClient.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersCreateActRequestDto createActRequest(CommonFieldsRepairDto commonFields) {
        return OrdersCreateActRequestDto.defaultRequest(commonFields.getOrderId());
    }

    public OrdersIdResponseDto actSignedClientOrderIdAsMasterBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedClient/actSignedClientOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actSignedClientLastOrderInfoBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        return LastOrderInfoResponseDto.noLastOrderResponse();
    }

    public OrdersIdResponseDto actSignedClientOrderIdAsClientBGRepair(CommonFieldsRepairDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedClient/actSignedClientOrderIdAsClient.json"), OrdersIdResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderId());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getClient().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getClient().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().getClient().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getClient().setRefererCode(commonFields.getClientRefererCode());
        expectedTemplateResponse.getData().getClient().getRoles().get(0).getPivot().setModelId(commonFields.getClientRoles0PivotModelId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffers().get(0).setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdPublishedByIdDispatcher());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
        return expectedTemplateResponse;
    }

    public OrdersSendSignRequestDto sendSignRequest(CommonFieldsRepairDto commonFields) {
        return OrdersSendSignRequestDto
                .builder()
                .orderId(commonFields.getOrderId())
                .build();
    }

    public OrdersSignRequestDto signRequest(CommonFieldsRepairDto commonFields) {
        return OrdersSignRequestDto.clientRequest(commonFields.getOrderId());
    }

    private void populateLastOrderInfoFields(LastOrderInfoResponseDto inputDto, CommonFieldsRepairDto commonFields) {
        inputDto.getData().setId(commonFields.getOrderId());
        inputDto.getData().setNumber(commonFields.getOrderNumber());
        inputDto.getData().getClientObject().setId(commonFields.getClientObjectId());
        inputDto.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        inputDto.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        inputDto.getData().getClientObject().setActiveOffersCount(commonFields.getActiveOffersCount());
        inputDto.getData().setSelectedDate(commonFields.getSelectedDate());
    }
}


