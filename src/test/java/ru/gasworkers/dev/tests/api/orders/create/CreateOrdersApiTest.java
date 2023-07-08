package ru.gasworkers.dev.tests.api.orders.create;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.ORDERS)
@Story("Создание заказа")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.ORDERS)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class CreateOrdersApiTest extends BaseApiTest {
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case: ")
    void positiveTestCase(CreateOrdersPositiveCase testCase, @WithUser User client) {
        step("Create order", () -> {
            String token = loginApi.getTokenPhone(client);

            CreateOrdersResponseDto createResponse = createOrdersApi.createOrders(testCase.getCreateRequestDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class);
            Integer orderId = createResponse.getData().getOrderId();
            Boolean insuranceCase = createResponse.getData().getIsInsuranceCase();

            CreateOrdersResponseDto actualResponse = CreateOrdersResponseDto.successResponse(orderId, insuranceCase);
            CreateOrdersResponseDto expectedResponse = testCase.getCreateResponseDto(orderId, insuranceCase);

            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case: ")
    void positiveThroughTestCase(CreateOrdersPositiveCase testCase, @WithThroughUser(withOrderType = @WithOrderType(type = "maintenance")) User client) {
        step("Create order", () -> {
            String token = loginApi.getTokenThrough(client);

            CreateOrdersResponseDto createResponse = createOrdersApi.createOrders(testCase.getCreateRequestDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class);
            Integer orderId = createResponse.getData().getOrderId();
            Boolean insuranceCase = createResponse.getData().getIsInsuranceCase();

            CreateOrdersResponseDto actualResponse = CreateOrdersResponseDto.successResponse(orderId, insuranceCase);
            CreateOrdersResponseDto expectedResponse = testCase.getCreateResponseDto(orderId, insuranceCase);

            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersNegativeCase.class)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case: ")
    void negativeTestCaseV2(CreateOrdersNegativeCase testCase, @WithUser User client) {
        String token = loginApi.getTokenPhone(client);
        step("Create order", () -> {
            CreateOrdersResponseDto actualResponse = createOrdersApi.createOrders(testCase.getCreateOrdersRequestDto(), token)
                    .statusCode(422)
                    .extract().as(CreateOrdersResponseDto.class);

            CreateOrdersResponseDto expectedResponse = testCase.getExpectedResponse(null, null); // Set initial values as null

            if (actualResponse.getData() != null) {
                Integer orderId = actualResponse.getData().getOrderId();
                Boolean isInsuranceCase = actualResponse.getData().getIsInsuranceCase();
                expectedResponse = testCase.getExpectedResponse(orderId, isInsuranceCase);
            }

            assertResponse(expectedResponse, actualResponse);
        });
    }
}
