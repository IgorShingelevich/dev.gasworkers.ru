package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.Selenide;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MosOblGasBannerSelfEmployedSideBarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.*;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon.NavCommonTabSelfEmployedProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile.NavProfileTabSelfEmployedProfilePageComponent;

public class ProfileSelfEmployedPage  extends BaseSelfEmployedPage{
    public final HeaderSelfEmployedComponent header;
    public final SelfEmployedSidebarComponent sidebar;
    public final  ModeSwitcherSelfEmployedComponent modeSwitcher;
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
            sidebar = new SelfEmployedSidebarComponent(browser);
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
            tabCommon.checkInitialState();
//            header.checkInitialState();
//            sidebar.checkInitialState();
//            modeSwitcher.checkInitialState();
//            mosOblGasBanner.checkInitialState();
//            fillProfileBanner.checkInitialState();
            nav.profile();
            tabProfile.checkFirsOfferEvaluatedInitialState();
            nav.contacts();
            tabContacts.checkFirsOfferEvaluatedInitialState(email, phone);
//             tabEquipment.checkInitialState();
            nav.password();
            tabPassword.checkFirsOfferEvaluatedInitialState();
            nav.notifications();
            tabNotifications.checkFirsOfferEvaluatedInitialState();
            nav.profile();
        });
    }

    public void checkFirsOfferEvaluatedInitialState(String email, String phone) {
        stepWithRole("Убедиться, что страница профиля в начальном состоянии", () -> {
            tabCommon.checkFirsOfferEvaluatedInitialState();
//            header.checkInitialState();
//            sidebar.checkInitialState();
//            modeSwitcher.checkInitialState();
//            mosOblGasBanner.checkInitialState();
//            fillProfileBanner.checkInitialState();
            nav.profile();
            tabProfile.checkFirsOfferEvaluatedInitialState();
            nav.contacts();
            tabContacts.checkFirsOfferEvaluatedInitialState(email, phone);
//             tabEquipment.checkInitialState();
            nav.password();
            tabPassword.checkFirsOfferEvaluatedInitialState();
            nav.notifications();
            tabNotifications.checkFirsOfferEvaluatedInitialState();
            stepWithRole("Убедиться, что паннель анимация паннели навигации срабатывает", () -> {
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

    public void checkNoError(){
        stepWithRole("Убедиться, что нет ошибок", () -> {
//            tabProfile.checkNoError();
//            tabContacts.checkNoError();
//            tabPassword.checkNoError();
//            tabNotifications.checkNoError();
        });
    }
}
