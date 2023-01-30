package helpers;

import model.browser.RoleBrowser;

import static io.qameta.allure.Allure.step;

public final class DriverManager {

    private final RoleBrowser roleBrowser;

    public DriverManager(RoleBrowser roleBrowser) {
        this.roleBrowser = roleBrowser;
    }

    public void back() {
        step(roleBrowser.getRole() + ": back", () ->
                roleBrowser.getDriver().back());
    }

    public void refresh() {
        step(roleBrowser.getRole() + ": refresh", () ->
                roleBrowser.getDriver().refresh());
    }

    public void forward() {
        step(roleBrowser.getRole() + ": forward", () ->
                roleBrowser.getDriver().forward());
    }

}