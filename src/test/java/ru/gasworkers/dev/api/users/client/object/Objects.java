package ru.gasworkers.dev.api.users.client.object;

import java.util.List;

@Deprecated

public class Objects {
    public static OLDObjectBuilder myHouse() {
        return OLDObjectBuilder.builder()
                .id(1345)
                .title("my house")
                .activeOffersCount(0)
                .photos(List.of())
                .address(OLDObjectBuilder.AddressDTO.builder()
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
                .company(OLDObjectBuilder.CompanyDTO.builder()
                        .id(1)
                        .title("МособлГаз")
                        .branches(List.of(
                                OLDObjectBuilder.BranchDTO.builder()
                                        .id(1)
                                        .title("Юг")
                                        .build(),
                                OLDObjectBuilder.BranchDTO.builder()
                                        .id(2)
                                        .title("Север")
                                        .build(),
                                OLDObjectBuilder.BranchDTO.builder()
                                        .id(3)
                                        .title("Восток")
                                        .build(),
                                OLDObjectBuilder.BranchDTO.builder()
                                        .id(4)
                                        .title("Запад")
                                        .build(),
                                OLDObjectBuilder.BranchDTO.builder()
                                        .id(13)
                                        .title("Юго-Восток")
                                        .build(),
                                OLDObjectBuilder.BranchDTO.builder()
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
