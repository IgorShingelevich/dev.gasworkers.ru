package ru.gasworkers.dev.api.auth.registration.through.dto.check;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CheckThroughRegistrationRequestDto {
    /*{
    "code": "111111",
    "email": "shing453453elevich@gmail.com",
    "phone": 70017654373
}*/

    private Integer code;
    private String email, type;
    private Long phone;

    @Builder
    public static CheckThroughRegistrationRequestDto newInstance(Integer code, String email, Long phone) {
        return CheckThroughRegistrationRequestDto.builder()
                .code(code)
                .email(email)
                .phone(phone)
                .build();
    }


}
