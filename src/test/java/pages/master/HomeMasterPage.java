package pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class HomeMasterPage extends BaseMasterPage{

    public final SidebarMasterComponent sidebar;

    public HomeMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
    }

    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        timeTableLocator = driver.$("div.timetable").as("Расписание"),
        switchToListViewLocator = driver.$("div.action-btn.list-type"),
        switchToTabViewLocator = driver.$("div.action-btn.card-type");

    ElementsCollection
        orderCardsCollection = driver.$$("div.order-card"),
        orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
        orderActionDropdownCollection = driver.$$("button.actions__btn"),
        actionsOpenOrderLinkCollection = driver.$$x("//a[@class='actions__slot--link']"),
        actionsArchiveOrderLinkCollection = driver.$$x("(//button[contains(@class,'actions__slot--btn')])");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            timeTableLocator.shouldBe(visible);
        });
    }




}
