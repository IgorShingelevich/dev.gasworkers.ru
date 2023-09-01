package ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class ConsultationNotificationSharedComponent extends BaseComponent {
    String title = "Видеоконсультация онлайн";

    public ConsultationNotificationSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkState(StateConsultation state) {
        switch (state) {
            case DRAFT:
            case MASTER_COMPLETE_CONSULTATION:
            case MASTER_FILLED_RESUME:
            case ORDER_COMPLETED:
                noNoticeConsultationComponent();
                break;
            case CLIENT_WAIT_MASTER:
                checkFinishLoading();
                checkNoticePartialText("У вас сейчас начнётся видеоконсультация.");
                noProceedButton();
                checkChatMasterLink();
                checkCancelConsultationLink();
                checkToOrderButton();
                break;
            case MASTER_START_CONSULTATION:
            case CLIENT_JOIN_CONSULTATION:
                checkFinishLoading();
                checkNoticePartialText("Мастер ожидает вас на консультации. Пожалуйста, перейдите в видео консультацию.");
                noToOrderButton();
                checkChatMasterLink();
                checkCancelConsultationLink();
                checkProceedButton();
                break;
            default:
                throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
        }
    }

    public void checkNoticePartialText(String text) {
        stepWithRole("Убедиться, что текст уведомления: " + text, () -> {
            noticeTextLocator.shouldHave(partialText(text));
        });
    }

    public void noNoticeConsultationComponent() {
        stepWithRole("Убедиться, что компонент уведомление Видеоконсультация онлайн не отображается", () -> {
            self.shouldNotBe(visible);
        });
    }    SelenideElement self = driver.$("div[data-test-id='upcoming-conference-notification']").as("Уведомление о предстоящей видеоконсультации"),
            noticeTextLocator = self.$("p.gas-notice-primary__text--text").as("Текст уведомления"),
            primaryButton = self.$("[data-test-id=\"primary\"]").as("Основная кнопка"),
            proceedButtonLocator = self.$(byTagAndText("span", "Продолжить")).as("Кнопка Продолжить"),
            chatMasterLinkLocator = self.$(byTagAndText("a", "Написать мастеру в чат")).as("Ссылка на чат с мастером"), //todo: change
            cancelConsultationLinkLocator = self.$(byTagAndText("a", "Отменить консультацию")).as("Ссылка на отмену консультации"), //todo: change
            toOrderButtonLocator = self.$(byTagAndText("span", "Перейти к заказу")).as("Кнопка Перейти к заказу"); //todo: change

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загрузился компонент уведомление Видеоконсультация онлайн", () -> {
            self.shouldBe(visible);
        });
    }

    public void checkProceedButton() {
        stepWithRole("Убедиться, что кнопка Продолжить отображается", () -> {
            proceedButtonLocator.shouldBe(visible);
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

    public void clickProceedButton() {
        stepWithRole("Нажать кнопку Продолжить", () -> {
            proceedButtonLocator.click();
        });
    }

    public void noProceedButton() {
        stepWithRole("Убедиться, что кнопка Продолжить не отображается", () -> {
            proceedButtonLocator.shouldNotBe(visible);
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
