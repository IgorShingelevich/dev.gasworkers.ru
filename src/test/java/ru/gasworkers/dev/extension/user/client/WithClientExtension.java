package ru.gasworkers.dev.extension.user.client;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.users.client.house.dto.HousesRequestDto;
import ru.gasworkers.dev.api.users.client.house.dto.HousesResponseDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.extension.BaseJunitExtension;
import ru.gasworkers.dev.extension.user.User;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class WithClientExtension extends BaseJunitExtension implements BeforeEachCallback, ParameterResolver {

    public static final Namespace NAMESPACE = Namespace.create(WithClientExtension.class);

    private final Class<WithClient> withClientAnnotationClass = WithClient.class;

    @Override
    public void beforeEach(ExtensionContext context) {
        List<Parameter> parameters = Arrays.stream(context.getRequiredTestMethod().getParameters())
                .filter(p -> p.isAnnotationPresent(withClientAnnotationClass))
                .collect(Collectors.toList());

        Map<String, User> users = new HashMap<>();
        for (Parameter parameter : parameters) {
            WithClient annotation = parameter.getAnnotation(withClientAnnotationClass);
            ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();
            registrationApi.complexRegistration(complexDto);
            String token = login(complexDto);

            for (WithHouse house : annotation.houses())
                handleWithHouseAnnotation(house, token);

            users.put(parameter.getName(), User.fromComplexDto(complexDto));
        }

        saveToStore(context, NAMESPACE, users);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(WithClient.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return (User) getFromStore(extensionContext, NAMESPACE, Map.class)
                .get(parameterContext.getParameter().getName());
    }

    private String login(ComplexRegistrationRequestDto complexUserDto) {
        LoginRequestDto inputDto = LoginRequestDto.asUserPhone(complexUserDto.getPhone(), complexUserDto.getPassword());
        return loginApi.login(inputDto)
                .statusCode(200)
                .extract().as(LoginResponseDto.class)
                .getData().getToken();
    }

    private void handleWithHouseAnnotation(WithHouse annotation, String token) {
        // Create house
        HousesRequestDto inputHouseDto = HousesRequestDto.newInstance(annotation);
        HousesResponseDto.DataDto house = clientHousesApi.addHouse(inputHouseDto, token)
                .statusCode(200)
                .extract().as(HousesResponseDto.class).getData();

        // Create equipments
        for (WithEquipment equipment : annotation.equipments()) {
            AddEquipmentRequestDto inputEquipmentDto = AddEquipmentRequestDto.defaultBoilerEquipment();
            equipmentApi.addEquipment(inputEquipmentDto, house.getId(), token)
                    .statusCode(200);
        }

        // Create orders
        for (WithOrder order : annotation.orders()) {
            CreateOrderRequestDto inputOrderDto = new CreateOrderRequestDto(
                    order.value().toString(), house.getId().toString());
            orderApi.createOrder(inputOrderDto, token)
                    .statusCode(200);
        }
    }

}
