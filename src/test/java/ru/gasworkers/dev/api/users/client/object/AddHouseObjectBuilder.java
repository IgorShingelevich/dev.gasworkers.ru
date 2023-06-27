package ru.gasworkers.dev.api.users.client.object;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;

public class AddHouseObjectBuilder {
    public static AddHouseObjectRequestDTO addDefaultHouseObjectRequest() {
        return AddHouseObjectRequestDTO.builder()
                .addressId(2121)
                .companyId(1)
                .branchId(null)
                .accountNumber(null)
                .photos(null)
                .title("my house")
                .build();
    }
}
