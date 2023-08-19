//package ru.gasworkers.dev.pages.context;
//
//import com.codeborne.selenide.SelenideDriver;
//import lombok.AllArgsConstructor;
//import ru.gasworkers.dev.model.Role;
//import ru.gasworkers.dev.model.browser.RoleBrowser;
//@AllArgsConstructor
//public enum RolePages {
//    CLIENT(Role.CLIENT),
//    DISPATCHER(Role.DISPATCHER),
//    MASTER(Role.MASTER);
//
//    private final Role role;
//    public BaseRolePages getRolePages(Role role) {
//        RoleBrowser roleBrowser = RoleBrowser.instance(.........);
//        switch (this) {
//            case CLIENT:
//                return new ClientPages(roleBrowser.getDriver());
//            case DISPATCHER:
//                return new DispatcherPages(roleBrowser.getDriver());
//            case MASTER:
//                return new MasterPages(roleBrowser.getDriver());
//            default:
//                throw new IllegalArgumentException("Unknown role: " + this);
//        }
//    }
//}
