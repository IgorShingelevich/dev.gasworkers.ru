package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MosOblGasBannerSelfEmployedSideBarComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent.AddCertificateAndEquipmentSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent.ListCertificateAndEquipmentSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent.SearchCertificateAndEquipmentSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.SearchComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent.SelectCertificateAndEquipmentSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PaginationComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.DispatcherModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.MasterModeSelfEmployedSidebarComponent;

import static com.codeborne.selenide.Condition.text;

public class CertificatesAndEquipmentSelfEmployedPage extends BaseSelfEmployedPage {
    public final HeaderSelfEmployedComponent header;
    public final DispatcherModeSelfEmployedSidebarComponent sidebarDispatcher;
    public final MasterModeSelfEmployedSidebarComponent sidebarMaster;
    public final MosOblGasBannerSelfEmployedSideBarComponent mosOblGasBanner;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final SearchCertificateAndEquipmentSelfEmployedComponent searchEquipment;
    public final SelectCertificateAndEquipmentSelfEmployedComponent selectEquipment;
    public final AddCertificateAndEquipmentSelfEmployedComponent addEquipment;
    public final ListCertificateAndEquipmentSelfEmployedComponent listEquipment;
    public final PaginationComponent pagination;

    public CertificatesAndEquipmentSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        sidebarDispatcher = new DispatcherModeSelfEmployedSidebarComponent(browser);
        sidebarMaster = new MasterModeSelfEmployedSidebarComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        mosOblGasBanner = new MosOblGasBannerSelfEmployedSideBarComponent(browser);
        searchEquipment = new SearchCertificateAndEquipmentSelfEmployedComponent(browser);
        selectEquipment = new SelectCertificateAndEquipmentSelfEmployedComponent(browser);
        addEquipment = new AddCertificateAndEquipmentSelfEmployedComponent(browser);
        listEquipment = new ListCertificateAndEquipmentSelfEmployedComponent(browser);
        pagination = new PaginationComponent(browser);
    }

    private final String
            titleText = "Сертификаты и оборудование",
            infoBannerText = "Для получения заказов на ТО и ремонт укажите марку и модель котла, с которым Вы работаете. Прикрепите сертификат о прохождении обучения к указанному оборудованию.",
    searchEquipmentText = "Введите модель оборудования";

    SelenideElement
            titleLocator = driver.$("h1").as("Заголовок"),
            infoBannerLocator = driver.$("div").as("Информационный баннер");

    public void checkInitialState() {
        stepWithRole("Проверка начального состояния страницы", () -> {
            titleLocator.shouldHave(text(titleText));
            infoBannerLocator.shouldHave(text(infoBannerText));
            searchEquipment.checkInitialState();
            selectEquipment.checkInitialState();
            addEquipment.checkCollapsedState();
            listEquipment.checkInitialState();
            pagination.checkInitialState();
        });
    }
}
