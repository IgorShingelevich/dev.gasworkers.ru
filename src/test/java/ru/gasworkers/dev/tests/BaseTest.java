package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.extension.browser.ScreenshotExtension;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {

}
// TODO DISCUSS  download method FOLDER -  periodically fall download - Failed to download file in 1000 ms.
// TODO DISCUSS   delete previous Allure run results before new run - from repo and from local machine - Report successfully generated to C:\Users\V\AppData\Local\Temp\15304290317834521042\allure-report
// TODO DISCUSS   managing   attachments in Allure - pics ( only  if fall), open web page from attachments
// TODO DISCUSS   should i include url check to  all .checkFinishLoading() methods?
// TODO DISCUSS ru/gasworkers/dev/pages/components/masterComponent/editObjectMasterComponents/EditObjectMasterComponent.java:46 - ElementsCollection.SelenideElement construction
// TODO DISCUSS   how to handle equipment description. Different amount of equipment in different objects - how to put them in variables?
//TODO DISCUSS add generated equipment, address ant all generated data to builder
// TODO DISCUSS   public final  vs private final in PageComponents
// TODO DISCUSS  should i follow this  implementation for  validations check
 /*implementation in  validations check
        Нужна коллекция элементов с текстами ошибок от инпутов.

        $$(“input > .error-message”).shouldHave(texts(“Требуется поле Адрес электронной почты”, “Требуется поле Имя”, “Требуется поле Пароль”))

        List<String> actualErrorsText = new ArrayList<>();
        List<SelenideElement> arrOfField = $$(".control-group");
        for (SelenideElement field : arrOfField) {
        field.$(".controls.input-validation-error").click();
        actualErrorsText.add(field.$(".tooltip-alert.alert-danger").shouldBe(Condition.visible).getText());
        }*/


