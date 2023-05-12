package ru.gasworkers.dev.api.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class StartRegistrationInputDto {

    public static StartRegistrationInputDto newInstance(String type, String email, String phone, Boolean isPhoneSend) {
        return StartRegistrationInputDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .isPhoneSend(isPhoneSend)
                .build();
    }

    private String type, email, phone;
    @JsonProperty("phone_send")
    private Boolean isPhoneSend;

}
