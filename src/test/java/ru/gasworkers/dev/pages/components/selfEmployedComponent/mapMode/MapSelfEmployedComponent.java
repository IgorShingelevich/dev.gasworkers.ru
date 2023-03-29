package ru.gasworkers.dev.pages.components.selfEmployedComponent.mapMode;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MapSelfEmployedComponent extends BaseComponent {
    public MapSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            mapLegendDropdownText = "Легенда карты",
            mapLegendItem1Text = "- Тендер ТО",
            mapLegendItem2Text = " - Тендер Ремонт",
            mapLegendItem3Text = "- Участвую в тендере",
            mapLegendItem4Text = "- Заказы принятые диспетчером в работу";

    ElementsCollection
            mapLegendItemsCollection = driver.$$("div.map-sticky__header--legend li").as("Элементы легенды карты");

    SelenideElement
            mapSpinner = driver.$("img[src = '/_ipx/_/images/logo-blue-loader.svg']").as("Спиннер карты"),
            mapPlusButton = driver.$(".ymaps-2-1-79-zoom__icon.ymaps-2-1-79-float-button-icon").as("Кнопка плюс карты"),
            mapOffersBox = driver.$("div.map-sticky__header--offers").as("Блок с предложениями карты"),
            mapOrdersLegendDropdown = driver.$("div[data-guide = 'orders-legend']").as("Дропдаун легенда заказов"),
            mapSizeToggleButtonLocator = driver.$("div.map-sticky__header--full").as("Кнопка переключения размера карты");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что все элементы карты загрузились", () -> {
            stepWithRole("Убедиться, что кнопки плюс и переключения размера карты отображаются", () -> {
                mapPlusButton.shouldBe(visible, Duration.ofSeconds(20));
                mapSizeToggleButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что  блок с предложениями карты отображается", () -> {
                mapOffersBox.shouldBe(visible);
            });
            checkMapLegend();
            checkNoSpinner();
        });
        System.out.println("mapOffersBlock: " + mapOffersBox.getText());
    }

    public void toggleMapSize() {
        stepWithRole("Переключить размер карты", () -> {
            mapSizeToggleButtonLocator.click();
        });
    }

    public void checkMapLegend() {
        stepWithRole("Убедиться, что легенда карты отображается", () -> {
            stepWithRole("Убедиться, что элементы легенды скрыты", () -> {
                mapLegendItemsCollection.shouldBe(CollectionCondition.size(0));
            });
            clickDropdown();
            stepWithRole("Убедиться, что элементы легенды отображаются", () -> {
                mapLegendItemsCollection.shouldBe(CollectionCondition.size(4));
                mapLegendItemsCollection.get(0).shouldHave(text(mapLegendItem1Text));
                mapLegendItemsCollection.get(1).shouldHave(text(mapLegendItem2Text));
                mapLegendItemsCollection.get(2).shouldHave(text(mapLegendItem3Text));
                mapLegendItemsCollection.get(3).shouldHave(text(mapLegendItem4Text));
            });
            clickDropdown();
            stepWithRole("Убедиться, что элементы легенды скрыты", () -> {
                mapLegendItemsCollection.shouldBe(CollectionCondition.size(0));
            });
        });
    }

    public void clickDropdown() {
        stepWithRole("Кликнуть на дропдаун легенды", () -> {
            mapOrdersLegendDropdown.click();
        });
    }

    public Integer offersCount2() {
        String value = mapOffersBox.getText();
        Integer count = null; // initialize the count variable to null

        String[] parts = value.split("\\s+");
        if (parts.length > 0) {
            try {
                count = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + value);
            }
        } else {
            System.out.println("Invalid input: " + value);
        }

        return count; // return the extracted count value (or null if parsing failed)
    }

    public Integer offersCount() {
        String value = mapOffersBox.getText();
        Integer count = null; // initialize the count variable to null

        Pattern pattern = Pattern.compile("^\\d{1,3}"); // define the regular expression pattern
        Matcher matcher = pattern.matcher(value); // create a matcher object

        if (matcher.find()) { // check if the pattern is found in the input string
            try {
                count = Integer.parseInt(matcher.group()); // extract the matched substring as an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + value);
            }
        } else {
            System.out.println("Invalid input: " + value);
        }

        return count; // return the extracted count value (or null if parsing failed)
    }

    private void checkNoSpinner() {
        stepWithRole("Убедиться, что спиннер карты не отображается", () -> {
            mapSpinner.shouldNotBe(visible);
        });
    }
}
//todo check toggle fullScreenMapMode
//todo geo markers interaction