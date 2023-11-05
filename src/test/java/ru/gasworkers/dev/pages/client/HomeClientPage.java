package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Cookie;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.RedNoticeComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PersonSummaryComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.SharedHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.guideComponent.PlayGuideComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification.ConsultationNotificationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class HomeClientPage extends BaseClientPage {

    public final ClientSidebarComponent sidebar;
    public final SharedHeaderComponent header;
    public final ClientActionsBlockComponent actionsBlock;
    public final LastOrderProfileClientComponent lastOrderComponent;
    public final PersonSummaryComponent personSummaryComponent;
    public final PlayGuideComponent guide;
    public final ConsultationNotificationSharedComponent consultationNotification;
    public final RedNoticeComponent redNotice;


    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
        header = new SharedHeaderComponent(browser);
        lastOrderComponent = new LastOrderProfileClientComponent(browser);
        personSummaryComponent = new PersonSummaryComponent(browser);
        guide = new PlayGuideComponent(browser);
        consultationNotification = new ConsultationNotificationSharedComponent(browser);
        redNotice = new RedNoticeComponent(browser);
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

    public void open(String token) {
        open("profile/client", token);
    }

    /*public String getToken() {
        return driver.getWebDriver().manage().getCookieNamed("Authorization").getValue().replace("Bearer ", "");
    }*/

    public String getToken() {
        // Get all cookies from the WebDriver instance
        Set<Cookie> cookies = driver.getWebDriver().manage().getCookies();

        // Find the cookie named "auth._token.local" in the set of cookies
        Optional<Cookie> authorizationCookie = cookies.stream()
                .filter(cookie -> "auth._token.local".equals(cookie.getName()))
                .findFirst();

        // If the "auth._token.local" cookie is found, extract the token
        if (authorizationCookie.isPresent()) {
            String tokenValue = authorizationCookie.get().getValue();
            return tokenValue.replace("Bearer ", "");
        } else {
            // Handle the case where the "auth._token.local" cookie is not found
            throw new NoSuchElementException("auth._token.local cookie not found");
        }
    }


  /*  public void open(String token) {
        driver.getWebDriver().manage().addCookie(
                new Cookie("Authorization", "Bearer " + token, "dev.gasworkers.ru", "/", null));
        driver.open("/profile/client");
    }*/

    public void checkFillProfileButton() {
        stepWithRole("Убедиться, что присутствует кнопка Заполнить профиль", () -> {
            fillProfileButtonLocator.shouldBe(visible);
        });
    }

    public void noFillProfileButton() {
        stepWithRole("Убедиться, что отсутствует кнопка Заполнить профиль", () -> {
            fillProfileButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkInitialGuide() {
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

    public void checkFinishLoading(String fullName, String sinceDate) {
        stepWithRole("Убедиться, что загружена Домашняя страница", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
            //todo personSummaryComponent appeariance
//            personSummaryComponent.checkFinishLoading(fullName, sinceDate);
            lastOrderComponent.checkFinishLoading();
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загружена Домашняя страница", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(5));
        });
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
                // TODO check background Address and Equipments and ServiceDate
            });
            stepWithRole("Убедиться, что присутствует компонент Объекты и оборудование", () -> {
                stepWithRole("Убедиться что присутствует только один объект", () -> {
                    objectTitleCollection.should(CollectionCondition.size(1));
                });
                stepWithRole("Убедиться что имя объекта - Мой Дом", () -> {
                    objectTitleCollection.get(0).shouldHave(text("Мой дом"));
                });
                // TODO check background Equipments
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
                // TODO check background Address and Equipments and ServiceDate
            });
            stepWithRole("Убедиться, что присутствует компонент Объекты и оборудование", () -> {
                stepWithRole("Убедиться что присутствует только один объект", () -> {
                    objectTitleCollection.should(CollectionCondition.size(1));
                });
                stepWithRole("Убедиться что имя объекта - Мой Дом", () -> {
                    objectTitleCollection.get(0).shouldHave(text("Мой дом"));
                });
                // TODO check background Equipments
            });
        });
    }

    public void checkUrl() {
        urlChecker.urlContains("profile/client");
    }


    public void checkRedirectOrderCardToSelectService(StateRepair state, ClientPages clientPages) {
        clientPages.getHomePage().lastOrderComponent.checkFinishLoading();
        switch (state) {
            case PUBLISHED:
            case HAS_SERVICE_OFFER:
            case HAS_SUPER_OFFER_SD_PROCESS:
                stepWithRole("Убедиться, что произошел редирект на страницу Выбора услуги", () -> {
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getSelectServicePage().checkUrl();
                });
                break;
            case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
            case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
            case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
            case WAIT_SERVICE_MASTER_SD_PROCESS:
            case MASTER_START_WORK:
            case MATERIAL_INVOICE_ISSUED:
            case MATERIAL_INVOICE_PAID:
            case ACTIONS_INVOICE_ISSUED:
            case ACTIONS_INVOICE_PAID:
                stepWithRole("Убедиться, что произошел редирект на страницу Карточки заказа", () -> {
                    clientPages.getHomePage().lastOrderComponent.open();
                    clientPages.getOrderCardPage().checkUrl();
                });
                break;
            default:
                throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
        }
    }
}