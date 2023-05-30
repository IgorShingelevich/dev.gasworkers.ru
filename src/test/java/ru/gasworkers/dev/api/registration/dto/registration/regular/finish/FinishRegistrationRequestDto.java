package ru.gasworkers.dev.api.registration.dto.registration.regular.finish;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class FinishRegistrationRequestDto {

    private String type, password, email, phone, gender;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("is_have_contract")
    private Boolean isHaveContract;

    @JsonProperty("is_ip")
    private Boolean isIp;

    @JsonProperty("employed_status")
    private String employedStatus;

    @JsonProperty("service_id")
    private Integer serviceId;

}
