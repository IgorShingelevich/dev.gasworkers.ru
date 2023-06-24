package ru.gasworkers.dev.api.orders.create.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrdersResponseDto {
    /*{
    "status": 0,
    "message": "Заказ создан",
    "data": {
        "order_id": 6425,
        "insurance_case": false
    }
}*/
    private Integer status;
    private String message;
    private List<Object> data;
    private FinishRegistrationResponseDto.ErrorsDto errors;
}
