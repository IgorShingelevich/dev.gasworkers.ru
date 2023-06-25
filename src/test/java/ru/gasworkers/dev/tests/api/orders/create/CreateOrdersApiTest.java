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
            CreateOrdersResponseDto createResponse = createOrdersApi.create(testCase.getCreateRequestDto())
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class);
            Integer orderId = createResponse.getData().getOrderId();
            Boolean insuranceCase = createResponse.getData().getInsuranceCase();

            CreateOrdersResponseDto actualResponse = CreateOrdersResponseDto.successResponse(orderId, insuranceCase);
//            CreateOrdersResponseDto expectedResponse = CreateOrdersResponseDto.successResponse(orderId, insuranceCase);
            CreateOrdersResponseDto expectedResponse = testCase.getCreateResponseDto(orderId, insuranceCase);

            assertResponse(expectedResponse, actualResponse);
        });
    }
}
