package ru.gasworkers.dev.api.registration.dto.registration;

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
    private File expectedStartResponseFile,
        expectedCheckResponseFile;

    public static CheckRegularRegistrationCodeInputDto newInstance(Integer code, String type, String email, String phone, File expectedStartResponseFile, File expectedCheckResponseFile) {
        return CheckRegularRegistrationCodeInputDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .code(code)
                .expectedStartResponseFile(expectedStartResponseFile)
                .expectedCheckResponseFile(expectedCheckResponseFile)
                .build();
    }

}
