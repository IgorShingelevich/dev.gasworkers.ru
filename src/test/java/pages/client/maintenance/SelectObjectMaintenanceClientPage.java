package pages.client.maintenance;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

public final class SelectObjectMaintenanceClientPage extends BaseClientPage {

    private final FocusHeaderComponent focusHeaderComponent;

    public SelectObjectMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        focusHeaderComponent = new FocusHeaderComponent(browser);
    }

    @Step("Выбрать объект по индексу {index}")
    public void selectObjectByIndex(int index) {
        driver.$$(".card-object button")
                .shouldHave(CollectionCondition.sizeGreaterThan(index))
                .get(index).click();
    }

}
