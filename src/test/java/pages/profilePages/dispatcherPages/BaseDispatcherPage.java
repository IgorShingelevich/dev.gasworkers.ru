package pages.profilePages.dispatcherPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

public class BaseDispatcherPage {

    public ActionsBlockDispatcherComponent actionBlock = new ActionsBlockDispatcherComponent();
    public  SidebarDispatcherComponent sidebar = new SidebarDispatcherComponent();

    SelenideElement
        popupLocator = $(".notice-list-fixed button.btn.btn-secondary");

   public void popUpClose() {

        if (popupLocator.isDisplayed()) {
            popupLocator.click();
        }
        else
            try {
                popupLocator.wait( 2000);
                popupLocator.click();
            }
            catch (Exception e) {
                System.out.println("Popup is not displayed");
            }
    }



}
