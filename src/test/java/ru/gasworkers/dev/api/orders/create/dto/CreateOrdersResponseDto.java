package ru.gasworkers.dev.api.orders.create.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
@Builder
public class CreateOrdersResponseDto {

    private Integer status;
    private String message;
    @Nullable
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class ErrorsDto {
        @JsonProperty("type")
        private List<String> type;

        @JsonProperty("object_id")
        private List<String> objectId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class DataDto {
        @JsonProperty("order_id")
        @Nullable
        private Integer orderId;

        @JsonProperty("insurance_case")
        @Nullable
        private Boolean isInsuranceCase;
    }

    public static CreateOrdersResponseDto successResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .status(0)
                .message("Заказ создан")
                .data(DataDto.builder()
                        .orderId(orderId)
                        .isInsuranceCase(isInsuranceCase)
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto invalidTypeResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Выбранное значение для тип ошибочно.")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Выбранное значение для тип ошибочно."))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto emptyRequestResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Поле тип обязательно для заполнения.")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Поле тип обязательно для заполнения."))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto missingTypeResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Тип заказа отсутствует")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Тип заказа отсутствует"))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto objectIdOtherUserHouseObjectResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Объект  не принадлежит пользователю")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Объект  не принадлежит пользователю"))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto invalidObjectIdResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Выбранное значение для object id некорректно.")
                .errors(ErrorsDto.builder()
                        .objectId(Arrays.asList("Выбранное значение для object id некорректно."))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto invalidObjectIdTypeResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Выбранное значение для тип ошибочно. (и еще 1 ошибка)")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Выбранное значение для тип ошибочно."))
                        .objectId(Arrays.asList("Выбранное значение для object id некорректно."))
                        .build())
                .build();
    }

    public static CreateOrdersResponseDto invalidObjectIdLengthLongResponse(Integer orderId, Boolean isInsuranceCase) {
        return CreateOrdersResponseDto.builder()
                .message("Длина идентификатора объекта должна быть не более 255 символов")
                .errors(ErrorsDto.builder()
                        .type(Arrays.asList("Длина идентификатора объекта должна быть не более 255 символов"))
                        .build())
                .build();
    }
}
