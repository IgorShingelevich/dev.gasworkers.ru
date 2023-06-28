package ru.gasworkers.dev.api.consultation.isStarted.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class IsStartedResponseDto {
    private String status;
    private String message;
    private DataDto data;
    private ErrorDto error;

    public static IsStartedResponseDto successStatusFalseResponse() {
        return IsStartedResponseDto.builder()
                .status("0")
                .message("СТатус начала конференции")
                .data(DataDto.builder()
                        .status(false)
                        .build())
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class DataDto {
        private Boolean status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class ErrorDto {
        private String code;
        private String message;
    }
}
