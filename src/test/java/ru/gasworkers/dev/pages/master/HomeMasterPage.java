package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;

public class HomeMasterPage extends BaseMasterPage{

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;


    public HomeMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
    }

    SelenideElement
        timeTableLocator = driver.$("div.timetable").as("Расписание"),
        toggleTimeTableModeButtonLocator = driver.$("div.p-default").as("Переключатель режима расписания"),
        listViewButtonLocator = driver.$("div.action-btn.list-type").as("Переключатель вида расписания (список)"),
        tabViewButtonLocator = driver.$("div.action-btn.card-type");

    ElementsCollection
        todayOrderNumberLinkCollection = driver.$$(".gas-box .item-flex a").as("Номера заказов на сегодня"),
        orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer").as("Номера заказов в истории заказов"),
        orderActionDropdownButtonCollection = driver.$$("button.actions__btn").as("Дропдаун кнопка  действия над заказом в истории заказов"),
        actionsOpenOrderLinkCollection = driver.$$("a.actions__slot--link").as("Ссылка открыть заказ в дропдауне действия над заказом в истории заказов");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            stepWithRole("Убедиться, что переключатель режима расписания отображается", () -> {
                toggleTimeTableModeButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что расписание отображается", () -> {
                timeTableLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что список заказов на сегодня отображается", () -> {
                todayOrderNumberLinkCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
            });
            sidebar.
        });
    }




}
