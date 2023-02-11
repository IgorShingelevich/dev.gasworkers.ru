package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PersonSummaryComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class HomeClientPage extends BaseClientPage {

    public final LastOrderProfileClientComponent lastOrderComponent;
    public final SidebarClientComponent sidebar;
    public final PersonSummaryComponent personSummaryComponent;





    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        lastOrderComponent = new LastOrderProfileClientComponent(browser);
        personSummaryComponent = new PersonSummaryComponent(browser);
    }



    private final String OBJECTS_TITLE = "Объекты и оборудование";


    SelenideElement
    //objectsBlock
        objectsTitleLocator = driver.$(".client-objects .title"),
        firstObjectLinkLocator = driver.$$x("(//div[contains(@class,'title link-blue text-primary pointer')])").get(4), // 0-3 out of visibility
        objectsPreviousButtonLocator = driver.$(".client-objects .slick-arrow.slick-prev"),
        objectsNextButtonLocator = driver.$(".client-objects .slick-arrow.slick-next");
    ElementsCollection
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
            // TODO add fullName check
            personSummaryComponent.checkFinishLoading(fullName, sinceDate);
            lastOrderComponent.checkFinishLoading();
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
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
