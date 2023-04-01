package ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.SearchComponent;

public class SearchCertificateAndEquipmentSelfEmployedComponent extends BaseComponent {

    public final SearchComponent searchComponent;

    public SearchCertificateAndEquipmentSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        searchComponent = new SearchComponent(browser);
    }

    private final String
            placeholderText = "Введите модель данного оборудования";


    public void checkInitialState() {
        stepWithRole("Убедиться, что компонент поиска в начальном состоянии", () -> {
            searchComponent.checkInitialState(placeholderText);
        });
    }
}
