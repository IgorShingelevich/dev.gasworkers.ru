package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class HomeMasterPage extends BaseMasterPage{

    public final SidebarMasterComponent sidebar;

    public HomeMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
    }

    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы").as("Заголовок страницы"),
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
            timeTableLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
    }




}
