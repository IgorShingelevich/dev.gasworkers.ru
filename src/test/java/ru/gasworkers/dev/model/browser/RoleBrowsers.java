package ru.gasworkers.dev.model.browser;

import com.codeborne.selenide.SelenideDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RoleBrowsers {

    private final List<RoleBrowser> browsers;

    public RoleBrowsers() {
        browsers = new ArrayList<>();
    }

    public RoleBrowsers add(RoleBrowser browser) {
        browsers.add(browser);
        return this;
    }

    public List<SelenideDriver> getDrivers() {
        return browsers.stream().map(RoleBrowser::getDriver).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return browsers.isEmpty();
    }

}
