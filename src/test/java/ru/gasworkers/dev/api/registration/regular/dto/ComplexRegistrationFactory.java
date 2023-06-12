package ru.gasworkers.dev.api.registration.regular.dto;

import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

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
                .middleName(data.getPatronymicName())
                .isPhoneSend(null)
                .isHaveContract(false)
                .isIp(false)
                .employedStatus(null)
                .serviceId(null)
                .build();
    }

}
