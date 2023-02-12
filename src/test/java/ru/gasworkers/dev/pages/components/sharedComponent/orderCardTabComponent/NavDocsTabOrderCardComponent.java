package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.CollectionCondition.size;


import static io.qameta.allure.Allure.step;


public class NavDocsTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;

    public NavDocsTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
    }

    ElementsCollection
        docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов"),
        docsDownloadButtonCollection = driver.$$(".link-pdf").as("Кнопки скачать документы");

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.currentStatus(orderStatus);
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

    public NavDocsTabOrderCardComponent downloadAgreement()  throws Exception {
        stepWithRole("Скачать договор ТО", () -> {
            File agreement = docsTitleCollection.findBy(text("Договор ТО")).download(1000L);
            InputStream is = new FileInputStream(agreement);
        });
        return this;
    }
    public NavDocsTabOrderCardComponent downloadCompletionAct() throws Exception {
        stepWithRole("Скачать акт выполненных работ", () -> {
            File completionAct = docsTitleCollection.findBy(text("Акт выполненных работ")).download(1000L);
            InputStream is = new FileInputStream(completionAct);
        });
        return this;
    }

    public NavDocsTabOrderCardComponent downloadInsurance() throws Exception {
        stepWithRole("Скачать страховой полис", () -> {
            File insurance = docsTitleCollection.findBy(text("Страховой полис")).download(1000L);
            InputStream is = new FileInputStream(insurance);
        });
        return this;
    }

}
