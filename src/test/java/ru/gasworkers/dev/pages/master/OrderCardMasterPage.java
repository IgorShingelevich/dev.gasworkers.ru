package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.StatusBoxOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.CheckListTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.InfoMasterTabOrderCardMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.MasterSidebarComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardMasterPage extends BaseMasterPage {

    public final MasterSidebarComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;
    public final StatusBoxOrderCardComponent statusBox;
    public final CommonTabOrderCardComponent tabCommon;
    public final CheckListTabOrderCardComponent tabCheckList;
    public final InfoMasterTabOrderCardMasterComponent tabInfoMaster;
    public final DocsTabOrderCardComponent tabDocs;
    private final String PAGE_TITLE = "Заказ";
    ElementsCollection

            orderDetailsCollection = driver.$$("div.order-details-item").as("Информация о заказе"),
            docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов");
    SelenideElement
            navCommonTabButtonLocator = driver.$("li[data-name='common']").as("Вкладка Описание заказа"),
            navInfoMasterTabButtonLocator = driver.$("li[data-name='master']").as("Вкладка Информация о мастере"),
            navChecklistTabButtonLocator = driver.$("li[data-name='checklist']").as("Вкладка Чек-лист"),
            navDocsTabButtonLocator = driver.$("li[data-name='documents']").as("Вкладка Документы"),
            titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
            repairFromMaintenanceButtonLocator = driver.$(byTagAndText("span", "Заказ на ремонт")).as("Заказ на ремонт из ТО"),
            orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
            mainButtonLocator = driver.$("button.btn.btn-primary").as("Основная кнопка"),
            saveCheckListButtonLocator = mainButtonLocator.$(byTagAndText("span", "Сохранить")).as("Сохранить"),
            editObjectButtonLocator = driver.$(byTagAndText("span", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
            startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе"),
            conferenceResumeTextLocator = driver.$("div.item.hr.mt-3 span").as("Текст резюме конференции"),
            conferenceVideoLocator = driver.$("div.wrapper video").as("Видео конференции"),
            saveButtonLocator = driver.$(byTagAndText("span", "Сохранить")).as("Сохранить");


    public OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new MasterSidebarComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        statusBox = new StatusBoxOrderCardComponent(browser);
        tabCommon = new CommonTabOrderCardComponent(browser);
        tabCheckList = new CheckListTabOrderCardComponent(browser);
        tabInfoMaster = new InfoMasterTabOrderCardMasterComponent(browser);
        tabDocs = new DocsTabOrderCardComponent(browser);
    }

    public void checkFinishLoading() {
        checkUrl();
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("master orderCardNumber: " + orderCardNumber);
        });
    }

    public void navCommon() {
        stepWithRole("Перейти на вкладку Описание заказа", () -> {
            navCommonTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Описание заказа", () -> {
                navCommonTabButtonLocator.shouldHave(cssClass("active")); // TODO uncomment after fix
            });
        });
    }

    public void navCheckList() {
        stepWithRole("Перейти на вкладку Чек лист", () -> {
            navChecklistTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Чек лист", () -> {
                navChecklistTabButtonLocator.shouldHave(cssClass("active")); // TODO uncomment after fix
            });
        });
    }

    public void navInfoMaster() {
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navInfoMasterTabButtonLocator.click();
            driver.$("li[data-name='master']").click();
            stepWithRole("Убедиться, что открылась вкладка Информация по работам", () -> {
                navInfoMasterTabButtonLocator.shouldHave(cssClass("active")); // TODO uncomment after fix
            });
        });
    }

    public void navDocs() {
        stepWithRole("Перейти на вкладку Документы", () -> {
            navDocsTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Документы", () -> {
                navDocsTabButtonLocator.shouldHave(cssClass("active")); // TODO uncomment after fix
            });
        });
    }

    public void checkMasterDispatchedOrderState(OrderStatus orderStatus, ServiceType serviceType) {
        //TODO check current nav tab is navCommon
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                tabCommon.fillUpBanner.checkBannerDetails();
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Чек лист", () -> {
                tabCommon.fillUpBanner.clickOnCheckListLink();
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isDisableRadioButtonsState();
                stepWithRole("Убедиться, представлена кнопки  Редактировать объект/оборудование, кнопка Кнопка Сохранить неактивная и кнопка Заказ на ремонт", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(disabled);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис ", () -> {
                    tabDocs.downloadAgreement();
                    tabDocs.downloadInsurance();
                });
            });
            navCommon();
        });
    }

    public void startWork() {
        stepWithRole("Нажать кнопку Приступить к работе", () -> {
            startWorkingButtonLocator.shouldBe(visible).click();
        });
    }

    public void checkFillingCheckListState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться что открыта вкладка Чеклист", () -> {
                tabCheckList.checkFinishLoading(orderStatus);
            });
            stepWithRole("Убедиться что на всех вкладках карточки заказа отсутствует баннер Заполните чек-лист", () -> {
                tabCheckList.fillUpBanner.checkBannerIsNotPresent();
                navCommon();
                tabCommon.fillUpBanner.checkBannerIsNotPresent();
                navInfoMaster();
                tabInfoMaster.fillUpBanner.checkBannerIsNotPresent();
                navDocs();
                tabDocs.fillUpBanner.checkBannerIsNotPresent();
            });
            stepWithRole("Вкладка Описание заказа", () -> {
                navCommon();
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
                stepWithRole("Убедиться, что  представлены кнопки Приступить к работе, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);

                });
            });
            stepWithRole("Вкладка Чек лист", () -> {
                navCheckList();
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isEnableRadioButtonsState();
                stepWithRole("Убедиться, представлена кнопки  Редактировать объект/оборудование, кнопка Кнопка Сохранить активная и кнопка Заказ на ремонт", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(enabled);
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
            });
            navCommon();
        });
    }

    public void editObject() {
        stepWithRole("Нажать кнопку Редактировать объект/оборудование", () -> {
            editObjectButtonLocator.shouldBe(visible).click();
        });
    }

    public void saveCheckList() {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            saveButtonLocator.shouldBe(visible).click();
        });
    }


    public void checkUrl() {
        urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/master/orders/");
    }

    public void checkFinishConference(String resume) {
        stepWithRole("Проверить, что в конференции есть сообщение: " + resume, () -> {
            conferenceResumeTextLocator.shouldHave(text(resume));
            conferenceVideoLocator.shouldBe(visible);
        });
    }
}