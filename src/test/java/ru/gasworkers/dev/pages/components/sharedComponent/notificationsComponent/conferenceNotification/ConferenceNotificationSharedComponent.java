package ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;

import static com.codeborne.selenide.Condition.*;

public class ConferenceNotificationSharedComponent extends BaseComponent {
    String title = "Видеоконсультация онлайн";

    public ConferenceNotificationSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkState(StateConsultation state) {
        switch (state) {
            case CLIENT_WAIT_MASTER:
                checkFinishLoading();
                checkNoticePartialText("У вас сейчас начнётся видеоконсультация.");
                checkChatMasterLink();
                checkCancelConsultationLink();
                checkToOrderButton();
                break;
            case MASTER_START_CONSULTATION:

                break;
            case COMPLETED:

                break;
            default:
                throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
        }
    }    SelenideElement self = driver.$("div[data-test-id='upcoming-conference-notification']").as("Уведомление о предстоящей видеоконсультации"),
            chatMasterButtonLocator = self.$("a.order-1").as("Кнопка чата с мастером"),
            cancelConferenceButtonLocator = self.$("a.order-4").as("Кнопка отмены видеоконсультации"),
            primaryButton = self.$("[data-test-id=\"primary\"]").as("Основная кнопка"),
            noticeTextLocator = self.$("p.gas-notice-primary__text--text").as("Текст уведомления"),
            chatMasterLinkLocator = self.$("a.link-dashed.mb-3.me-4.order-1").as("Ссылка на чат с мастером"),
            cancelConsultationLinkLocator = self.$("a.link-dashed.ms-sm-auto.mb-3.me-4.order-4.order-sm-2").as("Ссылка на отмену видеоконсультации"),
            toOrderButtonLocator = self.$("button[data-test-id='primary']").as("Кнопка Перейти к заказу");

    public void checkNoticePartialText(String text) {
        stepWithRole("Убедиться, что текст уведомления: " + text, () -> {
            noticeTextLocator.shouldHave(partialText(text));
        });
    }

    public void noConsultationComponent() {
        stepWithRole("Убедиться, что компонент Видеоконсультация онлайн не отображается", () -> {
            self.shouldNotBe(visible);
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загрузился компонент Видеоконсультация онлайн", () -> {
            self.shouldBe(visible);
        });
    }

    public void clickChatMasterLink() {
        stepWithRole("Нажать ссылку Написать мастеру в чат", () -> {
            chatMasterLinkLocator.click();
        });
    }

    public void clickCancelConsultationLink() {
        stepWithRole("Нажать ссылку Отменить консультацию", () -> {
            cancelConsultationLinkLocator.click();
        });
    }

    public void clickToOrderButton() {
        stepWithRole("Нажать кнопку Перейти к заказу", () -> {
            toOrderButtonLocator.click();
        });
    }

    public void noChatMasterLink() {
        stepWithRole("Убедиться, что ссылка Написать мастеру в чат не отображается", () -> {
            chatMasterLinkLocator.shouldNotBe(visible);
        });
    }

    public void noCancelConsultationLink() {
        stepWithRole("Убедиться, что ссылка Отменить консультацию не отображается", () -> {
            cancelConsultationLinkLocator.shouldNotBe(visible);
        });
    }

    public void noToOrderButton() {
        stepWithRole("Убедиться, что кнопка Перейти к заказу не отображается", () -> {
            toOrderButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkChatMasterLink() {
        stepWithRole("Убедиться, что ссылка Написать мастеру в чат отображается", () -> {
            chatMasterLinkLocator.shouldBe(visible);
        });
    }

    public void checkCancelConsultationLink() {
        stepWithRole("Убедиться, что ссылка Отменить консультацию отображается", () -> {
            cancelConsultationLinkLocator.shouldBe(visible);
        });
    }

    public void checkToOrderButton() {
        stepWithRole("Убедиться, что кнопка Перейти к заказу отображается", () -> {
            toOrderButtonLocator.shouldBe(visible);
        });
    }

    public void mainButton() {
        stepWithRole("Нажать на основную кнопку", () -> {
            primaryButton.click();
        });
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
