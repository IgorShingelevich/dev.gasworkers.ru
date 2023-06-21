package ru.gasworkers.dev.api.users.client.object;

import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;

// Import the Address class


public class AddHouseObjectBuilder {
    public static AddHouseObjectRequestDTO addObjectRequest() {
        return AddHouseObjectRequestDTO.builder()
                .addressId(2121)
                .companyId(1)
                .branchId(null)
                .accountNumber(null)
                .photos(null)
                .title("my house")
                .build();
    }

    public static AddHouseObjectModelDto addObjectRequest2() {
        AddHouseObjectModelDto.Builder builder = AddHouseObjectModelDto.builder()
                .id(2121)
                .title("my house");

        AddHouseObjectModelDto.Address address = new AddHouseObjectModelDto.Address();
        address.setId(2121);

        builder.address(address);

        return builder.build();
    }
}
