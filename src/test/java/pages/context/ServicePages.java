package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;

import static model.Role.SERVICE;


public class ServicePages extends BaseRolePages {
    public ServicePages(SelenideDriver driver) {
        super(SERVICE, driver);
    }



}
