package pages.client.maintenance;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.clientComponent.DatePickerOrderClientComponent;
import utils.RandomUtil;

import static com.codeborne.selenide.Condition.visible;

public final class SelectDateMaintenanceClientPage extends BaseClientPage {

    private final DatePickerOrderClientComponent datePicker;

    public SelectDateMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        datePicker = new DatePickerOrderClientComponent(browser);
    }

    private final String SELECT_DATE_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";

    SelenideElement titleLocator = driver.$(".page-content .text-center"),
            submitButtonLocator = driver.$(".w-100.btn.btn-primary.disable-outline"),
            swapObjectLinkLocator = driver.$x("//a[@href='#']");


    public SelectDateMaintenanceClientPage submitOrder() {
        stepWithRole(" Нажать на кнопку Разместить Заказ", () -> {
            submitButtonLocator.shouldBe(visible).click();
        });
        return this;
    }

    public SelectDateMaintenanceClientPage clickSelectDatePageChangeObjectButton() {
        swapObjectLinkLocator.shouldBe(visible).click();
        return this;
    }

    public SelectDateMaintenanceClientPage pickNowDateAM() {
        String nowDate = RandomUtil.getNowDate();
        stepWithRole("Выбрать текущую дату и время: " + nowDate, () -> {
            datePicker.setDate(nowDate);
            datePicker.setAMTime();
            System.out.println("Select date: " + nowDate);
        });

        return this;
    }


}





