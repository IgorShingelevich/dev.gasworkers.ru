package ru.gasworkers.dev.tests.api.story.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonFieldsRepairDto {
    private String tokenClient, tokenDispatcher, payment0Url, payment1Url, payment2Url, tokenMaster, orderNumber, clientEmail, clientRefererCode, approveDate, selectedDate;
    private Long clientPhone;
    private Integer clientNotificationsCount, serviceId, dispatcherId, masterId, orderId, offerIdBeforePayment,
            offerIdHasOfferClient, possibleOfferId, offerIdPublishedByIdDispatcher, clientObjectId, equipments0Id, activeOffersCount,
            receipts0Id, receipts1Id, receipts2Id, clientId, clientGuides0Id, payment0Id, payment1Id, payment2Id, objectId, clientRoles0PivotModelId, masters0CompletedOrdersCount;
}
