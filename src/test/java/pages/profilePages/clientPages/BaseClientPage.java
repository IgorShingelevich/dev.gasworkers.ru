package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.breadcrumbsComponent.ClientBreadcrumbsComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import static com.codeborne.selenide.Selenide.$;

public class BaseClientPage {

    public ActionsBlockClientComponent actionBlock = new ActionsBlockClientComponent();
    public SidebarClientComponent sidebar = new SidebarClientComponent();
    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();

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
