package ru.gasworkers.dev.tests.api.consultation.masters.pickMaster;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum PickMasterPositiveCase {
    ONLINE_MASTER_WITH_BOILER("Online master with boiler ( assertion error Expected :null Actual   :true )");
    private final String description;

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    public CreateOrdersRequestDto getCreateOrdersDto() {
        return CreateOrdersRequestDto.builder()
                .type("consultation")
                .build();
    }

    public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
        if (this == PickMasterPositiveCase.ONLINE_MASTER_WITH_BOILER) {
            return OnlineMastersRequestDto.builder()
                    .orderId(orderId)
                    .search("rating")
                    .build();
        }
        throw new EnumNotSupportedException(this);
    }

    public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        if (this == PickMasterPositiveCase.ONLINE_MASTER_WITH_BOILER) {
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
