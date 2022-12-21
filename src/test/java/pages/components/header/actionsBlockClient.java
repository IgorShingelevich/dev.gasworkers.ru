package pages.components.header;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class actionsBlockClient {

   SelenideElement actionsBlock = $(By.xpath("//div[@class='actions-block']"));

}
