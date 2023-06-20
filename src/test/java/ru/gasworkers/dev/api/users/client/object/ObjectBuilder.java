package ru.gasworkers.dev.api.users.client.object;

import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddObjectRequestDTO;

// Import the Address class
import ru.gasworkers.dev.api.users.client.object.ObjectModelDto.Address;

public class ObjectBuilder {
    public static AddObjectRequestDTO addObjectRequest() {
        return AddObjectRequestDTO.builder()
                .addressId(2121)
                .companyId(1)
                .branchId(null)
                .accountNumber(null)
                .photos(null)
                .title("my house")
                .build();
    }

    public static ObjectModelDto addObjectRequest2() {
        ObjectModelDto.Builder builder = ObjectModelDto.builder()
                .id(2121)
                .title("my house");

        ObjectModelDto.Address address = new ObjectModelDto.Address();
        address.setId(2121);

        builder.address(address);

        return builder.build();
    }
}
