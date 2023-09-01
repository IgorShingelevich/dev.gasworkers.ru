package ru.gasworkers.dev.api.consultation.resume;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeConsultationResponse {
    private Integer status;
    private String message;
    private List<DataDto> data;

    public static class DataDto {
    }
}
