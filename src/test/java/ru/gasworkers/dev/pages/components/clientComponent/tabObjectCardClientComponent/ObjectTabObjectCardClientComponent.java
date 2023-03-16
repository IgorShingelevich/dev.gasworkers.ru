package ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.util.ArrayList;
import java.util.List;

public class ObjectTabObjectCardClientComponent extends BaseComponent {

    public final NotEditableStateBannerObjectCardClientComponent noEditBanner;

    public ObjectTabObjectCardClientComponent (RoleBrowser browser){
        super(browser);
        noEditBanner = new NotEditableStateBannerObjectCardClientComponent(browser);
    }

    ElementsCollection
     objectInfoCollection = driver.$$("div.title").as("Информация об объекте");

    SelenideElement
    objectNameLocator = objectInfoCollection.get(0).as("Имя объекта");


    public String getName(){
        System.out.println("object name: " + objectNameLocator.getText());
        return objectNameLocator.getText();
    }

    public List<String> getEquipmentList (){
          List<String> equipmentList = new ArrayList<>();
          for (int i = 1; i < objectInfoCollection.size() -1; i++) {
                equipmentList.add(objectInfoCollection.get(i).getText());
              System.out.println(i + " equimpment: " + objectInfoCollection.get(i).getText());
          }
          return equipmentList;
    }





}
