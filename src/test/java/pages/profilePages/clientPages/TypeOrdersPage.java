package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TypeOrdersPage {



    private final String
        TYPE_ORDERS_TITLE = "Выберите тип работы";




    SelenideElement
        typeOrdersTitleLocator = $x("//div[@class='text-wrap']//p[@class='h3']"),
        maintenanceContractLocator =  $$(".btn.btn-primary.disable-outline").findBy(text("Заключение договора на ТО")),
        repairContractLocator =  $$(".btn.btn-primary.disable-outline").findBy(text("Ремонт")),
        videoContractLocator =  $$(".btn.btn-primary.disable-outline").findBy(text("Видеоконсультация"));




 public TypeOrdersPage isOpened() {
        typeOrdersTitleLocator.shouldBe(visible).shouldHave(text(TYPE_ORDERS_TITLE));
        return this;
    }

    public TypeOrdersPage Maintenance() {
        maintenanceContractLocator.shouldHave(text("Заключение договора на ТО")).click();
        return this;
    }

    public TypeOrdersPage Repair() {
        repairContractLocator.shouldHave(text("Ремонт")).click();
        return this;
    }

    public TypeOrdersPage Video() {
        videoContractLocator.shouldHave(text("Видеоконсультация")).click();
        return this;
    }











}
