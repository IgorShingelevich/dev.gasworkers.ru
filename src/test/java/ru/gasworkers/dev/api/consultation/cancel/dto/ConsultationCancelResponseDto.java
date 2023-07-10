package ru.gasworkers.dev.api.consultation.cancel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationCancelResponseDto {

    private Integer status;
    private String message;
    private Object[] data;

    public static ConsultationCancelResponseDto successResponse() {
        return ConsultationCancelResponseDto.builder()
                .status(0)
                .message("Консультация отменена")
                .data(new Object[]{})
                .build();
    }
}
