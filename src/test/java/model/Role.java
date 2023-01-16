package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.context.BaseRolePages;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;

@AllArgsConstructor
public enum Role {
    CLIENT("Client", ClientPages.class),
    MASTER("Master", MasterPages.class),
    DISPATCHER("Dispatcher", DispatcherPages.class);

    private final String description;
    @Getter
    private final Class<? extends BaseRolePages> pagesObjectClass;

    @Override
    public String toString() {
        return description;
    }

}
