package ru.gasworkers.dev.tests.api.user.client.house.addHouse;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HouseRequestDto;
import ru.gasworkers.dev.api.users.client.house.dto.HouseResponseDto;

@AllArgsConstructor
enum AddHousePositiveCase {
    OBJECT_DEFAULT_HOUSE("Добавление объекта с дефолтными данными"),
    OBJECT_DEFAULT_HOUSE_DUPLICATE("Добавление объекта с дублирующимися данными для другого пользователя"),
    ADD_HOUSE_OBJECT_DUPLICATE_TITLE("Добавление объекта с уже существующим title");
    //todo case - add house object for the same user with the same data

    private final String description;

    public HouseRequestDto getInputDto() {
        HouseRequestDto inputDto = HouseBuilder.addDefaultHouseRequestDto();
        return this == ADD_HOUSE_OBJECT_DUPLICATE_TITLE ? inputDto.setTitle("my house") : inputDto;
    }

    public HouseResponseDto getExpectedResponse() {
        return HouseResponseDto.successResponse();
    }

    @Override
    public String toString() {
        return description;
    }

}
