package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.context.BaseRolePages;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;

@AllArgsConstructor
public enum Role {
    CLIENT("Client", 79288010225L, ClientPages.class),
    MASTER("Master", 79917644241L, MasterPages.class),
    DISPATCHER("Dispatcher", null, DispatcherPages.class);

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
