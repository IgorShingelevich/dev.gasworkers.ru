package ru.gasworkers.dev.api.users.selfEmployed.complete.dto;

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
public class CompleteRequestDto {

    @JsonProperty("is_have_contract")
    private Boolean isHaveContract;
    @JsonProperty("is_ip")
    private Boolean isIp;
}
