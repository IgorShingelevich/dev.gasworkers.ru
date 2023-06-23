package ru.gasworkers.dev.tests.api.user.client.equipment;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentBuilder;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;


@AllArgsConstructor
enum AddEquipmentPositiveCase {
ADD_EQUIPMENT_BOILER("Add equipment boiler");
    private final String description;
   private final  AddEquipmentRequestDto addEquipmentDto = AddEquipmentBuilder.buildAddEquipmentBoilerRequest();



    public AddEquipmentRequestDto getAddEquipmentDto() {
        switch (this) {
            case ADD_EQUIPMENT_BOILER:
                return addEquipmentDto;
            default:
//                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
                throw new EnumNotSupportedException(this);
        }

    }

    @Override
    public String toString() {
        return description;
    }
}
