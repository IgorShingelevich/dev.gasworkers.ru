package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelectObjectRepairObjectPage {

    FocusHeaderComponent header = new FocusHeaderComponent();


    private final String
            SELECT_REPAIR_OBJECT_TITLE = "Создайте или выберите объект, укажите неисправное оборудование",
            OTHER_EQUIPMENT_PLACEHOLDER = "Укажите ваше оборудование",
            OTHER_EQUIPMENT_TEXT = "Газовая плита 4 комфорки, Неизвестная Марка";


    SelenideElement
            pageTitleLocator = $(".h3"),
            firstObjectFirstEquipmentCheckbox = $$x("(//div[@class='btn-wrap'][contains(.,'Выбрать')])").get(1).$$x("(//input[contains(@type,'checkbox')])").get(1),
            firstObjectSecondEquipmentCheckbox = $$x("(//div[@class='btn-wrap'][contains(.,'Выбрать')])").get(1).$$x("(//input[contains(@type,'checkbox')])").get(2),
//            firstObjectOtherEquipmentCheckbox = $$x("(//div[@class='btn-wrap'][contains(.,'Выбрать')])").get(1).$$x("(//input[contains(@type,'checkbox')])").get(3),  //hardcoded need .last
            firstObjectOtherEquipmentPlaceholder = $$x("(//div[@class='btn-wrap'][contains(.,'Выбрать')])").get(1).$$x("(//input[contains(@placeholder,'Укажите ваше оборудование')])").first(),
    //(//input[contains(@placeholder,'Укажите ваше оборудование')])[1]
            firstObjectButton = $$x("(//div[@class='btn-wrap'][contains(.,'Выбрать')])").get(0);
    public SelectObjectRepairObjectPage isOpened() {
        pageTitleLocator.shouldHave(text(SELECT_REPAIR_OBJECT_TITLE));
        return this;
    }

    public SelectObjectRepairObjectPage pick1thObject1thEquipment() {
        firstObjectFirstEquipmentCheckbox.click();
        firstObjectSecondEquipmentCheckbox.click();
//        firstObjectOtherEquipmentPlaceholder.should(appear).sendKeys(OTHER_EQUIPMENT_TEXT);
        firstObjectButton.shouldBe(enabled).click();

        return this;
    }



}
