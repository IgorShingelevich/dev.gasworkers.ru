package ru.gasworkers.dev.extension.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.dto.HouseRequestDto;
import ru.gasworkers.dev.api.users.client.house.dto.HouseResponseDto;

public final class WithUserExtension implements ParameterResolver {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LoginApi loginApi = new LoginApi();
    private final HouseApi houseApi = new HouseApi();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(WithUser.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        WithUser annotation = parameterContext.getParameter().getAnnotation(WithUser.class);

        ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();
        registrationApi.complexRegistration(complexDto);

        String token = login(complexDto);
        for (WithHouse house : annotation.houses()) {
            HouseRequestDto inputDto = HouseRequestDto.newInstance(house);
            Integer currentHouseID = houseApi.addHouse(inputDto, token).statusCode(200)
                    .extract().as(HouseResponseDto.class).getData().getId();
        }

        return User.fromComplexDto(complexDto);
    }

    private String login(ComplexRegistrationRequestDto complexUserDto) {
        LoginRequestDto inputDto = LoginRequestDto.asUserPhone(complexUserDto.getPhone(), complexUserDto.getPassword());
        return loginApi.login(inputDto)
                .statusCode(200)
                .extract().as(LoginResponseDto.class)
                .getData().getToken();
    }

}
