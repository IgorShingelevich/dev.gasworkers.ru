package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.extension.browser.ScreenshotExtension;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {

}

//todo rerun failed tests after run all tests
// todo  if Client and SelfEmployed ActionBlock differs only in reviewLink - how to reuse it?
// todo reuse master and self-employed OrderCardPage
//TODO DISCUSS  how to use sublist like -   ratingCheckboxCollection = checkboxCollection.subList(0, 5).as("Чекбоксы фильтра по рейтингу мастера"),
//ctrl w for Alex
// TODO DISCUSS pdf parsing
// TODO DISCUSS  TestClass Steps or Components
//TODO DISCUSS How to assign to client builder fields new variables
//TODO DISCUSS checkList radio buttons :after pseudo ru/gasworkers/dev/pages/components/masterComponent/CheckListMasterComponent.java:164
//TODO DISCUSS why collection stream is not recommended? C:/Users/V/.gradle/caches/modules-2/files-2.1/com.codeborne/selenide-core/6.11.2/dd2c544c99bca19de75c59f09c32c687154786af/selenide-core-6.11.2-sources.jar!/com/codeborne/selenide/ElementsCollection.java:457
//TODO DISCUSS   pride ru/gasworkers/dev/pages/components/masterComponent/CheckListMasterComponent.java:136 iteration vs single line
// TODO DISCUSS  SizeBrowser and PositionBrowser - solutions
//TODO DISCUSS   step Проверить заполненные данные - BG Registration address not refreshing, date - null
// TODO DISCUSS  download method FOLDER -  periodically fall download - Failed to download file in 1000 ms.
// TODO DISCUSS   delete previous Allure run results before new run - from repo and from local machine - Report successfully generated to C:\Users\V\AppData\Local\Temp\15304290317834521042\allure-report
// TODO DISCUSS   managing   attachments in Allure - pics ( only  if fall), open web page from attachments
// TODO DISCUSS ru/gasworkers/dev/pages/components/masterComponent/editObjectMasterComponents/EditObjectMasterComponent.java:46 - ElementsCollection.SelenideElement construction
// TODO DISCUSS   should i include url check to  all .checkFinishLoading() methods?
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


