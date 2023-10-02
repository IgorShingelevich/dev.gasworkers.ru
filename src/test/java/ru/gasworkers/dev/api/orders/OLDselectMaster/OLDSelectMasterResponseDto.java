package ru.gasworkers.dev.api.orders.OLDselectMaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OLDSelectMasterResponseDto {
    private Integer status;
    private String message;
    private DataDto data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        @JsonProperty("receipt_id")
        private Integer receiptId;
    }
}
