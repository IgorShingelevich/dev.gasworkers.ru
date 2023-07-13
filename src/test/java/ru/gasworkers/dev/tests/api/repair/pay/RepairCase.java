package ru.gasworkers.dev.tests.api.repair.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;

import java.io.File;
import java.io.IOException;

class RepairCase {
    public OrdersInfoResponseDto publishedOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrdersInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedOrdersInfo.json"), OrdersInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        return expectedTemplateResponse;
    }

    public LastOrderInfoResponseDto publishedLastOrderInfoBGRepair(Integer orderId, String orderNumber, Integer clientObjectId, Integer equipmentsId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LastOrderInfoResponseDto expectedTemplateResponse = objectMapper.readValue(new File("src/test/resources/api/repair/publishedBgState/publishedLastOrder.json"), LastOrderInfoResponseDto.class);
        expectedTemplateResponse.getData().setId(orderId);
        expectedTemplateResponse.getData().setNumber(orderNumber);
        expectedTemplateResponse.getData().getClientObject().setId(clientObjectId);
        expectedTemplateResponse.getData().getEquipments().get(0).setId(equipmentsId);
        expectedTemplateResponse.getData().getClientObject().getEquipments().get(0).setId(equipmentsId);

        return expectedTemplateResponse;
    }
}
