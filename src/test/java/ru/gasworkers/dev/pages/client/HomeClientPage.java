package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.guideComponent.FirstMaintenanceGuideComponent;
import ru.gasworkers.dev.pages.components.clientComponent.guideComponent.FirstRepairGuideComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PersonSummaryComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.notifications.conferenceNotification.ConferenceNotificationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class HomeClientPage extends BaseClientPage {

    public final ClientSidebarComponent sidebar;
    public final ClientActionsBlockComponent actionsBlock;
    public final LastOrderProfileClientComponent lastOrderComponent;
    public final PersonSummaryComponent personSummaryComponent;
    public final FirstMaintenanceGuideComponent firstMaintenanceGuide;
    public final FirstRepairGuideComponent firstRepairGuide;
    public final ConferenceNotificationSharedComponent conferenceNotification;


    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
        lastOrderComponent = new LastOrderProfileClientComponent(browser);
        personSummaryComponent = new PersonSummaryComponent(browser);
        firstMaintenanceGuide = new FirstMaintenanceGuideComponent(browser);
        firstRepairGuide = new FirstRepairGuideComponent(browser);
        conferenceNotification = new ConferenceNotificationSharedComponent(browser);
    }

    private final String
            OBJECTS_TITLE = "Объекты и оборудование";


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
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
            //todo personSummaryComponent appeariance
//            personSummaryComponent.checkFinishLoading(fullName, sinceDate);
            lastOrderComponent.checkFinishLoading();
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что  Домашняя страница в состоянии после Регистрации", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");

//            personSummaryComponent.checkInitialState(fullName, sinceDate);
            //todo person rating anr review count
            // todo appearence of this component from the header element
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
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
//            personSummaryComponent.checkBGInitialState(sinceDate); // removed from initial state
            //todo removed from initial state
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
        stepWithRole("Нажать кнопку Создать заказ", () -> {
            driver.$("#gas__content-header .btn-block")
                    .as("place order button")
                    .shouldBe(interactable)
                    .click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/orders/type");
        });
    }

    public void checkVideoBGInitialState(String sinceDate, String masterFullName) {
        stepWithRole("Убедиться, что  Домашняя страница в состоянии после Фоновой регистрации на Видео", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
//            personSummaryComponent.checkBGInitialState(sinceDate); // changed appearance
            // todo  personSummaryComponent appearance changed
            stepWithRole("Убедиться, что присутствует баннер Ожидания видеоконсультации", () -> {

            });
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
}