package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.videoBannerHomePageClientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AwaitingNowVideoBGBannerHomePageClientComponent extends BaseComponent {
    public AwaitingNowVideoBGBannerHomePageClientComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        titleText = "Видеоконсультация онлайн",
        chatMasterLinkText = "Написать мастеру в чат",
        cancelConsultationLinkText = "Отменить консультацию",
        toOrderButtonText = "Перейти к заказу";

    SelenideElement
        containerLocator = driver.$("div.w-100.d-flex.justify-content-center.flex-wrap").as("Контейнер компонента Ожидание видеоконсультации"),
        titleLocator = driver.$("span.gas-notice-primary__text--title").as("Заголовок компонента Ожидание видеоконсультации"),
        textLocator = driver.$("p.gas-notice-primary__text--text").as("Текст компонента Ожидание видеоконсультации"),
        chatMasterLinkLocator = driver.$("a.link-dashed.mb-3.me-4").as("Ссылка Написать мастеру в чат"),
        cancelConsultationLinkLocator = driver.$("a.link-dashed.ms-sm-auto.mb-3").as("Ссылка Отменить консультацию"),
        toOrderButtonLocator = driver.$("button.mb-3.btn.btn-primary.disable-outline").as("Кнопка Перейти к заказу");

    public void checkAwaitingNowBGVideoState(String masterFullName) {
        stepWithRole("Убедиться, что компонент Ожидание видеоконсультации загрузился", () -> {
             String text = "У вас сейчас начнётся видеоконсультация. Мастер " + masterFullName +" готовится к ней. Вам придет СМС со ссылкой для подключения к ней";
            containerLocator.shouldBe(visible, Duration.ofSeconds(30));
            titleLocator.shouldHave(text(titleText));
            textLocator.shouldHave(text(text));
            chatMasterLinkLocator.shouldHave(text(chatMasterLinkText));
            cancelConsultationLinkLocator.shouldHave(text(cancelConsultationLinkText));
            toOrderButtonLocator.shouldHave(text(toOrderButtonText));
        });
    }

    public void toOrder () {
        stepWithRole("Нажать на кнопку Перейти к заказу", () -> {
            toOrderButtonLocator.click();
        });
    }

    public void cancelConsultation () {
        stepWithRole("Нажать на ссылку Отменить консультацию", () -> {
            cancelConsultationLinkLocator.click();
        });
    }

    public void chatMaster () {
        stepWithRole("Нажать на ссылку Написать мастеру в чат", () -> {
            chatMasterLinkLocator.click();
        });
    }

}
