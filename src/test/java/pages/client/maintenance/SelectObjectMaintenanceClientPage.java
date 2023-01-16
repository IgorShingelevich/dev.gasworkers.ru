package pages.client.maintenance;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;

public final class SelectObjectMaintenanceClientPage extends BaseClientPage {

    public SelectObjectMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
    }

    @Step("Select object by index {index}")
    public void selectObjectByIndex(int index) {
        driver.$$(".card-object button")
                .shouldHave(CollectionCondition.sizeGreaterThan(index))
                .get(index).click();
    }

}
