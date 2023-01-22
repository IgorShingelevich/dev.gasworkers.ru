package pages.master.ordersPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;
import pages.master.BaseMasterPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class InProgressOrdersMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;

    public InProgressOrdersMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
    }

    ElementsCollection orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer");
    SelenideElement
            titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
            orderCardTitleLocator = driver.$(".page-title .h3.mb-2"),
            switchToListViewLocator = driver.$("div.action-btn.list-type"),
            switchToTabViewLocator = driver.$("div.action-btn.card-type");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Заказы принятые загружена", () -> {
            titleLocator.shouldHave(text("Список принятых заказов"));
            //greatherThan(0)
        });
    }

    public void switchToListView() {
        stepWithRole("Переключиться на вид списка", () -> {
            switchToListViewLocator.click();
        });
    }

    public void switchToTabView() {
        stepWithRole("Переключиться на вид карточек", () -> {
            switchToTabViewLocator.click();
        });
    }

    public void openOrderByNumber(Integer orderNumber) {
        stepWithRole("Открыть заказ по номеру: " + orderNumber, () -> {
            orderNumberLinkCollection.findBy(text(orderNumber.toString())).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber.toString()));
            System.out.println("givenOrderNumber: " + orderNumber);
        });
    }

    public void openOrderByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу: "+ index + ", номер: " + orderNumberLinkCollection.get(index).getText(), () -> {
            orderNumberLinkCollection.get(index).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("orderNumber:  " + orderCardTitleLocator.getText() + " index: " + index);
        });
    }


}
