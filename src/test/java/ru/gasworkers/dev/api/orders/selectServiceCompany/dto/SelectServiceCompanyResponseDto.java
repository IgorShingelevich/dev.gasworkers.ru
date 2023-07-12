package ru.gasworkers.dev.api.orders.selectServiceCompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectServiceCompanyResponseDto {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-service-company

    /*  {
          "status": 0,
              "message": "Исполнитель выбран",
              "data": {
          "receipt_id": 4435
      }
      }*/
    private Integer status;
    private String message;
    private DataDto data;

    public static SelectServiceCompanyResponseDtoBuilder successResponse(Integer receiptId) {
        return SelectServiceCompanyResponseDto.builder()
                .status(0)
                .message("Исполнитель выбран")
                .data(DataDto.builder()
                        .receiptId(receiptId)
                        .build());
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        @JsonProperty("receipt_id")
        private Integer receiptId;
    }
}
