package ru.gasworkers.dev.tests.api.story.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonFieldsDto {
    private String tokenAdmin, tokenClient, tokenDispatcher, tokenSuperDispatcher, payment0Url, payment1Url, payment2Url, tokenMaster,
            orderNumberFull, clientEmail, masterEmail, clientRefererCode, approveDate, selectedDate;
    private Long clientPhone;
    private Integer clientNotificationsCount, superServiceId, serviceId, dispatcherId, masterId, superMasterId, orderNumber,
            offerIdBeforePayment,
            offerIdHasOfferClient, possibleOfferId, offerIdPublishedByIdDispatcher, clientObjectId,
            equipments0Id, activeOffersCount = 0, receipts0Id, receipts1Id, receipts2Id, clientId,
            clientGuides0Id, payment0Id, payment1Id, payment2Id, total, objectId, clientRoles0PivotModelId,
            masters0CompletedOrdersCount, passportId, executorCompanyId;
    private List<OrdersIdResponseDto.Data.Receipts> receipts = List.of();
    private OrdersIdResponseDto.Data.Master master = null;
    //sssr dispatcher
    public String dispatcherEmail = "test_gw_dispatcher_sssr1@rambler.ru",
            dispatcherPassword = "1234";

}
