package ru.gasworkers.dev.tests.api.user.selfEmployed.complete;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.users.selfEmployed.complete.dto.CompleteRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum CompletePositiveCase {
    /*{
    "is_have_contract": true,
    "is_ip": false
}*/
    WITH_CONTRACT_WITH_IP("With contract with ip"),
    WITH_CONTRACT_NO_IP("With contract no ip"),
    NO_CONTRACT_WITH_IP("No contract with ip"),
    NO_CONTRACT_NO_IP("No contract no ip");

    private final String description;
    private final ComplexRegistrationRequestDto complexSelfEmployedDto = ComplexRegistrationFactory.defaultRandomSelfEmployed();

    public StartRegistrationRequestDto getStartDto() {
        return complexSelfEmployedDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        return complexSelfEmployedDto.toCheckRegularRegistration();
    }

    public FinishRegistrationRequestDto getFinishDto() {
        return complexSelfEmployedDto.toFinishRegistration();
    }

    public LoginRequestDto getLoginDto() {
        return complexSelfEmployedDto.toLogin();
    }

    public CompleteRequestDto getCompleteRoleDto() {
        switch (this) {
            case WITH_CONTRACT_WITH_IP:
                return CompleteRequestDto.builder()
                        .isHaveContract(true)
                        .isIp(true)
                        .build();
            case WITH_CONTRACT_NO_IP:
                return CompleteRequestDto.builder()
                        .isHaveContract(true)
                        .isIp(false)
                        .build();
            case NO_CONTRACT_WITH_IP:
                return CompleteRequestDto.builder()
                        .isHaveContract(false)
                        .isIp(true)
                        .build();
            case NO_CONTRACT_NO_IP:
                return CompleteRequestDto.builder()
                        .isHaveContract(false)
                        .isIp(false)
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }


    @Override
    public String toString() {
        return description;
    }
}
