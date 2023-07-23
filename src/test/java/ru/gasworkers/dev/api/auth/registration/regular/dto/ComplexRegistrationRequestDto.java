package ru.gasworkers.dev.api.auth.registration.regular.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.check.CheckThroughRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationRequestDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ComplexRegistrationRequestDto {
    private String userType, password, email, phone, gender, firstName, lastName, middleName, fullName,
            employedStatus, approveDate, selectedDate;
    private Integer code, serviceId, refererCode, refererType;
    private Boolean isPhoneSend, isHaveContract, isIp;

    //    from throughRegistration registration
    private String orderType;
    private Integer objectId, addressId, time;
    private String startDate, endDate, timeStarted, timeEnded, description;
    private Boolean noGuide;
    private List<StartThroughRegistrationRequestDto.OldEquipmentsDto> oldEquipments;
    private List<StartThroughRegistrationRequestDto.EquipmentsDto> equipments;
    private Long phoneAsLong;

    //from selfEmployedAndMaster registration


    public StartRegistrationRequestDto toStartRegistration() {
        return StartRegistrationRequestDto.newInstance(userType, email, phone, isPhoneSend);
    }

    public CheckRegistrationRequestDto toCheckRegularRegistration() {
        return CheckRegistrationRequestDto.newInstanceRegularRegistration(code, userType, email, phone);
    }

    public FinishRegistrationRequestDto toFinishRegistration() {
        return FinishRegistrationRequestDto.builder()
                .type(userType)
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

    public StartThroughRegistrationRequestDto toStartThroughRegistration() {
        return StartThroughRegistrationRequestDto.builder()
                .orderType(orderType)
                //  need to convert to string phone to integer
                .phone(phoneAsLong)
                .email(email)
                .objectId(objectId)
                .addressId(addressId)
                .startDate(startDate)
                .endDate(endDate)
                .time(time)
                .timeStarted(timeStarted)
                .timeEnded(timeEnded)
                .description(description)
                .noGuide(noGuide)
                .oldEquipments(oldEquipments)
                .equipments(equipments)
                .build();
    }

    public CheckThroughRegistrationRequestDto toCheckThroughRegistration() {
        return CheckThroughRegistrationRequestDto.newInstance(code, email, phoneAsLong);
    }

    public LoginRequestDto toLogin() {
        return LoginRequestDto.newInstance(userType, email, phone, null, password);
    }
}