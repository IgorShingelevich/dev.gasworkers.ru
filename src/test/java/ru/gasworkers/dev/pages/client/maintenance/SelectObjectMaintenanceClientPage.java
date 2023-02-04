package ru.gasworkers.dev.pages.client.maintenance;

import com.codeborne.selenide.CollectionCondition;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

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
