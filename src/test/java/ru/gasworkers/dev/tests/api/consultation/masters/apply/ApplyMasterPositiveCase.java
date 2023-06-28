package ru.gasworkers.dev.tests.api.consultation.masters.apply;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum ApplyMasterPositiveCase {
    ONLINE_MASTER_WITH_BOILER("Online master with boiler");
    private final String description;

    public AddHouseObjectRequestDTO getAddObjectDto() {
        return AddHouseObjectBuilder.addDefaultHouseObjectRequest();
    }

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    @Override
    public String toString() {
        return description;
    }

    public CreateOrdersRequestDto getCreateOrdersDto() {
        return CreateOrdersRequestDto.builder()
                .type("consultation")
                .build();
    }

    public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
        if (this == ApplyMasterPositiveCase.ONLINE_MASTER_WITH_BOILER) {
            return OnlineMastersRequestDto.builder()
                    .orderId(orderId)
                    .search("rating")
                    .build();
        }
        throw new EnumNotSupportedException(this);
    }

    public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        if (this == ApplyMasterPositiveCase.ONLINE_MASTER_WITH_BOILER) {
            return PickMasterRequestDto.builder()
                    .orderId(orderId)
                    .online(true)
                    .build();
        }
        throw new EnumNotSupportedException(this);
    }

    public ApplyMasterRequestDto getApplyMasterDto(Integer orderId, Integer timetableId) {
        if (this == ApplyMasterPositiveCase.ONLINE_MASTER_WITH_BOILER) {
            return ApplyMasterRequestDto.builder()
                    .orderId(orderId)
                    .timetableId(timetableId)
                    .description("test description")
                    .now(true)
                    .build();
        }
        throw new EnumNotSupportedException(this);
    }
}
