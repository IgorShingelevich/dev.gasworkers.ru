package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;

public class NoticeComponent extends BaseComponent {

    public final RedNoticeComponent redNotice;

    public NoticeComponent(RoleBrowser browser) {
        super(browser);
        redNotice = new RedNoticeComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загрузился компонент Уведомления", () -> {
            self.shouldBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.notice-list-fixed").as("Уведомления"),
            readAllButtonLocator = self.$("button[data-test-id='secondary']").as("Кнопка Прочитать все");

    public void noNotifications() {
        stepWithRole("Убедиться, что уведомлений нет", () -> {
            self.shouldNotBe(visible);
        });
    }


}
