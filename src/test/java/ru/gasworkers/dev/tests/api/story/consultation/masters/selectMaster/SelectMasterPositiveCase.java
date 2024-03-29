package ru.gasworkers.dev.tests.api.story.consultation.masters.selectMaster;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum SelectMasterPositiveCase {
    PICK_MASTER_POSITIVE_CASE("Позитивный кейс оплаты ( assertion error Expected :null Actual   :true )");
    private final String description;

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    public CreateOrderRequestDto getCreateOrdersDto() {
        return CreateOrderRequestDto.builder()
                .type("consultation")
                .build();
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
        if (this == SelectMasterPositiveCase.PICK_MASTER_POSITIVE_CASE) {
            return PickMasterRequestDto.builder()
                    .orderId(orderId)
                    .online(true)
                    .build();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }
}
