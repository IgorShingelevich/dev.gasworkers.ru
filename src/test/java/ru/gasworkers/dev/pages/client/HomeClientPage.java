package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

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


    SelenideElement
    // clientBlock
        profileBlockFullNameLocator = driver.$(".profile-card .title").as("Full name"),
        profileBlockSinceDateLocator = driver.$(".profile-card .since-date").as("Registration date"),
        profileBlockRatingLocator = driver.$("span.rating-badge").as("Rating"),
        profileBlockReviewsCountLocator = driver.$(".profile-card .reviews").as("Reviews count"),
        profileBlockImageLocator = driver.$(".profile-card").$(".profile-image"),
    //objectsBlock
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
            profileBlockFullNameLocator.shouldBe(visible);
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

    public void checkInitialState( String fullName, String sinceProfileDate) {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            stepWithRole("Убедиться, что  кнопки начальнгого состояния Заполнить профиль, Добавить оборудование и Создать Заказ присутствуют", () -> {
                driver.$(byTagAndText("span", "Заполнить профиль")).shouldBe(visible, Duration.ofSeconds(30)).as("FillProfile button");
                driver.$(byTagAndText("span", "Добавить оборудование")).shouldBe(visible).as("AddEquipment button");
                driver.$(byTagAndText("span", "Создать заказ")).shouldBe(visible).as("CreateOrder button");
            });
            stepWithRole("Убедиться, секция персональных данных содержит: ", () -> {
                checkFullName(fullName);
                stepWithRole("Дата регистрации: " + sinceProfileDate, () -> {
                    profileBlockSinceDateLocator.as("Profile since date").shouldHave(text("Зарегистрирован с " +sinceProfileDate));
                });
                checkRating("5.00");
                checkReviewsCount("0");
            });
            stepWithRole("Убедиться, что отсутствует Информация о последнем заказе и карусель Объекты и оборудование", () -> {
                driver.$("div.section .card-wrapper").shouldNotBe(visible).as("Last order info");
                objectsTitleLocator.shouldNotBe(visible).as("Objects and equipment Title");
                firstObjectLinkLocator.shouldNotBe(visible).as("First object link");


            });
        });
    }

    public HomeClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что загружена Домашняя страница", () -> {

            profileBlockFullNameLocator.shouldBe(visible);
            // TODO add fullName check
            driver.$(".client-objects [data-index='0']").shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public  HomeClientPage checkFullName(String fullName) {
        stepWithRole("Убедиться, что  полное имя: " + fullName, () -> {
            profileBlockFullNameLocator.shouldHave(Condition.text(fullName));
        });
        return this;
    }

    public void checkRating(String rating) {
        stepWithRole("Убедиться, что рейтинг: " + rating, () ->
                profileBlockRatingLocator.shouldHave(Condition.text(rating))
        );
    }

    public void checkReviewsCount(String reviewsCount) {
        stepWithRole("Убедиться, что количество отзывов: " + reviewsCount, () ->
                profileBlockReviewsCountLocator.shouldHave(Condition.text(reviewsCount))
        );
    }

    public void clickPlaceOrderButton() {
        stepWithRole("Нажать кнопку Создать заказ", () ->
                driver.$("#gas__content-header .btn-block")
                        .as("place order button")
                        .shouldBe(interactable)
                        .click()
        );
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
