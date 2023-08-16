package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;

import static com.codeborne.selenide.Condition.text;

public class InfoMasterTabOrderCardClientComponent extends BaseOrderCardComponent {
    public final StatusOrderCardPageComponent orderState;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final TableInfoMasterTabComponent table;
    SelenideElement
            conferenceResumeTextLocator = driver.$("div.item.hr.mt-3 span").as("Текст резюме конференции"),
            conferenceVideoLocator = driver.$("div.wrapper video").as("Видео конференции");

    public InfoMasterTabOrderCardClientComponent(RoleBrowser browser) {
        super(browser);
        orderState = new StatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        table = new TableInfoMasterTabComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.checkCurrentStatus(orderStatus);
    }

    public void checkResumeConference(String resume) {
        stepWithRole("Проверить, что в конференции есть сообщение: " + resume, () -> {
            conferenceResumeTextLocator.shouldHave(text(resume));
        });
    }
}
