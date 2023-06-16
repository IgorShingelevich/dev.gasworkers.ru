package ru.gasworkers.dev.api.registration.regular.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ComplexRegistrationRequestDto {
    private String type, password, email, phone, gender,
            firstName, lastName, middleName, employedStatus;
    private Integer code, serviceId;
    private Boolean isPhoneSend, isHaveContract, isIp;

    public StartRegistrationRequestDto toStartRegistration() {
        return StartRegistrationRequestDto.newInstance(type, email, phone, isPhoneSend);
    }

    public CheckRegistrationRequestDto toCheckRegistration() {
        return CheckRegistrationRequestDto.newInstance(code, type, email, phone);
    }

    public FinishRegistrationRequestDto toFinishRegistration() {
        return FinishRegistrationRequestDto.builder()
                .type(type)
                .password(password)
                .email(email)
                .phone(phone)
                .gender(gender)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .isHaveContract(isHaveContract)
                .isIp(isIp)
                .employedStatus(employedStatus)
                .serviceId(serviceId)
                .build();
    }
}