package ru.gasworkers.dev.tests.api.story.repair.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonFieldsRepairDto {
    private String tokenClient, tokenDispatcher, paymentUrl, tokenMaster, orderNumber, clientEmail, clientRefererCode, approveDate;
    private Long clientPhone;
    private Integer clientNotificationsCount, serviceId, dispatcherId, masterId, orderId, offerIdBeforePayment,
            offerIdHasOfferClient, possibleOfferId, offerIdPublishedByIdDispatcher, clientObjectId, equipments0Id, activeOffersCount,
            receiptId, clientId, clientGuides0Id, paymentId, objectId, clientRoles0PivotModelId;
}
