package ru.gasworkers.dev.api.registration.dto.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class StartRegistrationRequestDto {

    private String type, email, phone;
    @JsonProperty("phone_send")
    private Boolean isPhoneSend;

    public static StartRegistrationRequestDto newInstance(String type, String email, String phone, Boolean isPhoneSend) {
        return StartRegistrationRequestDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .isPhoneSend(isPhoneSend)
                .build();
    }

}
