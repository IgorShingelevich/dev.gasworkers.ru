package ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class ConferenceNotificationSharedComponent extends BaseComponent {
    String title = "Видеоконсультация онлайн";

    public ConferenceNotificationSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void mainButton() {
        stepWithRole("Нажать на основную кнопку", () -> {
            primaryButton.click();
        });
    }

    public boolean isDisplayed(boolean b) {
        return self.isDisplayed();
    }

    void checkClientReadinessNotificationText(String masterFullName) {
        String clientDescription = "У вас сейчас начнётся видеоконсультация. Мастер " + masterFullName + " готовится к ней. Вам придет СМС со ссылкой для подключения к ней";
        stepWithRole("Проверка текста уведомления о готовности клиента", () -> {
            self.shouldHave(text(title));
            self.shouldHave(text(clientDescription));
        });
    }

    void checkMasterReadinessNotificationText(String orderNumber, String equipmentName) {
        stepWithRole("Проверка текста уведомления о готовности мастера", () -> {
            String masterDescription1 = "Клиент ожидает от вас начала видеоконсультации.",
                    masterDescription2 = "Ваш номер заказа",
                    masterDescription3 = "Вас приглашают провести видеоконсультацию прямо сейчас по оборудованию: " + equipmentName + ".",
                    masterDescription4 = "Пожалуйста, начните консультацию. В случае невозможности подключения, сообщите клиенту в чате. В противном случае по истечении 30 минут видеоконсультация будет считаться несостоявшейся. Денежные средства вам не будут зачислены.",
                    masterDescription = masterDescription1 + " " + masterDescription2 + " " + orderNumber + ". " + masterDescription4 + " " + equipmentName;
            self.shouldHave(text(title));
            self.shouldHave(text(masterDescription));
            self.shouldHave(text(masterDescription2));
            self.shouldHave(text(masterDescription3));
            self.shouldHave(text(masterDescription4));
        });
    }

    SelenideElement self = driver.$("div[data-test-id='upcoming-conference-notification']").as("Уведомление о предстоящей видеоконсультации"),
            chatMasterButtonLocator = self.$("a.order-1").as("Кнопка чата с мастером"),
            cancelConferenceButtonLocator = self.$("a.order-4").as("Кнопка отмены видеоконсультации"),
            primaryButton = self.$("[data-test-id=\"primary\"]").as("Основная кнопка");

    public void checkClientWaitingMasterButtonState() {
        self.shouldHave(Condition.partialText("Перейти к заказу"));
    }

    public void checkMasterStartConferenceButtonState() {
        self.shouldHave(Condition.partialText("Начать консультацию"));
    }

    public void checkClientJoinConferenceButtonState() {
        self.shouldHave(Condition.partialText("Продолжить"));
    }

    public void checkClientFindNewMasterButtonState() {
        self.shouldHave(Condition.partialText("Найти нового мастера"));
    }
}
