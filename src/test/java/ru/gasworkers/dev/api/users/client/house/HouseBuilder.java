package ru.gasworkers.dev.api.users.client.house;
import ru.gasworkers.dev.api.users.client.house.dto.HouseRequestDto;

public class HouseBuilder {

    public static HouseRequestDto addDefaultHouseRequestDto() {
        return HouseRequestDto.builder()
                .addressId(2121)
                .companyId(1)
                .title("my house")
                .build();
    }

}
