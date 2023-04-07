package ru.gasworkers.dev.pages.client.video;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.NavRightNowTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.NavScheduleTimeTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;

public class ConsultationVideoClientPage extends BaseClientPage {

    public final FocusHeaderComponent focusHeader;
    public final StepperComponent stepper;
    public final NavRightNowTabConsultationVideoClientComponent rightNowTab;
    public final NavScheduleTimeTabConsultationVideoClientComponent scheduleTimeTab;

    public ConsultationVideoClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        rightNowTab = new NavRightNowTabConsultationVideoClientComponent(browser);
        scheduleTimeTab = new NavScheduleTimeTabConsultationVideoClientComponent(browser);

    }

    private final String
            urlPrefixText = "https://dev.gasworkers.ru/orders/consultation/",
            title = "Выберите мастера",
            subtitle = "Оплатите видеоконсультацию, консультация начнется в указанное время или сразу после оплаты, если выбран мастер онлайн",
            banner = "Для проведения консультации выберите один из вариантов консультации: “Консультация прямо сейчас” или ”Выбрать время”, а также подберите подходящего для вас мастера",
            rightNowNavTab = "Консультация прямо сейчас",
            scheduleTimeNavTab = "Выбрать время";

    SelenideElement
            titleLocator = driver.$("div p.h3").as("Заголовок страницы Консультация по видео"),
            subtitleLocator = driver.$("div p.medium").as("Подзаголовок страницы Консультация по видео"),
            bannerLocator = driver.$("div.gas-tip__text .first-text").as("Баннер страницы Консультация по видео");
    ElementsCollection
            navTabsLocator = driver.$$("div.nav-btns div").as("Вкладки страницы Консультация по видео");

    public void navRightNowTab() {
        stepWithRole("Перейти на вкладку Консультация прямо сейчас", () -> {
            navTabsLocator.get(0).shouldHave(text(rightNowNavTab)).click();
            rightNowTab.checkFinishLoading();
        });
    }

    public void navScheduleTimeTab() {
        stepWithRole("Перейти на вкладку Выбрать время", () -> {
            navTabsLocator.get(1).shouldHave(text(scheduleTimeNavTab)).click();
            scheduleTimeTab.checkFinishLoading();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Консультация по видео загрузилась", () -> {
//            focusHeader.checkFinishLoading(); // changed header all over the site
            checkUrlStartsWith(urlPrefixText);
            // todo new header
//            stepper.checkFinishLoading();
            // todo stepper missing
            // todo stepper state

            rightNowTab.checkFinishLoading();
            scheduleTimeTab.checkFinishLoading();
        });
    }

    public void defaultBGVideoState() {
        stepWithRole("Убедиться, что страница Видео консультация  находится в состоянии после фоновой регистрации", () -> {
            rightNowTab.checkFinishLoading();
            rightNowTab.checkDefaultState();
            navScheduleTimeTab();
            //todo scheduleTimeTab all checks
            scheduleTimeTab.checkFinishLoading();
            scheduleTimeTab.checkDefaultState();
            navRightNowTab();
        });
    }
}
//todo scheduleTimeTab all checks
// todo stepper state
