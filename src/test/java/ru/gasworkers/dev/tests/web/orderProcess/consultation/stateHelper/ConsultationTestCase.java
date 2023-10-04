package ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper;

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
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;

import java.io.File;
import java.io.IOException;

public class ConsultationTestCase {
    ObjectMapper objectMapper = new ObjectMapper();

    public UserResponseDto publishedClient(CommonFieldsDto commonFields) throws IOException {
        UserResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedUser.json"), UserResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getClientId());
        expectedTemplateResponse.getData().setEmail(commonFields.getClientEmail());
        expectedTemplateResponse.getData().getGuides().get(0).setId(commonFields.getClientGuides0Id());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().setUserNotificationsCount(commonFields.getClientNotificationsCount());
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto publishedLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto publishedOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto publishedOrderIdAsDispatcherBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersIdAsDispatcher.json"), OrdersIdResponseDto.class);
        populatePublishedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto hasOfferLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto hasOfferOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdHasOfferClient());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderNumber());
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto hasOfferOrderIdBGRepairAsDispatcher(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/hasOfferBgState/hasOfferOrderIdAsDispatcher.json"), OrdersIdResponseDto.class);
        populateHasOfferOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto beforePaymentLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersInfoResponseDto beforePaymentOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/beforePayment/beforePaymentOrdersInfoAsClient.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getReceipt().setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipt().setOrderId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        return expectedTemplateResponse;
    }


    public OrdersInfoResponseDto dateSelectingOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getOffer().setId(commonFields.getOfferIdBeforePayment());
        expectedTemplateResponse.getData().getOffer().setOrderId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPossibleOfferId(commonFields.getPossibleOfferId());
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto dateSelectingLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto dateSelectingOrderIdBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/dateSelectingBgState/dateSelectingOrdersIdAsDispatcher.json"), OrdersIdResponseDto.class);
        populateDateSelectingOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersApproveDateRequestDto approveDateRequest(CommonFieldsDto commonFields) {
        return OrdersApproveDateRequestDto.builder()
                .orderId(commonFields.getOrderNumber())
                .selectedDate(commonFields.getApproveDate())
                .build();
    }


    public OrdersIdResponseDto waitMasterOrderIdAsDispatcherBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsDispatcher.json"), OrdersIdResponseDto.class);
        populateWaitMasterOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public LastOrderInfoResponseDto waitMasterLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public OrdersIdResponseDto waitMasterOrderIdAsClientBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsClient.json"), OrdersIdResponseDto.class);
        populateWaitMasterOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto waitMasterOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/waitMaster/waitMasterOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateWaitMasterOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public SaveCheckListRequestDto saveCheckListRequest(CommonFieldsDto commonFields) {
        return SaveCheckListRequestDto.successfulRequest(commonFields.getOrderNumber());


    }

    public OrdersIdResponseDto masterStartWorkingOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/masterStartWorking/masterStartWorkingOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateWaitMasterOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto masterStartWorkingLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/masterStartWorking/masterStartWorkingLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }


    public OrdersMaterialValuesRequestDto materialValuesRequest(CommonFieldsDto commonFields) {
        return OrdersMaterialValuesRequestDto.builder()
                .orderId(commonFields.getOrderNumber())
                .build();
    }

    public OrdersSaveMaterialValuesRequestDto saveMaterialValuesRequest(CommonFieldsDto commonFields) {
        return OrdersSaveMaterialValuesRequestDto.oneMaterialRequest(commonFields.getOrderNumber());
    }

    public OrdersIdResponseDto materialInvoiceIssuedOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoiceIssued/materialInvoiceIssuedOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateMaterialInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto materialInvoiceIssuedLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoiceIssued/materialInvoiceIssuedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersActionsRequestDto actionsRequest(CommonFieldsDto commonFields) {
        return OrdersActionsRequestDto.builder()
                .orderId(commonFields.getOrderNumber())
                .build();
    }

    public LastOrderInfoResponseDto materialInvoicePaidLastOrderInfoBUGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoicePaid/materialInvoicePaidLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto materialInvoicePaidOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/materialInvoicePaid/materialInvoicePaidOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateMaterialInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersSaveActionsRequestDto saveActionsRequest(CommonFieldsDto commonFields) {
        return OrdersSaveActionsRequestDto.oneActionRequest(commonFields.getOrderNumber());
    }

    public OrdersIdResponseDto actionsInvoiceIssuedOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actionsInvoiceIssuedLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoiceIssuedOrderIdAsClientBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoiceIssued/actionsInvoiceIssuedOrderIdAsClient.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actionsInvoicePaidLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidLastOrderInfo.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoicePaidOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actionsInvoicePaidOrderIdAsClientBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actionsInvoicePaid/actionsInvoicePaidOrderIdClient.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actSignedMasterOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actSignedMasterLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterLastOrder.json"), LastOrderInfoResponseDto.class);
        populateLastOrderInfoFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersIdResponseDto actSignedMasterOrderIdAsClientBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedMaster/actSignedMasterOrderIdAsClient.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersCreateActRequestDto createActRequest(CommonFieldsDto commonFields) {
        return OrdersCreateActRequestDto.defaultRequest(commonFields.getOrderNumber());
    }

    public OrdersIdResponseDto actSignedClientOrderIdAsMasterBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedClient/actSignedClientOrderIdAsMaster.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto actSignedClientLastOrderInfoBGRepair(CommonFieldsDto commonFields) throws IOException {
        return LastOrderInfoResponseDto.noLastOrderResponse();
    }

    public OrdersIdResponseDto actSignedClientOrderIdAsClientBGRepair(CommonFieldsDto commonFields) throws IOException {
        OrdersIdResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/actSignedClient/actSignedClientOrderIdAsClient.json"), OrdersIdResponseDto.class);
        populateActionInvoiceIssuedOrderIdFields(expectedTemplateResponse, commonFields);
        return expectedTemplateResponse;
    }

    public OrdersSendSignRequestDto sendSignRequest(CommonFieldsDto commonFields) {
        return OrdersSendSignRequestDto
                .builder()
                .orderId(commonFields.getOrderNumber())
                .build();
    }

    public OrdersSignRequestDto signRequest(CommonFieldsDto commonFields) {
        return OrdersSignRequestDto.clientRequest(commonFields.getOrderNumber());
    }

    private void populateLastOrderInfoFields(LastOrderInfoResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().getClientObject().setId(commonFields.getClientObjectId());
        expectedTemplateResponse.getData().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(commonFields.getEquipments0Id());
        expectedTemplateResponse.getData().getClientObject().setActiveOffersCount(commonFields.getActiveOffersCount());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
    }

    private void populatePublishedOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
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
    }


    private void populateHasOfferOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
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
    }

    private void populateDateSelectingOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
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
    }

    private void populateWaitMasterOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
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
    }

    private void populateMaterialInvoiceIssuedOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
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
    }

    private void populateActionInvoiceIssuedOrderIdFields(OrdersIdResponseDto expectedTemplateResponse, CommonFieldsDto commonFields) {
        expectedTemplateResponse.getData().setId(commonFields.getOrderNumber());
        expectedTemplateResponse.getData().setNumber(commonFields.getOrderNumberFull());
        expectedTemplateResponse.getData().setPhone(commonFields.getClientPhone());
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
        expectedTemplateResponse.getData().getReceipts().get(0).setId(commonFields.getReceipts0Id());
        expectedTemplateResponse.getData().getReceipts().get(1).setId(commonFields.getReceipts1Id());
        expectedTemplateResponse.getData().getReceipts().get(2).setId(commonFields.getReceipts2Id());
        expectedTemplateResponse.getData().setSelectedDate(commonFields.getSelectedDate());
        expectedTemplateResponse.getData().getMaster().setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getMasters().get(0).setCompletedOrdersCount(commonFields.getMasters0CompletedOrdersCount());
        expectedTemplateResponse.getData().getServiceCenter().getMasters().get(0).setCountCompletedOrders(commonFields.getMasters0CompletedOrdersCount());
    }

}



