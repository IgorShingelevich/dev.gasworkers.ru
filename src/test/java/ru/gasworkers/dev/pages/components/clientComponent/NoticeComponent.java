package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

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
    }

    public void noNotifications() {
        stepWithRole("Убедиться, что уведомлений нет", () -> {
            self.shouldNotBe(visible);
        });
    }
    public void checkMasterStartConsultationNotification() {
        stepWithRole("Убедиться, что уведомление о  видеоконсультации  в  состоянии - Мастер начал консультацию", () -> {
            conferenceNotificationBoxLocator.shouldBe(visible);
            conferenceNotificationTextLocator.shouldNotHave(partialText("У вас сейчас начнётся видеоконсультация."));
            conferenceNotificationTextLocator.shouldHave(partialText("Мастер ожидает вас на консультации. Пожалуйста, перейдите в видео консультацию. В случае невозможности подключения, сообщите мастеру в чате. В противном случае, по истечении 30 минут видеоконсультация будет считаться несостоявшейся. Денежные средства возврату не подлежат."));
            cancelConsultationButtonLocator.shouldBe(visible);
            continueButtonLocator.shouldBe(visible);
            toOrderButtonLocator.shouldNotBe(visible);
        });
    }SelenideElement
            self = driver.$("div.notice-list-fixed").as("Уведомления"),
            conferenceNotificationBoxLocator = self.$("div[data-test-id='upcoming-conference-notification']").as("Уведомление о предстоящей видеоконсультации"),
            conferenceNotificationTextLocator = conferenceNotificationBoxLocator.$("p.gas-notice-primary__text--text").as("Текст уведомления  в уведомлении о предстоящей видеоконсультации"),
            cancelConsultationButtonLocator = conferenceNotificationBoxLocator.$(byTagAndText("span", "Отменить консультацию")).as("Кнопка Отменить консультацию"),
            toOrderButtonLocator = conferenceNotificationBoxLocator.$(byTagAndText("span", "Перейти к заказу")).as("Кнопка Перейти к заказу"),
            continueButtonLocator = conferenceNotificationBoxLocator.$(byTagAndText("span", "Продолжить")).as("Кнопка Продолжить"),
            readAllButtonLocator = self.$("button[data-test-id='secondary']").as("Кнопка Прочитать все");

    public void checkUpcomingConsultationNotification() {
        stepWithRole("Убедиться, что уведомление о предстоящей видеоконсультации отображается", () -> {
            conferenceNotificationBoxLocator.shouldBe(visible);
            conferenceNotificationTextLocator.shouldNotHave(partialText("Мастер ожидает вас на консультации. Пожалуйста, перейдите в видео консультацию. В случае невозможности подключения, сообщите мастеру в чате. В противном случае, по истечении 30 минут видеоконсультация будет считаться несостоявшейся. Денежные средства возврату не подлежат."));
            conferenceNotificationTextLocator.shouldHave(partialText("У вас сейчас начнётся видеоконсультация."));
            continueButtonLocator.shouldNotBe(visible);
            cancelConsultationButtonLocator.shouldBe(visible);
            toOrderButtonLocator.shouldBe(visible);
        });
    }


    public void checkStateConsultation(StateConsultation state) {
        stepWithRole("Убедиться, что  уведомление соответствует состоянию: " + state, () -> {
            checkFinishLoading();
            redNotice.noNotice();
            switch (state) {
                case DRAFT:
                case ORDER_COMPLETED:
                    noNotifications();
                    break;
                case CLIENT_WAIT_MASTER:
                    checkUpcomingConsultationNotification();
                    break;
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                    checkMasterStartConsultationNotification();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });

    }




}
