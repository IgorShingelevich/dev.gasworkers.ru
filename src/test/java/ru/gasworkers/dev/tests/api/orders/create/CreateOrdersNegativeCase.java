package ru.gasworkers.dev.tests.api.orders.create;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

import java.util.Arrays;
import java.util.stream.Stream;

@AllArgsConstructor
enum CreateOrdersNegativeCase {


    CREATE_ORDERS_EMPTY_REQUEST("empty request"),
    CREATE_ORDERS_MISSING_TYPE("missing type( works as empty request"),
    CREATE_ORDERS_INVALID_TYPE("invalid type (unification  Выбранное значение для тип ошибочно.) "),
    CREATE_ORDERS_INVALID_OBJECT_ID("invalid objectId (unification Выбранное значение для object id некорректно. "),
    CREATE_ORDERS_INVALID_OBJECT_ID_OTHER_USER_HOUSE_OBJECT_ID("other user house object ( 403  \"message\": \"\" ) "),
    CREATE_ORDERS_INVALID_OBJECT_ID_NOT_EXIST_ZERO("invalid objectId - not exist zero"),
    CREATE_ORDERS_INVALID_OBJECT_ID_TYPE("invalid objectId and type"),
    CREATE_ORDERS_INVALID_OBJECT_ID_LENGTH_LONG("invalid objectId length long( general answer -  not specified) ");

    private final String description;
    public CreateOrdersRequestDto getCreateOrdersRequestDto() {
        switch (this) {
            case CREATE_ORDERS_EMPTY_REQUEST:
            case CREATE_ORDERS_MISSING_TYPE:
                return CreateOrdersRequestDto.builder()
                        .build();
            case CREATE_ORDERS_INVALID_TYPE:
                return CreateOrdersRequestDto.builder()
                        .type("invalid type")
                        .build();
            case CREATE_ORDERS_INVALID_OBJECT_ID:
                return CreateOrdersRequestDto.builder()
                        .type("maintenance")
                        .objectId("invalid objectId")
                        .build();
            case CREATE_ORDERS_INVALID_OBJECT_ID_OTHER_USER_HOUSE_OBJECT_ID:
                return CreateOrdersRequestDto.builder()
                        .type("maintenance")
                        .objectId("1611") // other user house object
                        .build();
            case CREATE_ORDERS_INVALID_OBJECT_ID_NOT_EXIST_ZERO:
                return CreateOrdersRequestDto.builder()
                        .type("maintenance")
                        .objectId("0")
                        .build();
            case CREATE_ORDERS_INVALID_OBJECT_ID_TYPE:
                return CreateOrdersRequestDto.builder()
                        .type("invalid type")
                        .objectId("invalid objectId")
                        .build();
            case CREATE_ORDERS_INVALID_OBJECT_ID_LENGTH_LONG:
                return CreateOrdersRequestDto.builder()
                        .type("maintenance")
                        .objectId("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    public CreateOrdersResponseDto getExpectedResponse(Integer orderId, Boolean isInsuranceCase) {
        switch (this) {
            case CREATE_ORDERS_EMPTY_REQUEST:
                return CreateOrdersResponseDto.emptyRequestResponse(orderId, isInsuranceCase);
            case CREATE_ORDERS_MISSING_TYPE:
                return CreateOrdersResponseDto.missingTypeResponse(orderId, isInsuranceCase);
            case CREATE_ORDERS_INVALID_TYPE:
                return CreateOrdersResponseDto.invalidTypeResponse(orderId, isInsuranceCase);
            case CREATE_ORDERS_INVALID_OBJECT_ID:
            case CREATE_ORDERS_INVALID_OBJECT_ID_NOT_EXIST_ZERO:
            case CREATE_ORDERS_INVALID_OBJECT_ID_LENGTH_LONG:
                return CreateOrdersResponseDto.invalidObjectIdResponse(orderId, isInsuranceCase);
            case CREATE_ORDERS_INVALID_OBJECT_ID_OTHER_USER_HOUSE_OBJECT_ID:
                return CreateOrdersResponseDto.objectIdOtherUserHouseObjectResponse(orderId, isInsuranceCase);
            case CREATE_ORDERS_INVALID_OBJECT_ID_TYPE:
                return CreateOrdersResponseDto.invalidObjectIdTypeResponse(orderId, isInsuranceCase);
           default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
