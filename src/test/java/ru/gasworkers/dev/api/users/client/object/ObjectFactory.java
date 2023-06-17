package ru.gasworkers.dev.api.users.client.object;

import ru.gasworkers.dev.api.users.client.object.dto.AddObjectRequestDTO;

public class ObjectFactory {
    public static AddObjectRequestDTO createDefaultHouse() {
        return AddObjectRequestDTO.builder()
                .addressId(2121)
                .companyId(1)
                .branchId(null)
                .accountNumber(null)
                .photos(new Integer[0])
                .title("my house")
                .build();
    }
}
