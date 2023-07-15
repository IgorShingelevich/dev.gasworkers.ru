package ru.gasworkers.dev.tests.api.user.client.house.getHouse;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentBuilder;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum GetHousePositiveCase {
    ADD_EQUIPMENT_BOILER("Add equipment boiler Viessman"),
    ADD_EQUIPMENT_BOILER_BOSH("Add equipment boiler Bosh"),
    ADD_EQUIPMENT_OVEN_BEON("Add equipment Owen Beon");
    private final String description;


    public AddEquipmentRequestDto getAddEquipmentDto() {
        switch (this) {
            case ADD_EQUIPMENT_BOILER:
                return AddEquipmentBuilder.boilerViessmanRequest();
            case ADD_EQUIPMENT_BOILER_BOSH:
                return AddEquipmentBuilder.boilerBoshRequest();
            case ADD_EQUIPMENT_OVEN_BEON:
                return AddEquipmentBuilder.owenBeonRequest();

            default:
//                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
                throw new EnumNotSupportedException(this);
        }

    }

    public GetClientHousesRequestDto getHouseRequestDto() {
        return GetClientHousesRequestDto.builder()
                .build();
    }

    @Override
    public String toString() {
        return description;
    }
}
