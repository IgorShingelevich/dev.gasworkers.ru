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
//                        docsDownloadButtonCollection.get(0).shouldBe(visible);
                        break;
                    case COMPLETION_ACT:
                        docsTitleCollection.findBy(text("Акт выполненных работ")).shouldBe(visible);
//                        docsDownloadButtonCollection.get(1).shouldBe(visible);
                        break;
                    case INSURANCE:
                        docsTitleCollection.findBy(text("Страховой полис")).shouldBe(visible);
//                        docsDownloadButtonCollection.get(2).shouldBe(visible);
                        break;
                }
            }
        });
    }

    public NavDocsTabOrderCardComponent downloadAgreement()  throws Exception {
        File agreement = docsTitleCollection.findBy(text("Договор ТО")).download();
        InputStream is = new FileInputStream(agreement);
        return this;
    }
    public NavDocsTabOrderCardComponent downloadCompletionAct() throws Exception {
        File completionAct = docsTitleCollection.findBy(text("Акт выполненных работ")).download();
        InputStream is = new FileInputStream(completionAct);
        return this;
    }

    public NavDocsTabOrderCardComponent downloadInsurance() throws Exception {
        File insurance = docsTitleCollection.findBy(text("Страховой полис")).download();
        InputStream is = new FileInputStream(insurance);
        return this;
    }

}
