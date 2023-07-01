package ru.gasworkers.dev.api.users.client.house;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectRequestDTO;

public class HouseFactory {

    public static AddHouseObjectRequestDTO addDefaultHouseRequestDto() {
        return AddHouseObjectRequestDTO.builder()
                .addressId(2121)
                .companyId(1)
                .title("my house")
                .build();
    }

}
