package ru.gasworkers.dev.extension.user;

import org.junit.jupiter.api.extension.*;
import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;

public final class WithUserExtension implements ParameterResolver {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(WithUser.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomClient();
        registrationApi.complexRegistration(complexDto);
        return User.fromComplexDto(complexDto);
    }

}
