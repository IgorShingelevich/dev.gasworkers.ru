package pages.client;

import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import model.client.ClientOrderType;

import static com.codeborne.selenide.Selectors.byTagAndText;

public final class TypeOrdersClientPage extends BaseClientPage {

    public TypeOrdersClientPage(RoleBrowser browser) {
        super(browser);
    }

    @Step("Select order type")
    public void selectOrderType(ClientOrderType orderType) {
        driver.$(byTagAndText("button", orderType.toString())).click();
    }

}
