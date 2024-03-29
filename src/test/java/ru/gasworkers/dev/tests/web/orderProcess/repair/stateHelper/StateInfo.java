package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.administration.totalPrice.TotalPriceResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersResponseDto;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateInfo {
    private CommonFieldsDto commonFields;
    private SuggestServicesResponseDto suggestedServiceDto;
    private NotificationsResponseDto notificationsDto;
    private LastOrderInfoResponseDto lastOrderInfoDto;
    private OrdersIdResponseDto ordersIdResponseDto;
    private CompaniesMastersResponseDto designatedCompaniesMastersResponseDto;
    private CompaniesMastersResponseDto superCompaniesMastersResponseDto;
    private TotalPriceResponseDto totalPriceResponseDto;


    public StateInfo actualDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .lastOrderInfoDto(lastOrderInfoDto)
                .ordersIdResponseDto(ordersIdResponseDto)
                .notificationsDto(notificationsDto)
                .suggestedServiceDto(suggestedServiceDto)
                .designatedCompaniesMastersResponseDto(designatedCompaniesMastersResponseDto)
                .superCompaniesMastersResponseDto(superCompaniesMastersResponseDto)
                .totalPriceResponseDto(totalPriceResponseDto)
                .build();
    }
}


