package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectModelDto;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;

@AllArgsConstructor
enum AddObjectPositiveCase {
    OBJECT_DEFAULT_HOUSE(
            "Добавление объекта с дефолтными данными");

    private final String description;
    private final AddHouseObjectRequestDTO addObjectDto = AddHouseObjectBuilder.addObjectRequest();
//    private final AddHouseObjectModelDto addObjectDto2 = AddHouseObjectBuilder.addObjectRequest2();

    public AddHouseObjectRequestDTO getAddObjectDtoDTO() {
        switch (this) {
            case OBJECT_DEFAULT_HOUSE:
                return addObjectDto;
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
