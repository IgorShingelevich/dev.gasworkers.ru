package ru.gasworkers.dev.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;

public abstract class BaseJunitExtension {

    protected final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    protected final LoginApi loginApi = new LoginApi();

    protected final ClientHousesApi clientHousesApi = new ClientHousesApi();

    protected final CreateOrderApi orderApi = new CreateOrderApi();
    protected final AddEquipmentApi equipmentApi = new AddEquipmentApi();

    protected void saveToStore(ExtensionContext context, Namespace namespace, Object value) {
        context.getStore(namespace).put(getUniqueTestId(context), value);
    }

    protected <T> T getFromStore(ExtensionContext context, Namespace namespace, Class<T> valueClass) {
        return context.getStore(namespace).get(getUniqueTestId(context), valueClass);
    }

    private String getUniqueTestId(ExtensionContext context) {
        return context.getRequiredTestClass().getName() + ":" + context.getRequiredTestMethod().getName();
    }

}
