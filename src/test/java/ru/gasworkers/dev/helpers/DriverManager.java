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
        step(roleBrowser.getUserRole() + ": back", () ->
                roleBrowser.getDriver().back());
    }

    public void refresh() {
        step(roleBrowser.getUserRole() + ": refresh", () ->
                roleBrowser.getDriver().refresh());
    }

    public void forward() {
        step(roleBrowser.getUserRole() + ": forward", () ->
                roleBrowser.getDriver().forward());
    }

    public void zoom(double factor) {
        step(roleBrowser.getUserRole() + ": zoom", () ->
                roleBrowser.getDriver().zoom(factor));
//            executeJavaScript("document.body.style.zoom='50%'");
//            executeJavaScript("window.devicePixelRatio = 0.5");
//            executeJavaScript("window.resizeBy(50, 50)");
//            executeJavaScript("window.resizeTo(50, 50)");
    }

    public void switchToTab(int index) {
        step(roleBrowser.getUserRole() + ": switch to tab", () -> {
            roleBrowser.getDriver().driver().switchTo().frame(index);
        });
    }

    // close current tab .closeWindow
    public void closeTab() {
        roleBrowser.getDriver().driver().close();

    }

    @Step("Add screenshot to {attachName}")
    public void screenshot(String attachName) {
        Attach.screenshotAs(roleBrowser.getDriver(), attachName);
    }

    public void screenshot() {
        screenshot("Screenshot page");
    }

    public void close() {
        step(roleBrowser.getUserRole() + ": close", () ->
                roleBrowser.getDriver().close());
    }


}
