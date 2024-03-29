package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.Selenide;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MosOblGasBannerSelfEmployedSideBarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.*;
import ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navCommon.NavCommonTabSelfEmployedProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navProfile.NavProfileTabSelfEmployedProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.DispatcherModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.MasterModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

public class ProfileSelfEmployedPage extends BaseSelfEmployedPage {
    public final HeaderSelfEmployedComponent header;
    public final DispatcherModeSelfEmployedSidebarComponent sidebarDispatcher;
    public final MasterModeSelfEmployedSidebarComponent sidebarMaster;
    public final ModeSwitcherSelfEmployedComponent modeSwitcher;
    public final MosOblGasBannerSelfEmployedSideBarComponent mosOblGasBanner;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final NavigationProfilePageComponent nav;
    public final NavProfileTabSelfEmployedProfilePageComponent tabProfile;
    public final NavCommonTabSelfEmployedProfilePageComponent tabCommon;
    public final NavContactsTabProfilePageComponent tabContacts;
    public final NavEquipmentTabProfilePageComponent tabEquipment;
    public final NavNotificationsTabProfilePageComponent tabNotifications;
    public final NavPasswordTabProfilePageComponent tabPassword;

    public ProfileSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        sidebarDispatcher = new DispatcherModeSelfEmployedSidebarComponent(browser);
        sidebarMaster = new MasterModeSelfEmployedSidebarComponent(browser);
        modeSwitcher = new ModeSwitcherSelfEmployedComponent(browser);
        mosOblGasBanner = new MosOblGasBannerSelfEmployedSideBarComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        nav = new NavigationProfilePageComponent(browser);
        tabProfile = new NavProfileTabSelfEmployedProfilePageComponent(browser);
        tabCommon = new NavCommonTabSelfEmployedProfilePageComponent(browser);
        tabContacts = new NavContactsTabProfilePageComponent(browser);
        tabEquipment = new NavEquipmentTabProfilePageComponent(browser);
        tabNotifications = new NavNotificationsTabProfilePageComponent(browser);
        tabPassword = new NavPasswordTabProfilePageComponent(browser);

    }


    public void checkInitialState(String email, String phone) {
        stepWithRole("Убедиться, что страница профиля в начальном состоянии", () -> {
            stepWithRole("Убедиться, что элементы страницы профиля в начальном состоянии", () -> {
                modeSwitcher.checkDispatcherMode();
                modeSwitcher.switchMaster();
                modeSwitcher.checkMasterMode();
                header.checkFinishLoading();
                sidebarMaster.checkFinishLoading();
                //todo add message icon
//                sidebar.checkFinishLoading();
                // todo sidebar unification
                //todo sidebar notifications block
                mosOblGasBanner.checkFinishLoading();
                fillProfileBanner.checkFinishLoading();
                modeSwitcher.switchDispatcher();
                modeSwitcher.checkDispatcherMode();
            });
            stepWithRole("Убедиться, что вкладки профиля в начальном состоянии", () -> {
                tabCommon.checkInitialState();
                nav.profile();
                tabProfile.checkInitialState();
                nav.contacts();
                tabContacts.checkInitialState(email, phone);
                nav.equipment();
                tabEquipment.checkInitialState();
                nav.password();
                tabPassword.checkInitialState();
                nav.notifications();
                tabNotifications.checkInitialState();
                nav.contacts();
                Selenide.sleep(100);
                nav.common();
                Selenide.sleep(100);
                nav.profile();
            });
        });
    }

    public void checkFirsOfferEvaluatedInitialState(String email, String phone) {
        stepWithRole("Убедиться, что страница профиля в состоянии после расценки первого заказа", () -> {
            stepWithRole("Убедиться, что элементы страницы профиля в состоянии после расценки первого заказа", () -> {
                header.checkFinishLoading();
//            modeSwitcher.checkDispatcherMode();
                //todo sidebar unification
//                sidebar.checkFinishLoading();
                //todo sidebar notifications block
                mosOblGasBanner.checkFinishLoading();
                fillProfileBanner.checkFinishLoading();
            });
            stepWithRole("Убедиться, что вкладки в состоянии после расценки первого заказа", () -> {
                tabCommon.checkFirsOfferEvaluatedInitialState();
                nav.profile();
                tabProfile.checkFirsOfferEvaluatedInitialState();
                nav.contacts();
                tabContacts.checkFirsOfferEvaluatedSEInitialState(email, phone);
                nav.equipment();
                tabEquipment.checkFirsOfferEvaluatedSEInitialState();
                nav.password();
                tabPassword.checkFirsOfferEvaluatedSEInitialState();
                nav.notifications();
                tabNotifications.checkFirsOfferEvaluatedSEInitialState();
            });
            stepWithRole("Убедиться, что  анимация паннели навигации срабатывает", () -> {
                nav.password();
                Selenide.sleep(100);
                nav.equipment();
                Selenide.sleep(100);
                nav.contacts();
                Selenide.sleep(100);
                nav.common();
                Selenide.sleep(100);
                //  todo animation handling
                nav.profile();
            });
        });
    }

    public void checkFilledProfileState() {
        stepWithRole("Убедиться, что нет ошибок", () -> {
            // todo se checkFilledProfileState
        });
    }

    public void fillCommonTabRandomData(RandomSelfEmployedAndMaster randomSelfEmployedAndMaster) {
        stepWithRole("Заполнить вкладку Общие данные", () -> {
            tabCommon.fillRandomData(randomSelfEmployedAndMaster);
        });

    }
}
//todo conditions modeSwitcher for all states
