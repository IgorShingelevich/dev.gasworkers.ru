package pages.client;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.components.clientComponent.LastOrderProfileClientComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class HomeClientPage extends BaseClientPage {

    public final LastOrderProfileClientComponent lastOrderProfileClientComponent;
    public final SidebarClientComponent sidebar;





    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        lastOrderProfileClientComponent = new LastOrderProfileClientComponent(browser);
    }



    private final String OBJECTS_TITLE = "Объекты и оборудование";

    // clientCardBlock
    SelenideElement
        profileFullNameLocator = driver.$(".profile-card .title"),
        profileCardSinceDateLocator = driver.$(".profile-card .since-date"),
        profileCardRatingLocator = driver.$(".profile-card.rating-badge"),
        profileCardReviewsLocator = driver.$(".profile-card .reviews"),
        profileCardImageLocator = driver.$(".profile-card").$(".profile-image");

    //objectsBlock
    SelenideElement
        objectsTitleLocator = driver.$(".client-objects .title"),
        firstObjectLinkLocator = driver.$$x("(//div[contains(@class,'title link-blue text-primary pointer')])").get(4), // 0-3 out of visibility
        objectsPreviousButtonLocator = driver.$(".client-objects .slick-arrow.slick-prev"),
        objectsNextButtonLocator = driver.$(".client-objects .slick-arrow.slick-next");
    ElementsCollection
        gotoObjectActionCollection = driver.$$(".actions .actions__slot--link"),
        actionButtonCollection = driver.$$(".actions .actions__btn");

//    @Step("Open client home page")
    public HomeClientPage open() {
        stepWithRole("Открыть домашнюю страницу", () -> {
            driver.open("/profile/client");
            profileFullNameLocator.shouldBe(visible);
        });
        return this;
    }

    public HomeClientPage checkInitialModal() {
        stepWithRole("Проверить начальное модальное окно", () -> {
            stepWithRole(" Убедиться что текст заголовка и подзаголовка правильный", () -> {
                driver.$("div.completed-block h3").shouldHave(text("Укажите Ваш объект и оборудование")).as("Initial title");
                driver.$("div.completed-block p").shouldHave(text("Заполните все данные по газовому оборудованию и мы сможем точнее и быстрее найти вам нужного мастера")).as("Initial subtitle");
            });
            stepWithRole("Убедиться, что присутствуют  кнопки Приступить и Позже", () -> {
                driver.$(byTagAndText("button", "Приступить")).shouldBe(visible).as("StartNow button");
                driver.$(byTagAndText("button", "Позже")).shouldBe(visible).as("Later button");
            });
        });
        return this;
    }

    public void startNowInitialModal() {
        stepWithRole("Нажать кнопку Приступить", () -> {
            driver.$(byTagAndText("button", "Приступить")).click();
        });
    }

    public void laterInitialModal() {
        stepWithRole("Нажать кнопку Позже", () -> {
            driver.$(byTagAndText("button", "Позже")).click();
        });
    }


//    @Step("Убедиться, что Домашняя страница загружена")
    public HomeClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    @Step("Check that client name is {fio}")
    public HomeClientPage checkFio(String fio) {
        stepWithRole("Проверка  ФИО {fio}", () ->
                profileFullNameLocator
                        .as("fio")
                        .shouldHave(Condition.text(fio)));
        return this;
    }

//    @Step("Click [Place order] button")
    public void clickPlaceOrderButton() {
        stepWithRole("Нажать кнопку Создать заказ", () ->
                driver.$("#gas__content-header .btn-block")
                        .as("place order button")
                        .shouldBe(interactable)
                        .click()
        );
    }

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние", () -> {
            stepWithRole("Убедиться, что  кнопки начальнгого состояния Заполнить профиль, Добавить оборудование и Создать Заказ присутствуют", () -> {
                driver.$(byTagAndText("button", "Заполнить профиль")).shouldBe(visible).as("FillProfile button");
                driver.$(byTagAndText("button", "Добавить оборудование")).shouldBe(visible).as("AddEquipment button");
                driver.$(byTagAndText("button", "Создать заказ")).shouldBe(visible).as("CreateOrder button");
            });
            stepWithRole("Убедиться, секция персональных данных содержит: ", () -> {
                stepWithRole("ФИО клиента: ", () -> {
                    profileFullNameLocator.as("Profile full name").shouldHave(text("Иванов Иван Иванович"));
                });
                stepWithRole("Дату регистрации: ", () -> {
                    // TODO registration date
                });
                stepWithRole("Начальный рейтинг клиента 5.00: ", () -> {
                    // TODO registration date
                });
                stepWithRole("Ноль отзывов ", () -> {
                    // TODO registration date
                });

            });
            stepWithRole("Убедиться, что отсутствует Информация о последнем заказе и карусель Объекты и оборудование", () -> {
                driver.$("div.section .card-wrapper").shouldNotBe(visible).as("Last order info");
                objectsTitleLocator.shouldNotBe(visible).as("Objects and equipment Title");
                firstObjectLinkLocator.shouldNotBe(visible).as("First object link");


            });
        });
    }

//    public HomeClientPage clickObjectsPreviousButton() {
//        objectsPreviousButtonLocator.shouldBe(visible).click();
//        return this;
//    }

//    public HomeClientPage clickObjectsNextButton() {
//        objectsNextButtonLocator.shouldBe(visible).click();
//        return this;
//    }


//    public HomeClientPage goto1thObjectActionButtonLocator() {
//        actionButtonCollection.get(3).hover();
//        actionMenuCollection.get(3).shouldBe(visible); //not working - menu collection is not visible
//        actionButtonCollection.get(3).click();
//        gotoObjectActionCollection.get(3).click();
//        return this;
//    }


//    public HomeClientPage partialTextSearch() {
//        objectsTitleLocator.shouldHave(text("Объекты и"));
//        //sout all the text from the locator objectsTitleLocator.shouldHave(text("Объекты и"))
//        System.out.println("objectsTitleLocator.getText" + objectsTitleLocator.getText());
//        return this;
//    }

}
