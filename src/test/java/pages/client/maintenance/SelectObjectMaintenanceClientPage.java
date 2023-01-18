package pages.client.maintenance;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

public final class SelectObjectMaintenanceClientPage extends BaseClientPage {

    private final FocusHeaderComponent focusHeaderComponent;

    public SelectObjectMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        focusHeaderComponent = new FocusHeaderComponent(browser);
    }

//    @Step("Выбрать объект по индексу {index}")
    public void selectObjectByIndex(int index) {
       String objectName = driver.$$("p.card-object__name ").get(index).getText(); //how to use globally?
        Integer reportCount = index + 1;
        stepWithRole("Выбрать: " + reportCount  + " объект по счету: "  + objectName , () -> {
            driver.$$(".card-object button")
                    .shouldHave(CollectionCondition.sizeGreaterThan(index))
                    //difference between .sizeGreaterThan and .sizeGreaterThanOrEqual. Why not to use .get(index) only?
                    .get(index).click();
            System.out.println("Select Object index: "+ index + " name: " + objectName);
        });

    }

}
