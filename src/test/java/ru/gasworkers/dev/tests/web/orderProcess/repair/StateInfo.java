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
    private LastOrderInfoResponseDto hasOfferLastOrderInfo;
    private SuggestServicesResponseDto hasOfferSuggestedServiceResponse;
    private OrdersIdResponseDto hasOfferOrderIdClient;
    private LastOrderInfoResponseDto publishedLastOrderInfo;
    private OrdersIdResponseDto publishedOrderIdResponse;
    private SuggestServicesResponseDto publishedSuggestedServiceResponse;
    private CommonFieldsRepairDto commonFields; // Include the common fields

    public StateInfo hasOfferDtoSet() {
        return StateInfo.builder()
                .hasOfferLastOrderInfo(hasOfferLastOrderInfo)
                .hasOfferSuggestedServiceResponse(hasOfferSuggestedServiceResponse)
                .hasOfferOrderIdClient(hasOfferOrderIdClient)
                .commonFields(commonFields) // Include the common fields
                .build();
    }

    public StateInfo publishedDtoSet() {
        return StateInfo.builder()
                .publishedLastOrderInfo(publishedLastOrderInfo)
                .publishedOrderIdResponse(publishedOrderIdResponse)
                .publishedSuggestedServiceResponse(publishedSuggestedServiceResponse)
                .commonFields(commonFields) // Include the common fields
                .build();
    }
}


