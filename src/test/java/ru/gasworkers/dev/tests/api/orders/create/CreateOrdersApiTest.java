package ru.gasworkers.dev.tests.api.orders.create;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.extension.user.client.WithClient;
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
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();

//    @Test
//    @WithClient(houses = {
//            @WithHouse(orders = @WithOrder("asfasfas")),
//            @WithHouse(order = {@WithOrder("asfasf"), @WithOrder("afsasfaf12")})
//    })
//    void test() {

    @Disabled
    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case: ")
    void positiveTestCase(CreateOrdersPositiveCase testCase, @WithClient User client) {
        step("Create order", () -> {
            String token = loginApi.getTokenPhone(client);

            CreateOrderResponseDto createResponse = createOrdersApi.createOrder(testCase.getCreateRequestDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class);
            Integer orderId = createResponse.getData().getOrderId();
            Boolean insuranceCase = createResponse.getData().getIsInsuranceCase();

            CreateOrderResponseDto actualResponse = CreateOrderResponseDto.successResponse(orderId, insuranceCase);
            CreateOrderResponseDto expectedResponse = testCase.getCreateResponseDto(orderId, insuranceCase);

            assertResponse(expectedResponse, actualResponse);
        });
    }
//
//    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case: ")
    void positiveThroughTestCase(CreateOrdersPositiveCase testCase, @WithThroughUser(withOrderType = @WithOrderType(type = "maintenance")) User client) {
        step("Create order", () -> {
            String token = loginApi.getTokenThrough(client);

            CreateOrderResponseDto createResponse = createOrdersApi.createOrder(testCase.getCreateRequestDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class);
            Integer orderId = createResponse.getData().getOrderId();
            Boolean insuranceCase = createResponse.getData().getIsInsuranceCase();

            CreateOrderResponseDto actualResponse = CreateOrderResponseDto.successResponse(orderId, insuranceCase);
            CreateOrderResponseDto expectedResponse = testCase.getCreateResponseDto(orderId, insuranceCase);

            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CreateOrdersNegativeCase.class)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case: ")
    void negativeTestCaseV2(CreateOrdersNegativeCase testCase, @WithClient User client) {
        String token = loginApi.getTokenPhone(client);
        step("Create order", () -> {
            CreateOrderResponseDto actualResponse = createOrdersApi.createOrder(testCase.getCreateOrdersRequestDto(), token)
                    .statusCode(422)
                    .extract().as(CreateOrderResponseDto.class);

            CreateOrderResponseDto expectedResponse = testCase.getExpectedResponse(null, null); // Set initial values as null

            if (actualResponse.getData() != null) {
                Integer orderId = actualResponse.getData().getOrderId();
                Boolean isInsuranceCase = actualResponse.getData().getIsInsuranceCase();
                expectedResponse = testCase.getExpectedResponse(orderId, isInsuranceCase);
            }

            assertResponse(expectedResponse, actualResponse);
        });
    }
}
