package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;

public class NavigationOrderCardPageComponent extends BaseComponent {
    SelenideElement
            navCommonTabButtonLocator = driver.$("li[data-name='common']").as("Вкладка Описание заказа"),
            navChecklistTabButtonLocator = driver.$("li[data-name='checklist']").as("Вкладка Чек-лист"),
            navInfoMasterTabButtonLocator = driver.$("li[data-name='master']").as("Вкладка Информация о мастере"),
            navDocsTabButtonLocator = driver.$("li[data-name='documents']").as("Вкладка Документы");

    public NavigationOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
    }

    public void common() {
        stepWithRole("Перейти на вкладку Описание заказа", () -> {
            navCommonTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Описание заказа", () -> {
                navCommonTabButtonLocator.shouldHave(cssClass("active"));
            });
        });
    }

    public void checkList() {
        stepWithRole("Перейти на вкладку Чек лист", () -> {
            navChecklistTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Чек лист", () -> {
                navChecklistTabButtonLocator.shouldHave(cssClass("active")); // TODO uncomment after fix
            });
        });
    }

   /* public void infoMaster() {
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navInfoMasterTabButtonLocator.click();
            driver.$("li[data-name='master']").click();
            stepWithRole("Убедиться, что открылась вкладка Информация по работам", () -> {
                navInfoMasterTabButtonLocator.shouldHave(cssClass("active"));
            });
        });
    }*/

    public void infoMaster() {
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            Selenide.sleep(1000); // Sleep for 1 second
            navInfoMasterTabButtonLocator.click();
            driver.$("li[data-name='master']").click();
            stepWithRole("Убедиться, что открылась вкладка Информация по работам", () -> {
//                if (!navInfoMasterTabButtonLocator.has(Condition.cssClass("active"))) {
//                    Selenide.sleep(1000); // Sleep for 1 second
//                    navInfoMasterTabButtonLocator.shouldHave(Condition.cssClass("active"));
//                } else {
                navInfoMasterTabButtonLocator.shouldHave(Condition.cssClass("active"));
//                }
            });
        });
    }

    public void docs() {
        stepWithRole("Перейти на вкладку Документы", () -> {
            Selenide.sleep(1000); // Sleep for 1 second
            navDocsTabButtonLocator.click();
            stepWithRole("Убедиться, что открылась вкладка Документы", () -> {
                navDocsTabButtonLocator.shouldHave(cssClass("active"));
            });
        });
    }

    public void noDocsTab() {
        stepWithRole("Убедиться, что нет вкладки Документы", () -> {
            navDocsTabButtonLocator.shouldNotBe(visible);
        });
    }



    public void noChecklistTab() {
        stepWithRole("Убедиться, что нет вкладки Чек лист", () -> {
            navChecklistTabButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkChecklistTab() {
        stepWithRole("Убедиться, что есть вкладка Чек лист", () -> {
            navChecklistTabButtonLocator.shouldBe(visible);
        });
    }

    public void checkNavStateMasterRole(StateRepair state) {
        switch (state) {
            case WAIT_SERVICE_MASTER_SD_PROCESS:
                checkChecklistTab();
                break;
            default:
                noChecklistTab();
        }
    }
}
