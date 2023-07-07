package ru.gasworkers.dev.api.auth.registration.through.dto.check;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Accessors(chain = true)
public class CheckThroughRegistrationResponseDto {
    private String status;
    private String message;
    private DataDto data;

    public static CheckThroughRegistrationResponseDto successResponse(Integer orderId, String type, String token) {
        return CheckThroughRegistrationResponseDto.builder()
                .status("0")
                .message("auth__register_through_code_checked")
                .data(DataDto.builder()
                        .id(orderId)
                        .type(type)
                        .token(token)
                        .build())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(NON_NULL)
    @Accessors(chain = true)
    public static class DataDto {
        private Integer id;
        private String type;
        private String token;
    }
}
