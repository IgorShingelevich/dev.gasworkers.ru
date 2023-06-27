package ru.gasworkers.dev.api.consultation.masters.apply.dto;

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
public class ApplyMasterRequestDto {
    /*curl --request POST \
    "https://api.dev.gasworkers.ru/api/v1/consultation/masters/19/apply" \
    --header "Content-Type: application/json" \
    --header "Accept: application/json" \
    --data "{
    \"order_id\": 14,
    \"timetable_id\": 13,
    \"description\": \"Consequatur excepturi perferendis est est.\",
    \"now\": true
}"*/

    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("timetable_id")
    private Integer timetableId;
    private String description;
    private Boolean now;


}
