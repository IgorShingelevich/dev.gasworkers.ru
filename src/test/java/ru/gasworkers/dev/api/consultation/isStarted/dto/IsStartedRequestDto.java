package ru.gasworkers.dev.api.consultation.isStarted.dto;

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
public class IsStartedRequestDto {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("main_filter")
    private String mainFilter;
}
