package ru.gasworkers.dev.helpers;

import io.qameta.allure.Step;
import ru.gasworkers.dev.model.browser.RoleBrowser;

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

    public void zoom(double factor) {
        step(roleBrowser.getRole() + ": zoom", () ->
                roleBrowser.getDriver().zoom(factor));
//            executeJavaScript("document.body.style.zoom='50%'");
//            executeJavaScript("window.devicePixelRatio = 0.5");
//            executeJavaScript("window.resizeBy(50, 50)");
//            executeJavaScript("window.resizeTo(50, 50)");
    }

    @Step("Add screenshot to {attachName}")
    public void screenshot(String attachName) {
        Attach.screenshotAs(roleBrowser.getDriver(), attachName);
    }

    public void screenshot() {
        screenshot("Screenshot page");
    }

}
