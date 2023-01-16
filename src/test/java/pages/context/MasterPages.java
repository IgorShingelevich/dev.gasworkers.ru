package pages.context;

import com.codeborne.selenide.SelenideDriver;

import static model.Role.*;

public final class MasterPages extends BaseRolePages {

    public MasterPages(SelenideDriver driver) {
        super(MASTER, driver);
    }

}
