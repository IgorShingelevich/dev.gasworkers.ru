package ru.gasworkers.dev.api.registration.regular.dto.finish;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
