package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateInfo {
    private CommonFieldsRepairDto commonFields;

    private NotificationsResponseDto publishedNotifications;
    private SuggestServicesResponseDto publishedSuggestedServiceResponse;
    private LastOrderInfoResponseDto publishedLastOrderInfo;
    private OrdersIdResponseDto publishedOrderIdResponse;

    private NotificationsResponseDto hasOfferNotifications;
    private SuggestServicesResponseDto hasOfferSuggestedServiceResponse;
    private LastOrderInfoResponseDto hasOfferLastOrderInfo;
    private OrdersIdResponseDto hasOfferOrderIdClient;

    private NotificationsResponseDto scheduleDateNotifications;
    private LastOrderInfoResponseDto scheduleDateLastOrderInfo;
    private OrdersIdResponseDto scheduleDateOrderIdResponse;

    private NotificationsResponseDto waitMasterNotifications;
    private LastOrderInfoResponseDto waitMasterLastOrderInfo;
    private OrdersIdResponseDto waitMasterOrderIdResponse;

    private NotificationsResponseDto masterStartWorkNotifications;
    private LastOrderInfoResponseDto masterStartWorkLastOrderInfo;
    private OrdersIdResponseDto masterStartWorkOrderIdResponse;

    private NotificationsResponseDto materialInvoiceIssuedNotifications;
    private LastOrderInfoResponseDto materialInvoiceIssuedLastOrderInfo;
    private OrdersIdResponseDto materialInvoiceIssuedOrderIdResponse;

    private NotificationsResponseDto materialInvoicePaidNotifications;
    private LastOrderInfoResponseDto materialInvoicePaidLastOrderInfo;
    private OrdersIdResponseDto materialInvoicePaidOrderIdResponse;

    public StateInfo hasOfferDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .hasOfferSuggestedServiceResponse(hasOfferSuggestedServiceResponse)
                .hasOfferLastOrderInfo(hasOfferLastOrderInfo)
                .hasOfferOrderIdClient(hasOfferOrderIdClient)
                .hasOfferNotifications(hasOfferNotifications)
                .build();
    }

    public StateInfo publishedDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .publishedSuggestedServiceResponse(publishedSuggestedServiceResponse)
                .publishedLastOrderInfo(publishedLastOrderInfo)
                .publishedOrderIdResponse(publishedOrderIdResponse)
                .publishedNotifications(publishedNotifications)
                .build();
    }

    public StateInfo scheduleDateDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .scheduleDateLastOrderInfo(scheduleDateLastOrderInfo)
                .scheduleDateOrderIdResponse(scheduleDateOrderIdResponse)
                .scheduleDateNotifications(scheduleDateNotifications)
                .build();
    }

    public StateInfo waitMasterDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .waitMasterLastOrderInfo(waitMasterLastOrderInfo)
                .waitMasterOrderIdResponse(waitMasterOrderIdResponse)
                .waitMasterNotifications(waitMasterNotifications)
                .build();
    }

    public StateInfo masterStartWorkDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .masterStartWorkLastOrderInfo(masterStartWorkLastOrderInfo)
                .masterStartWorkOrderIdResponse(masterStartWorkOrderIdResponse)
                .masterStartWorkNotifications(masterStartWorkNotifications)
                .build();
    }

    public StateInfo materialInvoiceIssuedDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .materialInvoiceIssuedLastOrderInfo(materialInvoiceIssuedLastOrderInfo)
                .materialInvoiceIssuedOrderIdResponse(materialInvoiceIssuedOrderIdResponse)
                .materialInvoiceIssuedNotifications(materialInvoiceIssuedNotifications)
                .build();
    }

    public StateInfo materialInvoicePaidDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .materialInvoicePaidLastOrderInfo(materialInvoicePaidLastOrderInfo)
                .materialInvoicePaidOrderIdResponse(materialInvoicePaidOrderIdResponse)
                .materialInvoicePaidNotifications(materialInvoicePaidNotifications)
                .build();
    }
}


