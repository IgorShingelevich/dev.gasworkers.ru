package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectResponseDTO;

@AllArgsConstructor
enum AddHouseObjectPositiveCase {
    OBJECT_DEFAULT_HOUSE(
            "Добавление объекта с дефолтными данными"),
    OBJECT_DEFAULT_HOUSE_DUPLICATE(
            "Добавление объекта с дублирующимися данными для другого пользователя"),
    ADD_HOUSE_OBJECT_DUPLICATE_TITLE(
            "Добавление объекта с уже существующим title");
    //todo case - add house object for the same user with the same data

    private final String description;
    private final AddHouseObjectRequestDTO addObjectDto = AddHouseObjectBuilder.addHouseObjectRequest();

    public AddHouseObjectRequestDTO getAddObjectDtoDTO() {
        switch (this) {
            case OBJECT_DEFAULT_HOUSE:
                return addObjectDto;
            case OBJECT_DEFAULT_HOUSE_DUPLICATE:
                return addObjectDto;
            case ADD_HOUSE_OBJECT_DUPLICATE_TITLE:
                return addObjectDto.setTitle("my house");
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
