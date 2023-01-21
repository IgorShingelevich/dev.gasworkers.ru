package pages.dispatcher;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import org.openqa.selenium.By;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectMasterDispatcherPage extends BaseDispatcherPage {

    private final SidebarDispatcherComponent sidebarDispatcher;

    public SelectMasterDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new SidebarDispatcherComponent(browser);
    }

    private final String
        PAGE_TITLE = "Найдите своего мастера прямо сейчас!";

    SelenideElement
        pageTitleLocator = driver.$("div h4"),
        selectedMasterButton = driver.$("button.btn[disabled='disabled']");


    ElementsCollection
        selectAvailableMasterButtonCollection = driver.$$("li.item button.btn:not([disabled='disabled'])");
    //.shouldHave(CollectionCondition.texts("Выбрать"));
        // $$(byTagAndText("button", "Выбрать"));

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Выбор мастера загружена", () -> {
            pageTitleLocator.shouldHave(text(PAGE_TITLE));
            selectAvailableMasterButtonCollection.as("Кнопка Выбрать").shouldBe(CollectionCondition.sizeGreaterThan(0));
            // is CollectionCondition.sizeGreaterThan(0)- good way to check gage is loaded?
        });
    }

    public void selectAvailableMasterByIndex(int availableMasterIndex) {
            String masterName = driver.$("li button[disabled='disabled']").closest("li").$(".name a").text();
        selectAvailableMasterButtonCollection.get(availableMasterIndex).click();
//                    .$$("button.btn:not([disabled='disabled'])")
//                    .ancestor("div .name")
            Integer masterNumericalOrder = availableMasterIndex + 1;
        stepWithRole("Выбрать:" + masterNumericalOrder + "-го свободного мастера: " + masterName, () -> {
            //how to warp up with  stepWith role the whole medhod with the description?
            System.out.println("selectedAvailableMasterByIndex: "+ availableMasterIndex + " name: " + masterName);

        });
    }
}
