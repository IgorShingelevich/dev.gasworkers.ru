package ru.gasworkers.dev.api.consultation.cancelRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationCancelRequestRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("cancel_reason")
    private String cancelReason;
    @JsonProperty("cancel_check_list")
    private List<CancelCheckListDto> cancelCheckList;

    @Data
    private static class CancelCheckListDto {
    }
}
