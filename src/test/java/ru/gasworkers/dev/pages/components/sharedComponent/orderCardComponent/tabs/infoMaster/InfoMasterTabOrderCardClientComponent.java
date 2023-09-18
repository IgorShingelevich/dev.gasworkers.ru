package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.SharedButtonsOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.SuggestedMasterRepairCommonTabOrderCardComponent;

import static com.codeborne.selenide.Condition.text;

public class InfoMasterTabOrderCardClientComponent extends BaseOrderCardComponent {
    public final StatusOrderCardPageComponent status;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final ApprovedMasterCardInfoMasterTabOrderCardComponent approvedMasterCard;
    public final MaintenanceDetailsInfoMasterTabComponent maintenanceDetails;
    public final RepairDetailsInfoMasterTabOrderCardComponent repairDetails;
    public final SuggestedMasterRepairCommonTabOrderCardComponent suggestedMasterCardRepair;
    public final SharedButtonsOrderCardComponent buttons;

    //todo add buttons
    SelenideElement
            noInfoTextLocator = driver.$("div.text-center").as("Текст отсутствия информации"),
            conferenceResumeTextLocator = driver.$("div.item.hr.mt-3 span").as("Текст резюме конференции"),
            conferenceVideoLocator = driver.$("div.wrapper video").as("Видео конференции");

    public InfoMasterTabOrderCardClientComponent(RoleBrowser browser) {
        super(browser);
        status = new StatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        approvedMasterCard = new ApprovedMasterCardInfoMasterTabOrderCardComponent(browser);
        maintenanceDetails = new MaintenanceDetailsInfoMasterTabComponent(browser);
        repairDetails = new RepairDetailsInfoMasterTabOrderCardComponent(browser);
        suggestedMasterCardRepair = new SuggestedMasterRepairCommonTabOrderCardComponent(browser);
        buttons = new SharedButtonsOrderCardComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        status.checkCurrentStatus(orderStatus);
    }

    public void checkResumeConference(String resume) {
        stepWithRole("Убедиться, что в конференции есть сообщение: " + resume, () -> {
            conferenceResumeTextLocator.shouldHave(text(resume));
        });
    }

    public void checkNoInfoBox() {
        stepWithRole("Убедиться, что отсутствует информация", () -> {
            noInfoTextLocator.shouldHave(text("Информации пока нет."));
        });
    }


}
