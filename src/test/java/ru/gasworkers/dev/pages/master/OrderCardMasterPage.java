package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCheckListTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;

public class OrderCardMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;
    public final NavCommonTabOrderCardComponent commonTab;
    public final NavCheckListTabOrderCardComponent tabCheckList;
    public final NavInfoMasterTabOrderCardComponent tabInfoMaster;
    public final NavDocsTabOrderCardComponent tabDocs;



    public  OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        commonTab = new NavCommonTabOrderCardComponent(browser);
        tabCheckList = new NavCheckListTabOrderCardComponent(browser);
        tabInfoMaster = new NavInfoMasterTabOrderCardComponent(browser);
        tabDocs = new NavDocsTabOrderCardComponent(browser);
    }

    private final String PAGE_TITLE = "??????????";

    ElementsCollection

        navButtonsCollection = driver.$$("div.navigation-block li").as("?????????????????????????? ????????????"),
        orderDetailsCollection = driver.$$("div.order-details-item").as("???????????????????? ?? ????????????"),
        docsTitleCollection = driver.$$("div .link-pdf ").as("???????????????? ????????????????????");


    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("?????????????????? ????????????????"),
        repairFromMaintenanceButtonLocator = driver.$(byTagAndText("span", "?????????? ???? ????????????")).as("?????????? ???? ???????????? ???? ????"),
        orderBlockLocator = driver.$(".page-content #order").as("???????? ????????????"),
        navDescriptionButtonLocator = navButtonsCollection.get(0).as("???????????????? ????????????"),
        navCheckListButtonLocator = navButtonsCollection.get(1).as("?????? ????????"),
        navInfoButtonLocator = navButtonsCollection.get(2).as("???????????????????? ???? ??????????????"),
        navDocumentsButtonLocator = navButtonsCollection.get(3).as("??????????????????"), mainButtonLocator = driver.$("button.btn.btn-primary").as("???????????????? ????????????"),
        saveCheckListButtonLocator = mainButtonLocator.$(byTagAndText("span", "??????????????????")).as("??????????????????"),
        editObjectButtonLocator = driver.$(byTagAndText("span", "?????????????????????????? ????????????/????????????????????????")).as("?????????????????????????? ????????????/????????????????????????"),
        startWorkingButtonLocator = driver.$(byTagAndText("span", "???????????????????? ?? ????????????")).as("???????????????????? ?? ????????????"),
        saveButtonLocator = driver.$(byTagAndText("span", "??????????????????")).as("??????????????????");

    public void checkFinishLoading() {
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("??????????????????, ?????? ???????????????? ????????????: " + orderCardNumber + " ??????????????????", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("master orderCardNumber: " + orderCardNumber);
        });
    }

    public void navCommon(){
     stepWithRole("?????????????? ???? ?????????????? ???????????????? ????????????", () -> {
        navButtonsCollection.get(0).shouldHave(text("???????????????? ????????????")).click();
    });
    }

    public void navCheckList(){
        stepWithRole("?????????????? ???? ?????????????? ?????? ????????", () -> {
            navButtonsCollection.get(1).shouldHave(text("?????? ????????")).click();
        });
    }

    public void navInfoMaster(){
        stepWithRole("?????????????? ???? ?????????????? ???????????????????? ???? ??????????????", () -> {
            navButtonsCollection.get(2).shouldHave(text("???????????????????? ???? ??????????????")).click();
        });
    }
    public void navDocs(){
        stepWithRole("?????????????? ???? ?????????????? ??????????????????", () -> {
            navButtonsCollection.get(3).shouldHave(text("??????????????????")).click();
        });
    }

    public void checkMasterDispatchedOrderState(OrderStatus orderStatus, OrderType orderType) {
        //TODO check current nav tab is navCommon
        stepWithRole("??????????????????, ?????? ???????????? ???????????? ?????????????????????????? ?????? ?????????????????? ", () -> {
            stepWithRole("?????????????? ???????????????? ????????????", () -> {
                commonTab.fillUpBanner.checkBannerDetails();
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("??????????????????, ??????  ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ???????????????????? ?? ???????????? ?? ???????????? ?????????? ???? ????????????", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("?????????????? ?????? ????????", () -> {
                commonTab.fillUpBanner.clickOnCheckListLink();
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isDisableRadioButtonsState();
                stepWithRole("??????????????????, ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ???????????? ?????????????????? ???????????????????? ?? ???????????? ?????????? ???? ????????????", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(disabled);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("?????????????? ???????????????????? ???? ??????????????", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("??????????????????, ??????  ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ???????????????????? ?? ???????????? ?? ???????????? ?????????? ???? ????????????", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("?????????????? ??????????????????", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("??????????????????, ??????  ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ???????????????????? ?? ???????????? ?? ???????????? ?????????? ???? ????????????", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
                stepWithRole("?????????????? ??????????????????: ?????????????? ???? ?? ?????????????????? ?????????? " , () -> {
                    tabDocs.downloadAgreement();
                    tabDocs.downloadInsurance();
                });
            });
            navCommon();
        });
    }

    public void startWork (){
        stepWithRole("???????????? ???????????? ???????????????????? ?? ????????????", () -> {
            startWorkingButtonLocator.shouldBe(visible).click();
        });
    }

    public void checkFillingCheckListState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("??????????????????, ?????? ???????????? ???????????? ?????????????????????????? ?????? ?????????????????? ", () -> {
            stepWithRole("?????????????????? ?????? ?????????????? ?????????????? ??????????????", () -> {
                tabCheckList.checkFinishLoading(orderStatus);
            });
            stepWithRole("?????????????????? ?????? ???? ???????? ???????????????? ???????????????? ???????????? ?????????????????????? ???????????? ?????????????????? ??????-????????", () -> {
                tabCheckList.fillUpBanner.checkBannerIsNotPresent();
                navCommon();
                commonTab.fillUpBanner.checkBannerIsNotPresent();
                navInfoMaster();
                tabInfoMaster.fillUpBanner.checkBannerIsNotPresent();
                navDocs();
                tabDocs.fillUpBanner.checkBannerIsNotPresent();
            });
            stepWithRole("?????????????? ???????????????? ????????????", () -> {
                navCommon();
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("??????????????????, ??????  ???????????????????????? ???????????? ???????????????????? ?? ????????????, ???????????? ?????????? ???? ????????????", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);

                });
            });
            stepWithRole("?????????????? ?????? ????????", () -> {
                navCheckList();
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isEnableRadioButtonsState();
                stepWithRole("??????????????????, ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ???????????? ?????????????????? ???????????????? ?? ???????????? ?????????? ???? ????????????", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(enabled);
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("?????????????? ???????????????????? ???? ??????????????", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("??????????????????, ??????  ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ?????????? ???? ????????????", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("?????????????? ??????????????????", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("??????????????????, ??????  ???????????????????????? ????????????  ?????????????????????????? ????????????/????????????????????????, ???????????? ?????????? ???? ????????????", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
    });
            navCommon();
        });
    }

    public void editObject (){
        stepWithRole("???????????? ???????????? ?????????????????????????? ????????????/????????????????????????", () -> {
            editObjectButtonLocator.shouldBe(visible).click();
        });
    }

    public void saveCheckList (){
        stepWithRole("???????????? ???????????? ??????????????????", () -> {
            saveButtonLocator.shouldBe(visible).click();
        });
    }


}