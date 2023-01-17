package pages.client;

import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import model.client.ClientRequestType;

import static com.codeborne.selenide.Selectors.byTagAndText;

public final class TypeOrdersClientPage extends BaseClientPage {

    public TypeOrdersClientPage(RoleBrowser browser) {
        super(browser);
    }

    @Step("Выберите тип заказа {requestType}")
    public void selectOrderType(ClientRequestType requestType) {
        driver.$(byTagAndText("button", requestType.toString())).click();
    }

}