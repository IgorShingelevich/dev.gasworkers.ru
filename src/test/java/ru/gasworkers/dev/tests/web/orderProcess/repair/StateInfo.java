package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateInfo {
    private CommonFieldsRepairDto commonFields;
    private LastOrderInfoResponseDto hasOfferLastOrderInfo;
    private SuggestServicesResponseDto hasOfferSuggestedServiceResponse;
    private OrdersIdResponseDto hasOfferOrderIdClient;
    private LastOrderInfoResponseDto publishedLastOrderInfo;
    private OrdersIdResponseDto publishedOrderIdResponse;
    private SuggestServicesResponseDto publishedSuggestedServiceResponse;
    private LastOrderInfoResponseDto scheduleDateLastOrderInfo;
    private OrdersIdResponseDto scheduleDateOrderIdResponse;
    private LastOrderInfoResponseDto waitMasterLastOrderInfo;
    private OrdersIdResponseDto waitMasterOrderIdResponse;

    public StateInfo hasOfferDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .hasOfferSuggestedServiceResponse(hasOfferSuggestedServiceResponse)
                .hasOfferLastOrderInfo(hasOfferLastOrderInfo)
                .hasOfferOrderIdClient(hasOfferOrderIdClient)
                .build();
    }

    public StateInfo publishedDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .publishedSuggestedServiceResponse(publishedSuggestedServiceResponse)
                .publishedLastOrderInfo(publishedLastOrderInfo)
                .publishedOrderIdResponse(publishedOrderIdResponse)
                .build();
    }

    public StateInfo scheduleDateDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .scheduleDateLastOrderInfo(scheduleDateLastOrderInfo)
                .scheduleDateOrderIdResponse(scheduleDateOrderIdResponse)
                .build();
    }

    public StateInfo waitMasterDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .waitMasterLastOrderInfo(waitMasterLastOrderInfo)
                .waitMasterOrderIdResponse(waitMasterOrderIdResponse)
                .build();
    }
}


