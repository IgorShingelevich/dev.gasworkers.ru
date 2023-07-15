package ru.gasworkers.dev.api.users.client.house;
import ru.gasworkers.dev.api.users.client.house.dto.HousesRequestDto;

public class ClientHousesBuilder {

    public static HousesRequestDto addDefaultHouseRequestDto() {
        return HousesRequestDto.builder()
                .addressId(2121)
                .companyId(1)
                .title("my house")
                .build();
    }

}
