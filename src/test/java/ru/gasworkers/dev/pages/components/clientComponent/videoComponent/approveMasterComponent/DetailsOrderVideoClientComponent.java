package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.approveMasterComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class DetailsOrderVideoClientComponent extends BaseComponent {
    public DetailsOrderVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }


    private final String
        titleText = "Информация по заказу";

    ElementsCollection
        serviceStarRatingCollection = driver.$$("div.gas-stars__star").as("Звезды рейтинга  сервисной компании"),
        mediums = driver.$$(".medium");

    SelenideElement
        titleLocator = driver.$("p.h4.mb-20").as("Заголовок компонента Информация по заказу"),
        masterFullNameLocator = driver.$("p.h3.link-dark-blue.w-100").as("ФИО мастера"),
        masterRatingLocator = driver.$("div.rating-badge").as("Рейтинг мастера"),
        quantityOfReviewsLocator = driver.$("div.reviews a").as("Количество отзывов мастера"),
        workPlaceMasterLocator = driver.$("p.medium.w-100 a").as("Место работы мастера"),
        quantityOfCompletedOrdersLocator = driver.$("span.bag.small.ms-3").as("Количество выполненных заказов мастера"),
        orderDateLocator = driver.$("div.mb-20.d-flex span").as("Дата заказа");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Детали заказа загрузился", () -> {
            titleLocator.shouldHave(text(titleText));

        });
    }

    public void checkMasterFullName(String fullName) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается ФИО мастера: " + masterFullNameLocator.getText(), () -> {
            masterFullNameLocator.shouldHave(text(fullName));
        });
        System.out.println("Мастер: " + masterFullNameLocator.getText());
    }

    public void checkPlaceWork(String placeWork) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается место работы мастера: ", () -> {
            mediums.findBy(Condition.partialText("Место работы:")).shouldHave(text(placeWork));
        });
        System.out.println("Место работы: " + mediums.findBy(Condition.text("Место работы:")).getText());
    }

     public void  checkQuantityOfCompletedOrders (String quantityOfCompletedOrders) {
         stepWithRole("Убедиться, что в компоненте Детали заказа отображается количество выполненных заказов мастера: " + mediums.findBy(Condition.text("Количество выполненных заказов:")).getText(), () -> {
             mediums.findBy(Condition.text("Количество выполненных заказов:")).shouldHave(text(quantityOfCompletedOrders));
         });
            System.out.println("Количество выполненных заказов: " + mediums.findBy(Condition.text("Количество выполненных заказов:")).getText());
     }

        public void checkOrderDate(String orderDate) {
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается Выбранная дата: "  + mediums.findBy(Condition.text("Выбранная дата:")).sibling(0).getText(), () -> {
                mediums.findBy(Condition.text("Выбранная дата:")).sibling(0).shouldHave(text(orderDate));
            });
            System.out.println("Дата заказа: " + mediums.findBy(Condition.text("Выбранная дата:")).sibling(0).getText());
        }

        public void checkRightNowTimeOrderState() {
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается время заказа: "  + mediums.findBy(Condition.text("Выбранное время:")).sibling(0).getText(), () -> {
                mediums.findBy(Condition.text("Выбранное время:")).sibling(0).shouldHave(text("Прямо сейчас"));
            });
            System.out.println("Выбранное время:" + mediums.findBy(Condition.text("Выбранное время:")).sibling(0).getText());
        }

        public void checkPriceOrder(String priceOrder) {
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается Стоимость: "  + mediums.findBy(Condition.text("Стоимость:")).sibling(0).getText(), () -> {
                mediums.findBy(Condition.text("Стоимость:")).sibling(0).shouldHave(text(priceOrder));
            });
            System.out.println("Стоимость: " + mediums.findBy(Condition.text("Стоимость:")).sibling(0).getText());
        }

    public void checkOrderType(OrderType orderType) {
        stepWithRole("Убедиться, что в компоненте  тип заказа  - Видеоконсультация", () -> {
            mediums.findBy(Condition.text("Тип заказа:")).sibling(0).shouldHave(text(orderType.toString()));
        });
        System.out.println("Тип заказа:" + mediums.findBy(Condition.text("Тип заказа:")).sibling(0).text());
    }

    public void checkEquipment(String equipment) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается оборудование: "  + mediums.findBy(Condition.text("Оборудование:")).sibling(0).getText(), () -> {
            mediums.findBy(Condition.text("Оборудование:")).sibling(0).shouldHave(text(equipment));
        });
        System.out.println("Оборудование: " + mediums.findBy(Condition.text("Оборудование:")).sibling(0).getText());
    }

    public void checkPersonAddress(String clientAddress) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается Личные данные: "  + mediums.findBy(Condition.text("Личные данные:")).parent().sibling(0).getText(), () -> {
            mediums.findBy(Condition.text("Личные данные:")).parent().sibling(0).shouldHave(text(clientAddress));
        });
        System.out.println("Личные данные: " + mediums.findBy(Condition.text("Личные данные:")).parent().sibling(0).getText());
    }





}
