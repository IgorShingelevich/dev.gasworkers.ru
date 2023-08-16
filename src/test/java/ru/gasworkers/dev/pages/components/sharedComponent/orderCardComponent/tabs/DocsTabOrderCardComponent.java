package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class DocsTabOrderCardComponent extends BaseOrderCardComponent {
    public final StatusOrderCardPageComponent orderState;
    public final FillUpCheckListBannerComponent fillUpBanner;

    private final long downloadTimeout = 4_000L;

    public DocsTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new StatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
    }

    ElementsCollection
        docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов"),
        docsDownloadButtonCollection = driver.$$(".link-pdf").as("Кнопки скачать документы");

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.checkCurrentStatus(orderStatus);
    }

    public void noDocs() {
        stepWithRole("Убедиться, что нет документов", () -> {
            docsTitleCollection.shouldBe(size(0));
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
