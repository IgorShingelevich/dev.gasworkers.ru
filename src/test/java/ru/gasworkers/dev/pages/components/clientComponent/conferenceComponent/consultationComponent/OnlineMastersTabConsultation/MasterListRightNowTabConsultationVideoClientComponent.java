package ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent.consultationComponent.OnlineMastersTabConsultation;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

public class MasterListRightNowTabConsultationVideoClientComponent extends BaseComponent {
    public MasterListRightNowTabConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
        masterBoxCollection = driver.$$("div.col-xl-6.mb-4").as("Коллекция мастеров вкладки Консультация прямо сейчас");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что список мастеров вкладки Консультация прямо сейчас загрузился", () -> {
            masterBoxCollection.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
        });
    }
    public void checkDefaultState() {
        stepWithRole("Убедиться, что список мастеров вкладки Консультация прямо сейчас находится в состоянии по умолчанию", () -> {
            masterBoxCollection.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
            //todo add default check
        });
    }

    public void selectMasterByIndex(int index) {
        stepWithRole("Выбрать мастера с индексом " + index, () -> {
            masterBoxCollection.get(index).find("button.btn-primary").click();
        });
    }

    public void selectMasterByName(String fullName) {
        stepWithRole("Нажать кнопку Выбрать у мастера: " +  masterBoxCollection.findBy(Condition.text(fullName)).$("p.h5").getText(), () -> {
            masterBoxCollection.findBy(Condition.text(fullName)).find("button.btn-primary").click();
        });
    }

    public String getCurrentMasterByName(String fullName) {
        return masterBoxCollection.findBy(Condition.text(fullName)).$("p.h5").getText();
    }

    public String getCurrentMasterByIndex(int index) {
        return masterBoxCollection.get(index).$("p.h5").getText();
    }

    public String getMasterPriceByIndex (int index) {
           return masterBoxCollection.get(index).find("span.bag-success").getText();
    }


}
