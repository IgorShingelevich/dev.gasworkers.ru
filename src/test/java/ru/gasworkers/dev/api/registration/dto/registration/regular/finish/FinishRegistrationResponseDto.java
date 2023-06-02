package ru.gasworkers.dev.api.registration.dto.registration.regular.finish;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinishRegistrationResponseDto {
    private Integer status;
    private String message;
    private List<String> data;

    public static FinishRegistrationResponseDto successResponse() {
        return new FinishRegistrationResponseDto(0, "Регистрация завершена", null);
    }

}
