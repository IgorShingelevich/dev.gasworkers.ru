package ru.gasworkers.dev.api.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRegularRegistrationCodeDTO {
    private Integer code;
    private String type;
    private String email;
    private String phone;
    @JsonProperty("expectedResponseFile")
    private File expectedResponseFile;

    public static CheckRegularRegistrationCodeDTO newInstance(Integer code, String type, String email, String phone, File expectedResponseFile) {
        return CheckRegularRegistrationCodeDTO.builder()
                .code(code)
                .type(type)
                .email(email)
                .phone(phone)
                .expectedResponseFile(expectedResponseFile)
                .build();
    }
}
