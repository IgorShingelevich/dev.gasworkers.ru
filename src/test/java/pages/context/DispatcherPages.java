package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import model.Role;

@Getter
public final class DispatcherPages extends BaseRolePages {

    public DispatcherPages(SelenideDriver driver) {
        super(Role.DISPATCHER, driver);
    }

}
