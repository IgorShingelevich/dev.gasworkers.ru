package ru.gasworkers.dev.tests.registration.usualRegistration.usualSelfEmployedRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomMaster;

import static io.qameta.allure.Allure.step;

public class ByPhoneSelfEmployedRegistrationTest extends BaseTest {
    RandomMaster randomMaster = new RandomMaster();


    @Browser(role = Role.SELF_EMPLOYED, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    SelfEmployedPages selfEmployedPages;

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.USUAL_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_MEMBER),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация СМЗ в СК")
    public void  ByPhoneSEMemberRegistrationTest() {
        step("Страница лендинга", () -> {
            selfEmployedPages.getLandingPage().open();
            selfEmployedPages.getLandingPage().checkFinishLoading();
            selfEmployedPages.getLandingPage().signUpMaster();
        });
        step("Страница первого шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().firstStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().firstStep.selectSE();
            selfEmployedPages.getRegistrationPage().firstStep.nextButton();
        });
        step("Страница второго шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().secondStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().secondStep.byPhone(randomMaster.getPhone());
            selfEmployedPages.getRegistrationPage().secondStep.nextButton();
        });
        step("Страница третьего шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().thirdStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().thirdStep.codeConfirmation.fillCode(randomMaster.getConfirmationCode());
        });


    }


}
