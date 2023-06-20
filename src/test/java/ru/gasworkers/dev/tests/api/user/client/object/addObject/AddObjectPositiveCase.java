package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.object.ObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.ObjectModelDto;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddObjectRequestDTO;

@AllArgsConstructor
enum AddObjectPositiveCase {
    OBJECT_DEFAULT_HOUSE(
            "Добавление объекта с дефолтными данными");

    private final String description;
//    private final AddObjectRequestDTO addObjectDto = ObjectBuilder.addObjectRequest();
    private final ObjectModelDto addObjectDto2 = ObjectBuilder.addObjectRequest2();

    public ObjectModelDto getAddObjectDtoDTO() {
        switch (this) {
            case OBJECT_DEFAULT_HOUSE:
                return addObjectDto2;
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
