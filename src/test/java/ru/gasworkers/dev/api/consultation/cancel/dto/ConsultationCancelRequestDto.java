package ru.gasworkers.dev.api.consultation.cancel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationCancelRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    private Boolean refund;
    @JsonProperty("cancel_reason")
    private String cancelReason;
    @JsonProperty("cancel_check_list")
    private String[] cancelCheckList;
}
