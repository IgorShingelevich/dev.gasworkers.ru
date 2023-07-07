package ru.gasworkers.dev.tests.api.user.client.house.getHouse;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.house.addEquipment.AddEquipmentBuilder;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.getHouse.dto.GetHouseRequestDto;
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
                return AddEquipmentBuilder.buildAddEquipmentBoilerViessmanRequest();
            case ADD_EQUIPMENT_BOILER_BOSH:
                return AddEquipmentBuilder.buildAddEquipmentBoilerBoshRequest();
            case ADD_EQUIPMENT_OVEN_BEON:
                return AddEquipmentBuilder.buildAddEquipmentOwenBeonRequest();

            default:
//                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
                throw new EnumNotSupportedException(this);
        }

    }

    public GetHouseRequestDto getHouseRequestDto() {
        return GetHouseRequestDto.builder()
                .build();
    }

    @Override
    public String toString() {
        return description;
    }
}