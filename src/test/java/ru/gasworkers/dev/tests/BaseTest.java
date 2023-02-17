package ru.gasworkers.dev.tests;

public class BaseTest {

}
// TODO DISCUSS 1  should i include url check to  all .checkFinishLoading() methods?
// TODO DISCUSS 2 should i follow this  implementation for  validations check
 /*implementation in  validations check
        Нужна коллекция элементов с текстами ошибок от инпутов.

        $$(“input > .error-message”).shouldHave(texts(“Требуется поле Адрес электронной почты”, “Требуется поле Имя”, “Требуется поле Пароль”))

        List<String> actualErrorsText = new ArrayList<>();
        List<SelenideElement> arrOfField = $$(".control-group");
        for (SelenideElement field : arrOfField) {
        field.$(".controls.input-validation-error").click();
        actualErrorsText.add(field.$(".tooltip-alert.alert-danger").shouldBe(Condition.visible).getText());
        }*/
// TODO DISCUSS 3  public final  vs private final in PageComponents
// TODO DISCUSS 4  delete previous Allure run results before new run
// TODO DISCUSS 5  managing   attachments in Allure - pics ( only  if fall), open web page from attachments
// TODO DISCUSS 6  download method FOLDER -  periodically fall download


