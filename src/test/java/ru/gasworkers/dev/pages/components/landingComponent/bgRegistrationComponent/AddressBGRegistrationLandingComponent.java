package ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.CollectionCondition.size;

public class AddressBGRegistrationLandingComponent extends BaseComponent {
    public AddressBGRegistrationLandingComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            addressFieldLocator = driver.$("div.search-option__address--title.short textarea").as("Поле ввода адреса"),
            dateFieldLocator = driver.$("div.search-option__date--title").as("Дропдаун ввода даты"),
            filledDateFieldLocator = driver.$("div.search-option__date--title-result").as("Поле заполенная  Дата");

    ElementsCollection
            addressSuggestionsCollection = driver.$$("div.address-list li").as("Подсказки адреса");


    public void fillAddressField(String inputClientAddress) {
        stepWithRole("Указать адрес", () -> {
            stepWithRole("начать вводить адрес: " + inputClientAddress, () -> {
                addressFieldLocator.setValue(inputClientAddress);
            });
            stepWithRole("Выбрать первую подсказку: " + addressSuggestionsCollection.get(0).getText(), () -> {
                addressSuggestionsCollection.get(0).click();
            });
            stepWithRole("Убедиться, что скрыты подсказки адреса", () -> {
                addressSuggestionsCollection.shouldHave(size(0));
            });
            stepWithRole("Убедиться, что введенный адрес " + inputClientAddress + " содержится в результирующем адресе: " + addressFieldLocator.getValue(), () -> {
//                addressFieldLocator.shouldHave(partialValue(inputClientAddress));
                assertFormattedAddressContainsCapitalizedWords(inputClientAddress, addressFieldLocator.getValue());
            });
        });
        System.out.println("Введенный адрес: " + inputClientAddress);
        System.out.println("Результирующий адрес: " + addressFieldLocator.getValue());
    }

    public static void assertFormattedAddressContainsCapitalizedWords(String nonFormattedAddress, String formattedAddress) {
        // Extract all the words that start with a capital letter and have at most 4 letters from the non-formatted address
        Pattern pattern = Pattern.compile("(?<=\\s)[А-Я][\\p{L}]{0,3}");
        Matcher matcher = pattern.matcher(nonFormattedAddress);
        List<String> capitalizedWords = new ArrayList<>();
        while (matcher.find()) {
            capitalizedWords.add(matcher.group());
        }

        // Check if all the capitalized words are contained in the formatted address
        for (String word : capitalizedWords) {
            assertThat(formattedAddress).contains(word.substring(0, Math.min(word.length(), 4)));
        }
    }

    public void assertFormattedAddressContainsCapitalizedWordsv2(String nonFormattedAddress, String formattedAddress) {
        // Extract all the words that start with a capital letter from the non-formatted address
        List<String> capitalizedWords = Arrays.asList(nonFormattedAddress.split("\\s+"))
                .stream()
                .filter(word -> Character.isUpperCase(word.charAt(0)))
                .collect(Collectors.toList());

        // Check if all the capitalized words are contained in the formatted address
        for (String word : capitalizedWords) {
            assertThat(formattedAddress).contains(word);
        }
    }
    public String getResultedAddress() {
        return addressFieldLocator.getValue();
    }
}
