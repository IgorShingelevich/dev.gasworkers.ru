package ru.gasworkers.dev.api.orders.checkList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCheckListResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static SaveCheckListResponseDto successResponse() {
        return SaveCheckListResponseDto.builder()
                .status(0)
                .message("Чек лист заполнен")
                .data(Collections.emptyList())
                .build();
    }

    public static class DataDto {
    }
}
