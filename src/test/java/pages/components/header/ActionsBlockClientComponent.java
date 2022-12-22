package pages.components.header;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ActionsBlockClientComponent {

   SelenideElement actionsBlock = $(".actions-block");

   public SelenideElement linkNotificationsABLocator = $(".notifications icon");

   public SelenideElement linkMessagesABLocator = $x("//div[@class='messages icon']"); // $(".messages icon");

   public SelenideElement dropdownProfileABLocator = $x("//div[@class='profile icon']"); // $(".profile icon");

   public SelenideElement profileNameABLocator = $x("//div[contains(@class, 'text-primary')]"); // $(".profile-menu__link text-primary");

   public SelenideElement linkProfileEditABLocator = $x("//a[@href='/profile/edit']"); //

   public SelenideElement linkReviewABLocator = $x("//a[@href='/profile/reviews']");

   public SelenideElement linkLogoutABLocator = $("profile-menu__link").$(".back-ic"); //


   
   
   
   

   
   

}
