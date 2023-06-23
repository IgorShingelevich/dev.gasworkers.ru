package ru.gasworkers.dev.api.users.client.equipment;

import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;

public class AddEquipmentBuilder {
    public static AddEquipmentRequestDto buildAddEquipmentBoilerRequest() {
        return AddEquipmentRequestDto.builder()
                .power(35)
                .photos(new String[]{})
                .videos(new String[]{})
                .modelId(2199)
                .customModel(null)
                .customBrand(null)
                .allWorks(true)
                .typeId(1)
                .brandId(25)
                .build();
    }
}
