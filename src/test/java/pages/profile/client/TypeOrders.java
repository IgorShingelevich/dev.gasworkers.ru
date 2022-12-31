package pages.profile.client;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TypeOrders {

    /**<div data-v-1b158f83="" class="text-wrap"><p data-v-1b158f83="" class="h3">Выберите тип работы</p> <p data-v-1b158f83="" class="h4">Для создания заказа, выберите тип работы</p> <button data-v-6d08f792="" data-v-1b158f83="" class="btn btn-primary disable-outline">
    Заключение договора на ТО
</button> <button data-v-6d08f792="" data-v-1b158f83="" class="btn btn-primary disable-outline">
    Ремонт
            </button> <button data-v-6d08f792="" data-v-1b158f83="" class="btn btn-primary disable-outline">
    Видеоконсультация
            </button> <!----></div>*/

    private final String TYPE_ORDERS_TITLE = "Выберите тип работы";


    SelenideElement typeOrdersTitleLocator = $x("//div[@class='text-wrap']//p[@class='h3']"),
        maintenanceContractLocator =  $$(".btn btn-primary disable-outline").findBy(text("Заключение договора на ТО")),
        repairContractLocator =  $$(".btn btn-primary disable-outline").findBy(text("Ремонт")),
        videoContractLocator =  $$(".btn btn-primary disable-outline").findBy(text("Видеоконсультация"));
















 public TypeOrders isOpened() {
        typeOrdersTitleLocator.shouldBe(visible).shouldHave(text(TYPE_ORDERS_TITLE));
        return this;
    }

    public TypeOrders clickMaintenanceContract() {
        maintenanceContractLocator.shouldHave(text("Заключение договора на ТО")).click();
        return this;
    }

    public TypeOrders clickRepairContract() {
        repairContractLocator.shouldHave(text("Ремонт")).click();
        return this;
    }

    public TypeOrders clickVideoContract() {
        videoContractLocator.shouldHave(text("Видеоконсультация")).click();
        return this;
    }











}
