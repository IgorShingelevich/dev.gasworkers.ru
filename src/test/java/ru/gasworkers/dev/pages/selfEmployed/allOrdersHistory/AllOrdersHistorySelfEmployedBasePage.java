package ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MosOblGasBannerSelfEmployedSideBarComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.ViewSwitcherOrderHistorySelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.PaginationComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.DispatcherModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.MasterModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.selfEmployed.BaseSelfEmployedPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;

public abstract class AllOrdersHistorySelfEmployedBasePage extends BaseSelfEmployedPage {
    public final ViewSwitcherOrderHistorySelfEmployedComponent viewSwitcher;
    public final HeaderSelfEmployedComponent header;
    public final ModeSwitcherSelfEmployedComponent modeSwitcher;
    public final DispatcherModeSelfEmployedSidebarComponent sidebarDispatcher;
    public final MasterModeSelfEmployedSidebarComponent sidebarMaster;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final MosOblGasBannerSelfEmployedSideBarComponent mosOblGasBanner;
    public final PaginationComponent pagination;

    public AllOrdersHistorySelfEmployedBasePage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        modeSwitcher = new ModeSwitcherSelfEmployedComponent(browser);
        viewSwitcher = new ViewSwitcherOrderHistorySelfEmployedComponent(browser);
        sidebarDispatcher = new DispatcherModeSelfEmployedSidebarComponent(browser);
        sidebarMaster = new MasterModeSelfEmployedSidebarComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        mosOblGasBanner = new MosOblGasBannerSelfEmployedSideBarComponent(browser);
        pagination = new PaginationComponent(browser);
    }

    private final String
            noOrdersText = "Документов нет";

    SelenideElement
            titleLocator = driver.$("div h1").as("Заголовок страницы"),
            noOrdersLocator = driver.$("div.page-content .mt-4 ").as("Документов нет");

    ElementsCollection
            orderCardCollection = driver.$$("div.order-card").as("Коллекция картчки заказа");

    public void checkEmptyState() {
        stepWithRole("Убедиться, что на странице нет истории заказов ", () -> {
            orderCardCollection.shouldHave(size(0));
            noOrdersLocator.shouldHave(text(noOrdersText));
            pagination.checkNotVisible();
        });
    }


}
