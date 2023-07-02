package ru.gasworkers.dev.api.consultation.masters.apply.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class ApplyMasterResponseDto {
    /*{
    "status": 0,
    "message": "Мастер выбран",
    "data": {
        "order_id": 6542,
        "receipt_id": 3999
    }
}*/
    private Integer status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class ErrorsDto {
        @JsonProperty("order_id")
        private String orderId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class DataDto {
        @JsonProperty("order_id")
        private Integer orderId;
        @JsonProperty("receipt_id")
        private Integer receiptId;
    }
}
