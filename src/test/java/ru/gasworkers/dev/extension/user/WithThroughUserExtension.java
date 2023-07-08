package ru.gasworkers.dev.extension.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;

public final class WithThroughUserExtension implements ParameterResolver {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(WithThroughUser.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        WithThroughUser annotation = parameterContext.getParameter().getAnnotation(WithThroughUser.class);

        ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomThroughClient();


        WithOrderType[] orderTypes = annotation.withOrderType();
        if (orderTypes.length > 0) {
            WithOrderType orderType = orderTypes[0]; // Assuming only one order type is specified
            if (orderType != null) {
                registrationApi.complexThroughRegistration(complexDto.setOrderType(orderType.type()));
            }
        }

        return User.fromComplexDto(complexDto);
    }
}
