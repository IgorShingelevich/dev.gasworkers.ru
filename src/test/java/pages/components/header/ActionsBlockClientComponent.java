package pages.components.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ActionsBlockClientComponent {

   SelenideElement actionsBlock = $(By.xpath("//div[@class='actions-block']"));

   public SelenideElement div = $x("//div[@class='notifications icon']");

   public SelenideElement div2 = $x("//div[@class='messages icon']");

   public SelenideElement div3 = $x("//div[@class='profile icon']");

   public SelenideElement divProfileMenu = $x("//div[contains(@class, 'text-primary')]");
   
   

   
   

}
