package ru.gasworkers.dev.api.consultation.cancelRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationCancelRequestResponseDto {
    private Integer status;
    private String message;
    private List<DataDto> data;

    @Data
    private static class DataDto {
    }
}
