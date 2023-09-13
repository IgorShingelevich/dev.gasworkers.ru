package ru.gasworkers.dev.api.orders.cancel.cancelById;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderByIdRequestDto {
    /*{
    "cancel_reason": null,
    "cancel_check_list": [
        "3"
    ]
}*/

    @JsonProperty("cancel_reason")
    private String cancelReason;

    @JsonProperty("cancel_check_list")
    private List<String> cancelCheckList;

    public static CancelOrderByIdRequestDto defaultCancelRequest() {
        return CancelOrderByIdRequestDto.builder()
                .cancelReason("причина автотест")
                .cancelCheckList(List.of("3"))
                .build();
    }


}
