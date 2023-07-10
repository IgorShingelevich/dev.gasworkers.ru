package ru.gasworkers.dev.extension.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;

public final class WithSelfEmployedExtension implements ParameterResolver {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LoginApi loginApi = new LoginApi();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(WithSelfEmployed.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(User.class);
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        WithSelfEmployed annotation = parameterContext.getParameter().getAnnotation(WithSelfEmployed.class);

        ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomSelfEmployed();
        registrationApi.complexRegistration(complexDto);
        return User.fromComplexDto(complexDto);
    }

}
