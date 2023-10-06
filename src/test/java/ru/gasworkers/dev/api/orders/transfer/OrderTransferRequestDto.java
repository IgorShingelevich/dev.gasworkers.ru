package ru.gasworkers.dev.api.orders.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTransferRequestDto {
    /*{
    "order_id": 10725,
    "executor_id": 39,
    "executor_type": "company",
    "full_transfer": true,
    "selected_date": null,
    "first_accept_price": 3100,
    "full_repair_price": 3999
}*/
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("executor_id")
    private Integer executorId;
    @JsonProperty("executor_type")
    private String executorType;
    @JsonProperty("full_transfer")
    private Boolean fullTransfer;
    @JsonProperty("selected_date")
    private String selectedDate;
    @JsonProperty("first_accept_price")
    private Integer firstAcceptPrice;
    @JsonProperty("full_repair_price")
    private Integer fullRepairPrice;
        /*{
        // fullTransferSserRequest
    "order_id": 10725,
    "executor_id": 39,
    "executor_type": "company",
    "full_transfer": true,
    "selected_date": null,
    "first_accept_price": 3100,
    "full_repair_price": 3999
}*/

    public static OrderTransferRequestDto fullTransferRequest(Integer orderId, Integer executorCompanyId) {
        return OrderTransferRequestDto.builder()
                .orderId(orderId)
                .executorId(executorCompanyId)
                .executorType("company")
                .fullTransfer(true)
                .selectedDate(null)
                .firstAcceptPrice(3100)
                .fullRepairPrice(3999)
                .build();
    }

    public static OrderTransferRequestDto fullTransferSssrRequest(Integer orderId) {
        return OrderTransferRequestDto.builder()
                .orderId(orderId)
                .executorId(39)
                .executorType("company")
                .fullTransfer(true)
                .selectedDate(null)
                .firstAcceptPrice(3100)
                .fullRepairPrice(3999)
                .build();
    }

}
