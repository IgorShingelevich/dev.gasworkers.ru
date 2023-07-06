package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class NavInfoMasterTabOrderCardMasterPageComponent extends BaseTabOrderCardComponent {
    public final OrderStatusOrderCardPageComponent orderState;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final TableInfoMasterTabComponent table;
    SelenideElement
            conferenceResumeTextLocator = driver.$("div.item.hr.mt-3 span").as("Текст резюме конференции"),
            conferenceVideoLocator = driver.$("div.wrapper video").as("Видео конференции");

    public NavInfoMasterTabOrderCardMasterPageComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        table = new TableInfoMasterTabComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.currentStatus(orderStatus);
    }

    public void checkFinishConference(String resume) {
        stepWithRole("Проверить, что в конференции есть сообщение: " + resume, () -> {
            conferenceResumeTextLocator.shouldHave(text(resume));
            conferenceVideoLocator.shouldBe(visible);
        });
    }
}
