package ru.gasworkers.dev.pages.client;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.client.ClientRequestType;

import static com.codeborne.selenide.Selectors.byTagAndText;

public final class TypeOrdersClientPage extends BaseClientPage {

    public TypeOrdersClientPage(RoleBrowser browser) {
        super(browser);
    }

//    @Step("Выберите тип заказа {requestType}")
    public void selectOrderType(ClientRequestType requestType) {
        stepWithRole("Выбрать тип заказа:  " + requestType, () -> {
            driver.$(byTagAndText("span", requestType.toString())).click();
            System.out.println("Select requestType: " + requestType);
        });

    }

}
