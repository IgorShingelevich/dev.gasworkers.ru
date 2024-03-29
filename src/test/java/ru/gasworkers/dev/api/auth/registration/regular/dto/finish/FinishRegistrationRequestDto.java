package ru.gasworkers.dev.api.auth.registration.regular.dto.finish;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Accessors(chain = true)
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

    @JsonProperty("referer_code")
    private String refererCode;

    @JsonProperty("referer_type")
    private String refererType;

}
