package ru.gasworkers.dev.pages.components.sharedComponent.checkStateOrderCardComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class CheckStateOrderCardComponent  extends BaseComponent {
    public CheckStateOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }



}
