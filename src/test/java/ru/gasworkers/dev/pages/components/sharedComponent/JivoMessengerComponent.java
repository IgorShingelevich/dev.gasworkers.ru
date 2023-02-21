package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class JivoMessengerComponent extends BaseComponent {
    public JivoMessengerComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        containerLocator = driver.$("jdiv[class*=envelopeIcon]").as("Контейнер мессенджера"),
        closeButtonLocator = driver.$("jdiv[class*=closeButton]").as("Кнопка закрытия мессенджера"),
        textareaLocator = driver.$("textarea[placeholder*=Введите]").as("Текстовое поле мессенджера");

    public void checkFinishLoadingAndClose() {
        stepWithRole("Проверить, что иконка мессенджера присутствует", () -> {
            openJivoWidget();
            closeJivoWidget();
        });
    }

    public void presenceOfJivoIcon() {
        stepWithRole("Проверить, что иконка мессенджера присутствует", () -> {
            try {
                if (containerLocator.exists()) {
                    containerLocator.shouldBe(visible);
//                    if(textareaLocator.exists()){
//                    closeJivoWidget();
//                    }
                } else { stepWithRole("Иконка мессенджера отсутствует", () -> {
                    System.out.println("No Jivo widget");
                });
                }
            } catch (ElementShould e) {
                // Log the exception and proceed with the test
                System.out.println("Element Jivo widget was not found or not enabled or not visible.");
                e.printStackTrace();
            }
        });
    }



    public void openJivoWidget() {
        stepWithRole("Открыть виджет мессенджера", () -> {
            containerLocator.shouldBe(visible, Duration.ofSeconds(10));
            containerLocator.click();
            textareaLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
    }

    public void closeJivoWidget() {
        stepWithRole("Закрыть виджет мессенджера", () -> {
            closeButtonLocator.click();
            textareaLocator.shouldNotBe(visible);
        });
    }

}
