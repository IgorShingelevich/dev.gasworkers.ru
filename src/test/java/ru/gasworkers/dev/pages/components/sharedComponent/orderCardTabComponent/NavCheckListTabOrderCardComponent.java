package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.CheckListMasterComponent;

public class NavCheckListTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;
    public final CheckListMasterComponent checkListComponent;
    public NavCheckListTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
        checkListComponent = new CheckListMasterComponent(browser);
    }



    public void checkFinishLoading(OrderStatus orderStatus) {
            orderState.currentStatus(orderStatus);
            checkListComponent.checkFinishLoading();
    }

}
