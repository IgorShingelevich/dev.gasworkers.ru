package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;

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
            Selenide.sleep(2000);
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
            driver.open("/profile/notifications");
        });
    }



    public void checkState(StateRepair stateRepair, NotificationsResponseDto dto) {
        stepWithRole("Проверить уведомления в состоянии " + stateRepair, () -> {
            String firstNotificationText = dto.getData().get(0).getText();
            switch (stateRepair) {
                case PUBLISHED:
                    checkExpectedAmountOfNotifications(1, 4000);
                    checkFirstNotificationText(stateRepair.notification());
                    assertThat(firstNotificationText, endsWith(stateRepair.notification()));
                    break;
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
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });

    }
}
