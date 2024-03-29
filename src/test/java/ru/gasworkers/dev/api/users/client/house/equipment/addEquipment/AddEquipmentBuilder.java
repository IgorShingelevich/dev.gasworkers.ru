package ru.gasworkers.dev.api.users.client.house.equipment.addEquipment;

import lombok.Builder;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
@Builder
public class AddEquipmentBuilder {
    public static AddEquipmentRequestDto boilerViessmanRequest() {
        return AddEquipmentRequestDto.builder()
                .power(Double.valueOf(35))
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

    public static AddEquipmentRequestDto boilerBoshRequest() {
        return AddEquipmentRequestDto.builder()
                .power(40.8)
                .photos(new String[]{})
                .videos(new String[]{})
                .modelId(496)
                .customModel(null)
                .customBrand(null)
                .allWorks(true)
                .typeId(1)
                .brandId(10)
                .build();
    }

    public static AddEquipmentRequestDto owenBeonRequest() {
        return AddEquipmentRequestDto.builder()
                .power(null)
                .photos(new String[]{})
                .videos(new String[]{})
                .modelId(2519)
                .customModel(null)
                .customBrand(null)
                .allWorks(true)
                .typeId(20)
                .brandId(74)
                .build();
    }


}
