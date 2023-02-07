package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class SidebarSelfEmployedComponent extends BaseSidebarComponent{
    public SidebarSelfEmployedComponent(RoleBrowser browser){
    super(browser);
    }
    SelenideElement orderFromNotificationCollection = $$(".item item").filterBy(text("45/22/077/002024")).first();
        //$$(By.xpath("//div[@class='item item']")).get(0);
        //$$("[class^=notifications][class*=gas-scrollbar-inline]").//first();

    //method Selenide element  shold be visible
    public void orderFromNotificationCollectionShouldBeVisible() {
        orderFromNotificationCollection.shouldBe(visible);
    }

}
