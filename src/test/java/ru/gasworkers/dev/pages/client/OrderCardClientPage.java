package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.administration.totalPrice.TotalPriceResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.OffersCounterClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification.ConsultationNotificationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.NavigationOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.buttons.ClientButtonsOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardClientPage extends BaseClientPage {
    public final ClientSidebarComponent sidebar;
    public final ClientActionsBlockComponent actionsBlock;
    public final StepperComponent stepper;
    public final ConsultationNotificationSharedComponent consultationNotification;
    public final OffersCounterClientComponent offersCounter;
    public final NavigationOrderCardPageComponent nav;
    public final CommonTabOrderCardComponent commonTab;
    public final InfoMasterTabOrderCardClientComponent infoMasterTab;
    public final DocsTabOrderCardComponent docsTab;
    public final ClientButtonsOrderCardComponent buttons;
    private final String
            ORDER_CARD_TITLE = "Заказ №",
            COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию",
            SUBMIT_AGREEMENT_SUBTITLE = "Кнопка «передать договор» в газораспределительную компанию будет активна после размещения отзыва";
    SelenideElement
            titleCardNumberLocator = driver.$("h1.h3.mb-2").as(" Заголовок Карточки заказа"),
            completeOrderInfoBannerLocator = driver.$(".hint-box p").as("Баннер с информацией о завершении заказа"),
            submitAgreementSubtitleLocator = driver.$("div p.text-secondary").as("Пояснение кнопки Передать Договор"),
            orderDetailsBlockLocator = driver.$("div.order-details").as("Блок с информацией о заказе"),
            toMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")).as("Кнопка Показать на карте"),
            cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Кнопка Отменить заказ"),
            payBillButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")).as("Кнопка Оплатить счет"),
            signButtonLocator = driver.$(byTagAndText("span", "Подписать")).as("Кнопка Подписать"),
            submitAgreementButtonLocator = driver.$("div a.btn-link-custom").as("Кнопка Передать договор"),
            mainButtonLocator = driver.$("div button.btn.btn-primary").as("Главная кнопка"),
            submitReviewButtonLocator = mainButtonLocator.$(byTagAndText("span", "Оставить отзыв")).as("Кнопка Оставить отзыв");

    ElementsCollection
            orderDetailsCollection = driver.$$("div.order-details-item").as("Информация о заказе");
    // TODO upd buttons to implement mainButtonLocator and be able to check enabled/disabled state

    public OrderCardClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        nav = new NavigationOrderCardPageComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
        stepper = new StepperComponent(browser);
        consultationNotification = new ConsultationNotificationSharedComponent(browser);
        offersCounter = new OffersCounterClientComponent(browser);
        commonTab = new CommonTabOrderCardComponent(browser);
        infoMasterTab = new InfoMasterTabOrderCardClientComponent(browser);
        docsTab = new DocsTabOrderCardComponent(browser);
        buttons = new ClientButtonsOrderCardComponent(browser);
    }

    /*protected SelenideElement leftMenuItemTopology(String pointMenu) {
        return driver.$x("//*[local-name() = 'svg'][@data-testid = 'WarehouseIcon']//ancestor::li//span[text() = '" + pointMenu + "']");
    }*/

    public void checkFinishLoading() {
        checkUrl();
        titleCardNumberLocator.shouldBe(visible);
        String orderNumber = titleCardNumberLocator.getText().substring(ORDER_CARD_TITLE.length());
        stepWithRole("Убедиться, что Карточка Заказа: " + orderNumber + " загружена", () -> {
            titleCardNumberLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(ORDER_CARD_TITLE));
            orderDetailsBlockLocator.shouldBe(visible, Duration.ofSeconds(20));
        });
    }

    // open  with  ?view=card like https://dev.gasworkers.ru/profile/client/orders/11081?view=card
    public void openRedirected(Integer orderID, String token) {
        open("profile/client/orders/" + orderID + "?view=card", token);
    }

    // open  normally like https://dev.gasworkers.ru/profile/client/orders/11081
    public void open(Integer orderID, String token) {
        open("profile/client/orders/" + orderID, token);
    }

    public void checkPublishedState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.status.checkCurrentStatus(orderStatus);
                commonTab.details.checkServiceType(serviceType);
                offersCounter.noOffers();
                stepWithRole("Убедиться, что  в Карточке заказа: " + orderStatus + " представлены кнопки Показать на карте и Отменить заказ ", () -> {
                    toMapButtonLocator.shouldBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
//TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                //TODO: add steps for this tab
            });
            System.out.println("client serviceType: " + serviceType + ", client orderStatus: " + orderStatus);
        });
    }

    public void checkReviewOffersState(OrderStatus orderStatus, ServiceType serviceType, Integer offersCount) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.status.checkCurrentStatus(orderStatus);
                commonTab.details.checkServiceType(serviceType);
                offersCounter.amount(offersCount);
                stepWithRole("Убедиться, что  в Карточке заказа: " + orderStatus + " представлены кнопки Показать на карте и Отменить заказ ", () -> {
                    toMapButtonLocator.shouldBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
//TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                nav.docs();
                docsTab.noDocs();
            });
            System.out.println("client serviceType: " + serviceType + ", client orderStatus: " + orderStatus);
        });
    }

    public void checkScheduleVisitState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.status.checkCurrentStatus(orderStatus);
                commonTab.details.checkServiceType(serviceType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Отменить заказ ", () -> {
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
                    submitAgreementButtonLocator.shouldNotBe(visible);
                    mainButtonLocator.shouldNotBe(visible);
                    //TODO buttons for this order state
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                nav.docs();
                docsTab.checkFinishLoadingOLD(orderStatus);
                docsTab.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Скачать документы: Договор ТО и Страховой полис ", () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadInsurance();
                });
            });
            nav.common();
            System.out.println("client serviceType: " + serviceType + ", client orderStatus: " + orderStatus);
        });
    }

    public String getOrderNumber() {
        return titleCardNumberLocator.getText().substring(titleCardNumberLocator.getText().length() - 4);
    }

    public String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public void checkNotReviewedCompletedOrderState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться что баннер с информацией о завершении заказа присутствует", () -> {
                    completeOrderInfoBannerLocator.shouldHave(text(COMPLETE_ORDER_INFO));
                });
                commonTab.status.checkCurrentStatus(orderStatus);
                commonTab.details.checkServiceType(serviceType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Передать договор с описанием и кнопка На главную", () -> {
//                submitAgreementButtonLocator.shouldHave(text("Передать договор")).shouldHave(Condition.cssClass("disabled");
                    submitAgreementButtonLocator.shouldHave(Condition.cssClass("disabled")).shouldHave(text("Передать договор"));
                    submitAgreementSubtitleLocator.shouldHave(text(SUBMIT_AGREEMENT_SUBTITLE));
                    submitReviewButtonLocator.shouldBe(visible);
//                toHomeButtonLocator.shouldHave(text("На главную")); - -after review
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldNotBe(visible);
                    payBillButtonLocator.shouldNotBe(visible);
                    signButtonLocator.shouldNotBe(visible);
                    //TODO buttons for this order state
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                nav.docs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует: " + Doc.AGREEMENT + ",  " + Doc.COMPLETION_ACT + " и " + Doc.INSURANCE, () -> {
                    docsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("COMPLETION_ACT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole("Скачать документы: Договор ТО, Акт выполненных работ и Страховой полис ", () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadCompletionAct();
                    docsTab.downloadInsurance();
                });
            });
            nav.common();
        });
        System.out.println("client serviceType: " + serviceType + "client orderStatus: " + orderStatus);
    }

    public void checkReviewedCompletedOrderState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться что баннер с информацией о завершении заказа присутствует", () -> {
                    completeOrderInfoBannerLocator.shouldHave(text(COMPLETE_ORDER_INFO));
                });
                commonTab.status.checkCurrentStatus(orderStatus);
                commonTab.details.checkServiceType(serviceType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Передать договор с описанием и кнопка На главную", () -> {
                    submitAgreementButtonLocator.shouldBe(enabled).shouldHave(text("Передать договор"));
                    submitAgreementSubtitleLocator.shouldHave(text(SUBMIT_AGREEMENT_SUBTITLE));
                    submitReviewButtonLocator.shouldBe(visible);
                    mainButtonLocator.shouldHave(text("На главную")).as("На главную");
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldNotBe(visible);
                    payBillButtonLocator.shouldNotBe(visible);
                    signButtonLocator.shouldNotBe(visible);
                    //TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Документы", () -> {
                nav.docs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует: " + Doc.AGREEMENT + ",  " + Doc.COMPLETION_ACT + " и " + Doc.INSURANCE, () -> {
                    docsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("COMPLETION_ACT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole(" Скачать документы: Договор ТО, Акт выполненных работ и Страховой полис ", () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadCompletionAct();
                    docsTab.downloadInsurance();
                });
            });
            nav.common();
        });
        System.out.println("client serviceType: " + serviceType + "client orderStatus: " + orderStatus);
    }

    public void checkUrl() {
        urlChecker.urlContains("/client/orders/");
    }

    public void checkTitle() {
        stepWithRole("Убедиться, что заголовок Карточки заказа отображается", () -> {
            titleCardNumberLocator.shouldBe(visible).shouldHave(text(ORDER_CARD_TITLE));
        });
    }

    public void checkOrderNumber(String expectedOrderNumber) {
        stepWithRole("Убедиться, что номер заказа " + expectedOrderNumber + " соответствует ожидаемому", () -> {
            titleCardNumberLocator.shouldHave(partialText(expectedOrderNumber));
        });
    }

    public void checkAllStateRepair(UserRole role, StateRepair state, OrdersIdResponseDto orderDto, TotalPriceResponseDto totalPriceDto) {
        stepWithRole("Убедиться, что статус " + state + " соответствует ожидаемому", () -> {
            checkOrderNumber(orderDto.getData().getNumber());
            consultationNotification.noNoticeConsultationComponent();
            nav.noChecklistTab();
            nav.common();
            state.checkCommonTab(role, this.commonTab, orderDto);
            nav.infoMaster();
            state.checkInfoMasterTab(role, this.infoMasterTab, orderDto);
            switch (state) {
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                    nav.noDocsTab();
                    break;
                default:
                    nav.docs();
                    state.checkDocsTab(role, this.docsTab, orderDto, totalPriceDto);
            }
        });
    }

    public void checkGeneralStateRepair(UserRole role, StateRepair state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что номер заказа " + state + " соответствует ожидаемому, отсутствуют несвойственные элементы", () -> {
            checkOrderNumber(dto.getData().getNumber());
            consultationNotification.noNoticeConsultationComponent();
            nav.noChecklistTab();
        });
    }

    public void checkTabCommonStateRepair(UserRole role, StateRepair state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что вкладка  Общие в состоянии " + state, () -> {
            nav.common();
            state.checkCommonTab(role, this.commonTab, dto);
        });
    }

    public void checkTabInfoMasterStateRepair(UserRole role, StateRepair state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что вкладка  Информация о мастере в состоянии " + state, () -> {
            nav.infoMaster();
            state.checkInfoMasterTab(role, this.infoMasterTab, dto);
        });
    }

    public void checkTabDocsStateRepair(UserRole role, StateRepair state, OrdersIdResponseDto ordersDto, TotalPriceResponseDto totalPriceDto) {
        stepWithRole("Убедиться, что вкладка  Документы в состоянии " + state, () -> {
            switch (state) {
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                    nav.noDocsTab();
                    break;
                default:
                    nav.docs();
                    state.checkDocsTab(role, this.docsTab, ordersDto, totalPriceDto);
            }
        });
    }

    public void checkStateConsultation(StateConsultation state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что статус " + state + " соответствует ожидаемому", () -> {
            checkOrderNumber(dto.getData().getNumber());
            consultationNotification.checkState(state);
            nav.noChecklistTab();
            nav.common();
            state.checkCommonTab(this.commonTab, dto);
            nav.infoMaster();
            state.checkInfoMasterTab(this.infoMasterTab, dto);
            nav.docs();
            state.checkDocsTab(this.docsTab, dto);
        });
    }

    public void checkGeneralStateConsultation(UserRole role, StateConsultation state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что номер заказа " + state + " соответствует ожидаемому, отсутствуют несвойственные элементы", () -> {
            checkOrderNumber(dto.getData().getNumber());
            consultationNotification.checkState(state);
            nav.noChecklistTab();
        });
    }

    public void checkTabCommonStateConsultation(UserRole role, StateConsultation state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что вкладка  Общие в состоянии " + state, () -> {
            nav.common();
            state.checkCommonTab(this.commonTab, dto);
        });
    }

    public void checkTabInfoMasterStateConsultation(UserRole role, StateConsultation state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что вкладка  Информация о мастере в состоянии " + state, () -> {
            nav.infoMaster();
            state.checkInfoMasterTab(this.infoMasterTab, dto);
        });
    }

    public void checkTabDocsStateConsultation(UserRole role, StateConsultation state, OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что вкладка  Документы в состоянии " + state, () -> {
            nav.docs();
            state.checkDocsTab(this.docsTab, dto);
        });
    }

}
