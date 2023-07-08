package ru.gasworkers.dev.api.auth.registration.regular.dto;

import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationRequestDto;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.List;

public class ComplexRegistrationFactory {
    public static ComplexRegistrationRequestDto defaultRandomClient() {
        RandomClient data = new RandomClient();
        return ComplexRegistrationRequestDto.builder()
                .type(UserType.CLIENT.toString())
                .password(data.getPassword())
                .email(data.getEmail())
                .phone(data.getPhone())
                .gender(data.getGender())
                .code(111111)
                .firstName(data.getName())
                .lastName(data.getSurname())
                .middleName(data.getMiddleName())
                .isPhoneSend(null)
                .isHaveContract(false)
                .isIp(false)
                .employedStatus(null)
                .serviceId(null)
                .build();
    }

    public static ComplexRegistrationRequestDto defaultRandomThroughClient() {
        RandomClient data = new RandomClient();
        return ComplexRegistrationRequestDto.builder()
                .type(UserType.CLIENT.toString())
                .password(data.getPassword())
                .email(data.getEmail())
                .phoneAsLong(data.getPhoneLong())
                .gender(data.getGender())
                .code(111111)
                .firstName(data.getName())
                .lastName(data.getSurname())
                .middleName(data.getMiddleName())
                .isPhoneSend(null)
                .isHaveContract(false)
                .isIp(false)
                .employedStatus(null)
                .serviceId(null)
                //from throughRegistrationObjectId())
                .addressId(HouseBuilder.addDefaultHouseRequestDto().getAddressId())
                .objectId(null)
                .startDate(data.getStartDate())
                .endDate(data.getEndDate())
                .timeStarted(null)
                .timeEnded(null)
                .description("error description")
                .noGuide(null)
                .oldEquipments(null)
                .equipments(List.of(StartThroughRegistrationRequestDto.EquipmentsDto.builder()
                                .brandId(AddEquipmentRequestDto.defaultBoilerEquipment().getBrandId())
                                .modelId(AddEquipmentRequestDto.defaultBoilerEquipment().getModelId())
                                .typeId(AddEquipmentRequestDto.defaultBoilerEquipment().getTypeId())
                                .power(AddEquipmentRequestDto.defaultBoilerEquipment().getPower())
                                .customBrand(null)
                                .customModel(null)
                                .build()
                        )
                )
                .time(null)
                .build();
    }
}