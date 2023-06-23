package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;

@AllArgsConstructor
enum AddHouseObjectPositiveCase {
    OBJECT_DEFAULT_HOUSE(
            "Добавление объекта с дефолтными данными"),
    OBJECT_DEFAULT_HOUSE_DUPLICATE(
            "Добавление объекта с дублирующимися данными для другого пользователя");
    //todo case - add house object for the same user with the same data

    private final String description;
    private final AddHouseObjectRequestDTO addObjectDto = AddHouseObjectBuilder.addHouseObjectRequest();

    public AddHouseObjectRequestDTO getAddObjectDtoDTO() {
        switch (this) {
            case OBJECT_DEFAULT_HOUSE:
                return addObjectDto;
            case OBJECT_DEFAULT_HOUSE_DUPLICATE:
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
