package ru.gasworkers.dev.tests.api.user.client.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.users.client.object.ObjectFactory;
import ru.gasworkers.dev.api.users.client.object.dto.AddObjectRequestDTO;
import ru.gasworkers.dev.api.users.client.object.dto.AddObjectResponseDTO;

@AllArgsConstructor
enum AddObjectPositiveCase {
    OBJECT_DEFAULT_HOUSE(
            "Добавление объекта с дефолтными данными");

    private final String description;
    private final AddObjectRequestDTO addObjectDto = ObjectFactory.createDefaultHouse();

    public AddObjectRequestDTO getAddObjectDtoDTO() {
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
