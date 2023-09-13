package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair.PUBLISHED;

public class AllNotificationsPage extends BasePage {

    public final ClientActionsBlockComponent actionBlock;
    public final ClientSidebarComponent sidebar;
    private final String
            NOTIFICATIONS_TITLE = "Уведомления";
    ElementsCollection
            notificationsCollection = driver.$$("div.notice-large").as("коллекция  уведомлений"),
            unreadNotificationsBadgeCollection = driver.$$("div.notice-large .item-header .w-25").as("коллекция непрочитанных уведомлений"),
            readButtonCollection = driver.$$("div.notice-large .w-25 .link-dark-blue").as("коллекция кнопок Прочитать"),
            orderNumberLinkCollection = driver.$$("div.notice-large .item-header .link-dark-blue").as("коллекция ссылок на номер заказа");

    SelenideElement
            pageTitleLocator = driver.$(".page-title"),
            readAllButtonLocator = driver.$(byTagAndText("span", "Прочитать все")),
            allNotificationsHaveReadButtonLocator = driver.$(byTagAndText("span", "Все уведомления прочитаны"));

    public AllNotificationsPage(RoleBrowser browser) {
        super(browser);
        actionBlock = new ClientActionsBlockComponent(browser);
        sidebar = new ClientSidebarComponent(browser);
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
            allNotificationsHaveReadButtonLocator.shouldNotBe(visible);
            notificationsCollection.shouldHave(size(0));
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загрузилась страница Уведомления", () -> {
            urlChecker.urlContains("/profile/notifications");
            pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));

        });
    }

    public void readAll() {
        stepWithRole("Прочитать все уведомления", () -> {
            stepWithRole("Нажать кнопку Прочитать все", () -> {
                readAllButtonLocator.click();
            });
            stepWithRole("Убедиться, что нет непрочитанных уведомлений", () -> {
                unreadNotificationsBadgeCollection.shouldHave(size(0));
            });
            stepWithRole("Убедиться, что присутствует неактивная кнопка Все уведомления прочитаны", () -> {
                allNotificationsHaveReadButtonLocator.shouldBe(visible);
            });
        });
    }

    public void openNotificationByTitle(String orderNumber) {
        stepWithRole("Открыть уведомление с заголовком: " + orderNumber, () -> {
            notificationsCollection.findBy(partialText(orderNumber)).click();
        });
    }

    public void checkFirstNotificationText(String notification) {
        stepWithRole("Убедиться, что первое уведомление содержит текст: " + notification, () -> {
            notificationsCollection.first().$(".item-flex .w-100.text-left").shouldHave(partialText(notification));
        });
    }

    public void checkExpectedAmountOfNotifications(int amount) {
        stepWithRole("Убедиться, что количество уведомлений равно: " + amount, () -> {
            notificationsCollection.shouldHave(size(amount));
        });
    }


    public void checkExpectedAmountOfNotifications(int amount, int ifWaitMilliseconds) {
        stepWithRole("Убедиться, что количество уведомлений равно: " + amount, () -> {
            if (notificationsCollection.size() != amount) {
                stepWithRole(" Ждем " + ifWaitMilliseconds + " мс", () -> {
                    System.out.println("waitNotifications: " + ifWaitMilliseconds + " ms");
                    Selenide.sleep(ifWaitMilliseconds);
                    open();// Pause execution for the specified number of milliseconds
                });
            }
            notificationsCollection.shouldHave(size(amount));
        });
    }

    public void open() {
        stepWithRole("Открыть страницу Уведомления", () -> {
            Selenide.sleep(3000);
            driver.open("/profile/notifications");
        });
    }

    public void checkStateConsultation(StateConsultation stateConsultation, NotificationsResponseDto dto) {
        stepWithRole("Проверить уведомления в состоянии " + stateConsultation, () -> {
            switch (stateConsultation) {
                case DRAFT_ONLINE_MASTERS:
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                case MASTER_COMPLETE_CONSULTATION:
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    checkExpectedAmountOfNotifications(0, 4000);
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }


        });
    }


    public void checkStateRepair(StateRepair stateRepair, NotificationsResponseDto dto) {
        stepWithRole("Проверить уведомления в состоянии " + stateRepair, () -> {
            Selenide.sleep(3000);
            String firstNotificationText = dto.getData().get(0).getText();
            switch (stateRepair) {
                case PUBLISHED:
                    checkExpectedAmountOfNotifications(1, 4000);
                    checkFirstNotificationText(stateRepair.notification());
                    assertThat(firstNotificationText, endsWith(stateRepair.notification()));
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                    checkExpectedAmountOfNotifications(1, 4000);
                    checkFirstNotificationText(PUBLISHED.notification());
                    assertThat(firstNotificationText, endsWith(PUBLISHED.notification()));
                    break;
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                case HAS_OFFER:
                    checkExpectedAmountOfNotifications(2, 4000);
                    checkFirstNotificationText(stateRepair.notification());
                    assertThat(firstNotificationText, endsWith(stateRepair.notification()));
                    break;
                case SCHEDULE_DATE:
                    checkExpectedAmountOfNotifications(3, 4000);
                    checkFirstNotificationText(stateRepair.notification());
                    assertThat(firstNotificationText, startsWith(stateRepair.notification()));
                    break;
                case WAIT_MASTER:
                    checkExpectedAmountOfNotifications(4, 4000);
                    checkFirstNotificationText(stateRepair.notification());
                    assertThat(firstNotificationText, startsWith(stateRepair.notification()));
                    break;
                case MASTER_START_WORK:
                    checkExpectedAmountOfNotifications(4, 4000);
                    break;
                case MATERIAL_INVOICE_ISSUED:
                    checkExpectedAmountOfNotifications(5, 4000);
                    assertThat(firstNotificationText, startsWith(stateRepair.notification()));
                    break;
                case MATERIAL_INVOICE_PAID:
                    checkExpectedAmountOfNotifications(5, 4000);
                    break;
                case ACTIONS_INVOICE_ISSUED:
                    checkExpectedAmountOfNotifications(6, 4000);
                    assertThat(firstNotificationText, startsWith(stateRepair.notification()));
                    break;
                case ACTIONS_INVOICE_PAID:
                    checkExpectedAmountOfNotifications(6, 4000);
                    break;
                case MASTER_SIGN_ACT:
                    checkExpectedAmountOfNotifications(7, 4000);
                    assertThat(firstNotificationText, startsWith(stateRepair.notification()));
                    break;
                case CLIENT_SIGN_ACT:
                    checkExpectedAmountOfNotifications(9, 4000);
                    assertThat(firstNotificationText, either(startsWith("Акт выполненных работ сформирован")).or(startsWith("Оставьте отзыв по заявке")));
//                    assertThat(secondNotificationText, startsWith("Акт выполненных работ сформирован"));
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });

    }
}
