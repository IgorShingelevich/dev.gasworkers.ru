package ru.gasworkers.dev.pages.components.dispatcherComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent.MainButtonSharedComponent;

import static com.codeborne.selenide.Condition.visible;

public class OfferPriceModalDispatcherComponent extends BaseComponent {
    public final MainButtonSharedComponent mainButton;
    public final MosOblGasBannerOfferPriceModalDispatcherComponent mosOblGasBanner;
    public OfferPriceModalDispatcherComponent(RoleBrowser browser) {
        super(browser);
        mainButton = new MainButtonSharedComponent(browser);
        mosOblGasBanner = new MosOblGasBannerOfferPriceModalDispatcherComponent(browser);
    }

    SelenideElement
    selfLocator = driver.$("div.modal-content-wrapper").as("Модальное окно расценка оборудования");

    public void checkFinishLoading(){
        stepWithRole("Убедиться, что модальное окно расценка оборудования загрузилось", () -> {
            selfLocator.shouldBe(visible);
            mosOblGasBanner.checkFinishLoading();
            mainButton.checkButtonText("Сохранить", selfLocator);
            //add second button check
            //add price calculation check
            //add toggle check
        });
    }

    public void saveButton(){
        stepWithRole("Нажать кнопку Сохранить", () -> {
            mainButton.clickActive("Сохранить",selfLocator);
        });
    }


}
// add savePrice toggle check