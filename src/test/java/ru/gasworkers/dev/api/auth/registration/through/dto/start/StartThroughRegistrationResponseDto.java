package ru.gasworkers.dev.api.auth.registration.through.dto.start;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class StartThroughRegistrationResponseDto {

    private Integer status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    public static StartThroughRegistrationResponseDto successResponse() {
        DataDto dataDto = new DataDto();
        dataDto.setSecondsLeft("60");
        return StartThroughRegistrationResponseDto.builder()
                .status(0)
                .message("auth__register_through_code_sent")
                .data(dataDto)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorsDto {
        private String code;
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataDto {
        @JsonProperty("seconds_left")
        private String secondsLeft;
    }

}
