package ru.gasworkers.dev.api.users.client.object;

import java.util.List;

public class Objects {
    public static ObjectBuilder myHouse() {
        return ObjectBuilder.builder()
                .id(1345)
                .title("my house")
                .activeOffersCount(0)
                .photos(List.of())
                .address(ObjectBuilder.AddressDTO.builder()
                        .id(2121)
                        .full("Москва, Московская улица")
                        .type("street")
                        .fullWithZip("Россия, Москва, Московская улица")
                        .zip(null)
                        .country("Россия")
                        .countryCode("RU")
                        .region("Москва")
                        .regionType("Москва")
                        .build())
                .equipments(List.of())
                .company(ObjectBuilder.CompanyDTO.builder()
                        .id(1)
                        .title("МособлГаз")
                        .branches(List.of(
                                ObjectBuilder.BranchDTO.builder()
                                        .id(1)
                                        .title("Юг")
                                        .build(),
                                ObjectBuilder.BranchDTO.builder()
                                        .id(2)
                                        .title("Север")
                                        .build(),
                                ObjectBuilder.BranchDTO.builder()
                                        .id(3)
                                        .title("Восток")
                                        .build(),
                                ObjectBuilder.BranchDTO.builder()
                                        .id(4)
                                        .title("Запад")
                                        .build(),
                                ObjectBuilder.BranchDTO.builder()
                                        .id(13)
                                        .title("Юго-Восток")
                                        .build(),
                                ObjectBuilder.BranchDTO.builder()
                                        .id(14)
                                        .title("Северо-Запад")
                                        .build()
                        ))
                        .build())
                .accountNumber(null)
                .createdAt(1687013283)
                .videoExists(false)
                .build();
    }
}
