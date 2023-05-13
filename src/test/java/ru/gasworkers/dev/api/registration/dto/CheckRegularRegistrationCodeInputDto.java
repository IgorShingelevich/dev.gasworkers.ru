package ru.gasworkers.dev.api.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRegularRegistrationCodeInputDto {

    private Integer code;
    private String type, email, phone;
//    @JsonProperty("expectedResponseFile")
    private File expectedResponseFile;

    public static CheckRegularRegistrationCodeInputDto newInstance(Integer code, String type, String email, String phone, File expectedResponseFile) {
        return CheckRegularRegistrationCodeInputDto.builder()
                .code(code)
                .type(type)
                .email(email)
                .phone(phone)
                .expectedResponseFile(expectedResponseFile)
                .build();
    }

}
