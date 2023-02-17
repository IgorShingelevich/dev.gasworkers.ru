package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.GuideFirstComponent;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PersonSummaryComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class HomeClientPage extends BaseClientPage {

    public final SidebarClientComponent sidebar;
    public final ActionsBlockClientComponent actionsBlock;
    public final LastOrderProfileClientComponent lastOrderComponent;
    public final PersonSummaryComponent personSummaryComponent;
    public final GuideFirstComponent guideFirst;

    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
        lastOrderComponent = new LastOrderProfileClientComponent(browser);
        personSummaryComponent = new PersonSummaryComponent(browser);
        guideFirst = new GuideFirstComponent(browser);
    }

    private final String OBJECTS_TITLE = "Объекты и оборудование";

    SelenideElement
            fillProfileButtonLocator = driver.$(byTagAndText("span", "Заполнить профиль")).as("Кнопка Заполнить профиль"),
            addEquipmentButtonLocator = driver.$(byTagAndText("span", "Добавить оборудование")).as("Кнопка Добавить оборудование"),
    //objectsBlock
        objectsPreviousButtonLocator = driver.$(".client-objects .slick-arrow.slick-prev"),
        objectsNextButtonLocator = driver.$(".client-objects .slick-arrow.slick-next");
    ElementsCollection
        objectTitleCollection = driver.$$("div.object .title.link-blue.text-primary.pointer").as("Названия объектов"),
        gotoObjectActionCollection = driver.$$(".actions .actions__slot--link"),
        actionButtonCollection = driver.$$(".actions .actions__btn");

    public HomeClientPage open() {
        stepWithRole("Открыть домашнюю страницу", () -> {
            driver.open("/profile/client");
        });
        return this;
    }

    public HomeClientPage checkInitialGuide() {
        stepWithRole("Убедиться, что представлены компоненты страницы Вводного гида: ", () -> {
            stepWithRole(" Убедиться что текст заголовка и подзаголовка правильный", () -> {
                driver.$("div.completed-block h3").shouldBe(visible, Duration.ofSeconds(30)).shouldHave(text("Укажите Ваш объект и оборудование")).as("Initial title");
                driver.$("div.completed-block p").shouldHave(text("Заполните все данные по газовому оборудованию и мы сможем точнее и быстрее найти вам нужного мастера")).as("Initial subtitle");
            });
            stepWithRole("Убедиться, что присутствуют  кнопки Приступить и Позже", () -> {
                driver.$(byTagAndText("span", "Приступить")).shouldBe(visible).as("StartNow button");
                driver.$(byTagAndText("span", "Позже")).shouldBe(visible).as("Later button");
            });
        });
        return this;
    }

    public void clickStartNowInitialModal() {
        stepWithRole("Нажать кнопку Приступить", () -> {
            driver.$(byTagAndText("span", "Приступить")).click();
        });
    }

    public void clickLaterInitialModal() {
        stepWithRole("Нажать кнопку Позже", () -> {
            driver.$(byTagAndText("span", "Позже")).click();
        });
    }

    public HomeClientPage checkFinishLoading(String fullName, String sinceDate) {
        stepWithRole("Убедиться, что загружена Домашняя страница", () -> {

            personSummaryComponent.checkFinishLoading(fullName, sinceDate);
            lastOrderComponent.checkFinishLoading();
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public void checkInitialState(String fullName, String sinceDate) {
        stepWithRole("Убедиться, что  Домашняя страница в состоянии после Регистрации", () -> {
            personSummaryComponent.checkInitialState(fullName, sinceDate);
            stepWithRole("Убедиться, что присутствует кнопка Заполнить профиль", () -> {
                fillProfileButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что присутствует кнопка Добавить оборудование", () -> {
                addEquipmentButtonLocator.shouldBe(visible);
            });
        });
    }

    public void checkBGInitialState(String sinceDate) {
        stepWithRole("Убедиться, что  Домашняя страница в состоянии после Фоновой регистрации", () -> {
            personSummaryComponent.checkBGInitialState(sinceDate);
            stepWithRole("Убедиться, что присутствует кнопка Заполнить профиль", () -> {
                fillProfileButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что присутствует компонент Информация о последнем заказе", () -> {
                lastOrderComponent.checkFinishLoading();
                // TODO check background Address and Equipment and ServiceDate
            });
            stepWithRole("Убедиться, что присутствует компонент Объекты и оборудование", () -> {
                stepWithRole("Убедиться что присутствует только один объект", () -> {
                    objectTitleCollection.should(CollectionCondition.size(1));
                });
                stepWithRole("Убедиться что имя объекта - Мой Дом", () -> {
                    objectTitleCollection.get(0).shouldHave(text("Мой дом"));
                });
                // TODO check background Equipment
            });
        });
    }

    public void clickPlaceOrderButton() {
        stepWithRole("Нажать кнопку Создать заказ", () ->
                driver.$("#gas__content-header .btn-block")
                        .as("place order button")
                        .shouldBe(interactable)
                        .click()
        );
    }
}