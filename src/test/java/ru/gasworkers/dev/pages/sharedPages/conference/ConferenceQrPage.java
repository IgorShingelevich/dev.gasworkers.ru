package ru.gasworkers.dev.pages.sharedPages.conference;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;

public class ConferenceQrPage extends BasePage {
    private final String title = "Видеоконсультация",
            description = "Для удобства работы отсканируйте QR - kod вашим мобильным устройством, но при наличии камеры на вашем компьютере видеоконсультацию можете остаться тут.";

    public ConferenceQrPage(RoleBrowser browser) {
        super(browser);
    }

    @Override
    public void outlineButton() {
        super.outlineButton();
    }

    public void checkUrl() {
        urlChecker.urlStartsWith("https://dev.gasworkers.ru/conference");
        urlChecker.urlContains("/qr");
    }
}
