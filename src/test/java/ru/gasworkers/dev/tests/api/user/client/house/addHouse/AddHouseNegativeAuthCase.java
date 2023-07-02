package ru.gasworkers.dev.tests.api.user.client.house.addHouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HouseRequestDto;

@AllArgsConstructor
enum AddHouseNegativeAuthCase {
    WRONG_TOKEN("Невалидный токен", "12345-54321"),
    MISSING_TOKEN("Пропущен токен", null);

    private final String description;
    @Getter
    private final String token;

    public HouseRequestDto getInputDto() {
        return HouseBuilder.addDefaultHouseRequestDto();
    }

    @Override
    public String toString() {
        return description;
    }

}
