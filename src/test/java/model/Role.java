package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.context.*;

@AllArgsConstructor
public enum Role {
    CLIENT("Client", 79288010225L, ClientPages.class),
    MASTER("Master", 79917644241L, MasterPages.class),
    DISPATCHER("Dispatcher", null, DispatcherPages.class),
    SERVICE ("Service", null, ServicePages.class);

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
