package ru.gasworkers.dev.api.registration.dto.registration.regular;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.gasworkers.dev.api.registration.dto.registration.regular.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationRequestDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Accessors(chain = true)
public class ComplexRegistrationRequestDto {

    private String type, password, email, phone, gender;
    private Integer code;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("phone_send")
    private Boolean isPhoneSend;

    @JsonProperty("is_have_contract")
    private Boolean isHaveContract;

    @JsonProperty("is_ip")
    private Boolean isIp;

    @JsonProperty("employed_status")
    private String employedStatus;

    @JsonProperty("service_id")
    private Integer serviceId;

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
