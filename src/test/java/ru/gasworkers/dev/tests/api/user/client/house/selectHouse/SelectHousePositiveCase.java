package ru.gasworkers.dev.tests.api.user.client.house.selectHouse;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;

import java.util.List;

@AllArgsConstructor
enum SelectHousePositiveCase {
    SELECT_OBJECT_POSITIVE_CASE("Получение информации по объектам клиента");
    private final String description;

    public CreateOrdersRequestDto getCreateOrdersDto() {
        return CreateOrdersRequestDto.builder()
                .type("consultation")
                .build();
    }

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
        return OnlineMastersRequestDto.builder()
                .orderId(orderId)
                .search("rating")
                .build();
    }

    public IsStartedRequestDto getIsStartedDto(Integer orderId) {
        return IsStartedRequestDto.builder()
                .orderId(orderId)
                .mainFilter("rating")
                .build();
    }

    public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        return PickMasterRequestDto.builder()
                .orderId(orderId)
                .online(true)
                .build();
    }


    public SelectHouseRequestDto getSelectObjectDto(Integer objectId, Integer orderId, List<Integer> equipmentList) {
        return SelectHouseRequestDto.builder()
                .clientObjectId(objectId)
                .orderId(orderId)
                .equipment(equipmentList)
                .build();
    }

    @Override
    public String toString() {
        return description;
    }
}
