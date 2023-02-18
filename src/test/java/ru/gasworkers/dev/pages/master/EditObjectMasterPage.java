package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents.EditDistributorMasterComponent;
import ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents.EditDistributorModalMasterComponent;
import ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents.EditObjectMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class EditObjectMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;
    public final EditDistributorMasterComponent editDistributorTab;
    public final EditDistributorModalMasterComponent distributorModal;
    public final EditObjectMasterComponent editObjectTab;

    public EditObjectMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        editDistributorTab = new EditDistributorMasterComponent(browser);
        distributorModal = new EditDistributorModalMasterComponent(browser);
        editObjectTab = new EditObjectMasterComponent(browser);
    }

    private final String
        EDIT_OBJECT_TITLE = "Объекты и оборудование";
    SelenideElement
        pageTitleLocator = driver.$("div h1.h3.mb-2").as("Заголовок страницы Редактирование объекта"),
        toOrderButtonLocator = driver.$(byTagAndText("span", "К заказу")).as("Кнопка К заказу");

    ElementsCollection
        navCollection = driver.$$("a.nav-primary__link").as("Навигация");

    public void checkFinishLoading() {
        String urlEditObjectMasterPage = driver.url();
        stepWithRole("Убедиться, что страница Редактирование объекта загружена", () -> {
            pageTitleLocator.shouldHave(text(EDIT_OBJECT_TITLE)).shouldBe(visible);
            navCollection.get(0).shouldHave(text("Объект"));
            navCollection.get(1).shouldHave(text("Филиал газораспределительной компании"));
            editObjectTab.checkFinishLoading();
            toOrderButtonLocator.shouldBe(visible);
        });
    }

    public void navObject() {
        stepWithRole("Нажать на ссылку Объект", () -> {
            navCollection.get(0).click();
        });
    }

    public void navGasBranch() {
        stepWithRole("Нажать на ссылку  Филиал газораспределительной компании", () -> {
            navCollection.get(1).click();
        });
    }
}
//TODO add equipment, edit Object(name, photo and address) - components ModalWindows