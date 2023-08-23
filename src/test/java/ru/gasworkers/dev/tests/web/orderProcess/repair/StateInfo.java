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
    private SuggestServicesResponseDto suggestedServiceDto;
    private NotificationsResponseDto notificationsDto;
    private LastOrderInfoResponseDto lastOrderInfoDto;
    private OrdersIdResponseDto ordersIdResponseDto;

    public StateInfo actualDtoSet() {
        return StateInfo.builder()
                .commonFields(commonFields) // Include the common fields
                .lastOrderInfoDto(lastOrderInfoDto)
                .ordersIdResponseDto(ordersIdResponseDto)
                .notificationsDto(notificationsDto)
                .suggestedServiceDto(suggestedServiceDto)
                .build();
    }
}


