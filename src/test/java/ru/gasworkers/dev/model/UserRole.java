package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.pages.context.*;

@AllArgsConstructor
public enum UserRole {
    ADMIN("Admin", null, null),
    CLIENT("Client", 79312534936L, ClientPages.class),
    MASTER("Master", 79917644241L, MasterPages.class),
    SELF_EMPLOYED("Self-employed", null, SelfEmployedPages.class),
    SUPER_DISPATCHER("SuperDispatcher", null, DispatcherPages.class),
    DISPATCHER("Dispatcher", null, DispatcherPages.class),
    SERVICE ("ServiceCompanyStaff", null, ServicePages.class);

    //todo: Dispatcher2, Master2, Client2, etc.

    private final String description;

    @Getter
    private final Long phoneNumber;

    @Getter
    private final Class<? extends BaseRolePages> pagesObjectClass;

    @Override
    public String toString() {
        return description;
    }

}
