package ru.gasworkers.dev.api.administration.totalPrice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalPriceResponseDto {
    Integer status;
    String message;
    DataDto data;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        Integer total;
        ReceiptsDto[] receipts;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ReceiptsDto {
            String title;
            Integer amount;
            Boolean paid;
            @JsonProperty("created_at")
            String createdAt;
        }
    }

}
