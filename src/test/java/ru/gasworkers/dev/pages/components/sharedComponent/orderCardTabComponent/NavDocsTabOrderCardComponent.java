package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.CollectionCondition.size;


import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Doc.*;


public class NavDocsTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStateCardComponent orderState;

    public NavDocsTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStateCardComponent(browser);
    }

    ElementsCollection
            docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов"),
            docsDownloadCollection = driver.$$(".link-pdf span").as("Кнопки скачать документы");

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
    public void presentedDocs(Doc... docs) {
        stepWithRole("Убедиться, что документы " + docs + " присутствуют", () -> {
            for (Doc doc : docs) {
                switch (doc) {
                    case AGREEMENT:
                        docsTitleCollection.findBy(text("Договор ТО")).shouldBe(visible);
                        docsDownloadCollection.findBy(text("Скачать")).shouldBe(visible);
                        break;
                    case COMPLETION_ACT:
                        docsTitleCollection.findBy(text("Акт выполненных работ")).shouldBe(visible);
                        docsDownloadCollection.findBy(text("Скачать")).shouldBe(visible);
                        break;
                    case INSURANCE:
                        docsTitleCollection.findBy(text("Страховой полис")).shouldBe(visible);
                        docsDownloadCollection.findBy(text("Скачать")).shouldBe(visible);
                        break;
                }
            }
        });
    }

    public void noDocs() {
        stepWithRole("Убедиться, что нет документов", () -> {
            docsTitleCollection.shouldBe(size(0));
        });
    }


}
