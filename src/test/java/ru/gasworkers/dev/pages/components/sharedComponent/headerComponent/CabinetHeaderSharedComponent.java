package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Selenide.$;

public class CabinetHeaderSharedComponent extends BaseComponent {
    public CabinetHeaderSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            logoLocator =driver.$("#gas__header .logo").as("Логотип"),
            linkSupportLocator = driver.$x("//a[contains(text(), 'Служба поддержки')]").as("Ссылка на Службу поддержки"),
            linkExitLocator = driver.$x("//a[contains(text(), 'Выйти')]").as("Ссылка на Выйти"),
            approveCancelPageTitleLocator = $("div.recovery-box").as("Заголовок страницы подтверждения отмены заказа");

}
