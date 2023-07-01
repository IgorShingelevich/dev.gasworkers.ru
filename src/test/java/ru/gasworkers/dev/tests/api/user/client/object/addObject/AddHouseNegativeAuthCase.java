package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.users.client.house.HouseFactory;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectRequestDTO;

@AllArgsConstructor
enum AddHouseNegativeAuthCase {
    WRONG_TOKEN("Невалидный токен", "12345-54321"),
    MISSING_TOKEN("Пропущен токен", null);

    private final String description;
    @Getter
    private final String token;

    public AddHouseObjectRequestDTO getInputDto() {
        return HouseFactory.addDefaultHouseRequestDto();
    }

    @Override
    public String toString() {
        return description;
    }

}
