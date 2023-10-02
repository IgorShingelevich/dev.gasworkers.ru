package ru.gasworkers.dev.api.orders.makeOffer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeOfferResponseDto {

    private Integer status;
    private String message;
    private List<DataDto> data;

    public static MakeOfferResponseDto successResponse() {
        return MakeOfferResponseDto.builder()
                .status(0)
                .message("Участие подтверждено")
                .data(List.of())
                .build();
    }

    public class DataDto {
    }

}
