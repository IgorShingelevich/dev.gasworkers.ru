package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.SharedButtonsOrderCardClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.SuggestedMasterRepairCommonTabOrderCardComponent;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;


public class DocsTabOrderCardComponent extends BaseOrderCardComponent {
    public final StatusOrderCardPageComponent status;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final SuggestedMasterRepairCommonTabOrderCardComponent suggestedMastersRepair;
    public final SharedButtonsOrderCardClientComponent buttons;


    private final long downloadTimeout = 4_000L;
    SelenideElement
            noDocsInfoBoxLocator = driver.$("div.text-center").as("Информационный блок о том, что документов нет"),
            self = driver.$("div.order-details").as("Контейнер с документами"),
            totalPriceLocator = self.$("div.order-details-item").as("Итоговая стоимость");
    ElementsCollection
            docsBoxCollection = self.$$("div.link-pdf-wrapper").as("Контейнеры с документами"),
            docsTitleCollection = self.$$("div .link-pdf ").as("Названия документов"),
            docsDownloadButtonCollection = self.$$(".link-pdf").as("Кнопки скачать документы");

    public DocsTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        status = new StatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        suggestedMastersRepair = new SuggestedMasterRepairCommonTabOrderCardComponent(browser);
        buttons = new SharedButtonsOrderCardClientComponent(browser);
    }

    public void checkFinishLoadingOLD(OrderStatus orderStatus) {
        status.checkCurrentStatus(orderStatus);
    }

    public void checkFinishLoadingOLD() {
        stepWithRole("Убедиться, что контейнер с документами загрузился", () -> {
            self.shouldBe(visible);
            totalPriceLocator.shouldBe(visible);
        });
    }

    public void checkNoTotalPrice() {
        stepWithRole("Убедиться, что итоговая стоимость отсутствует", () -> {
            totalPriceLocator.shouldNotBe(visible);
        });
    }



    public void checkNoDocsInfoBox() {
        stepWithRole("Убедиться, что информационный блок о том, что документов нет, присутствует", () -> {
            noDocsInfoBoxLocator.shouldHave(text("Документов пока нет"));
        });
    }

    public void noDocs() {
        stepWithRole("Убедиться, что нет документов", () -> {
            docsBoxCollection.shouldBe(size(0));
        });
    }

    public void checkActDoc() {
        stepWithRole("Убедиться, что документ Акт выполненных работ присутствует", () -> {
            docsTitleCollection.findBy(text("Акт выполненных работ")).shouldBe(visible);
        });
    }

    public void checkTotalPrice(String finalPrice) {
        stepWithRole("Убедиться, что итоговая стоимость " + finalPrice, () -> {
            totalPriceLocator.shouldHave(partialText(finalPrice));
        });
    }

    public void presentedDocs(Doc... docs) {
        stepWithRole("Убедиться, что документы " + docs + " присутствуют", () -> {
            for (Doc doc : docs) {
                switch (doc) {
                    case AGREEMENT:
                        docsTitleCollection.findBy(text("Договор ТО")).shouldBe(visible);
                        break;
                    case COMPLETION_ACT:
                        docsTitleCollection.findBy(text("Акт выполненных работ")).shouldBe(visible);
                        break;
                    case INSURANCE:
                        docsTitleCollection.findBy(text("Страховой полис")).shouldBe(visible);
                        break;
                }
            }
            System.out.println("Документы: " + docs + " присутствуют");
        });
    }

    public File downloadAgreement() {
        File file = stepWithRole("Скачать договор ТО", () -> {
            return docsTitleCollection.findBy(text("Договор ТО")).download(downloadTimeout);
        });
        return file;
    }

    public File downloadCompletionAct() throws Exception {
        return stepWithRole("Скачать акт выполненных работ", () -> {
            return docsTitleCollection.findBy(text("Акт выполненных работ")).download(downloadTimeout);
        });
    }

    public File downloadInsurance() throws Exception {
        return stepWithRole("Скачать страховой полис", () ->
                docsTitleCollection.findBy(text("Страховой полис")).download(downloadTimeout));
    }


}
