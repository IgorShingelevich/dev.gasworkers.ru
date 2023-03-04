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
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается ФИО мастера: " + mediums.findBy(Condition.text("Мастер:")).getText(), () -> {
            mediums.findBy(Condition.text("Мастер:")).shouldHave(text(fullName));
        });
        System.out.println("Мастер: " + mediums.findBy(Condition.text("Мастер:")).getText());
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
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается дата заказа: "  + mediums.findBy(Condition.text("Дата заказа:")).getText(), () -> {
                mediums.findBy(Condition.text("Дата заказа:")).shouldHave(text(orderDate));
            });
            System.out.println("Дата заказа: " + mediums.findBy(Condition.text("Дата заказа:")).getText());
        }

        public void checkRightNowTimeOrderState() {
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается время заказа: "  + mediums.findBy(Condition.text("Время заказа:")).getText(), () -> {
                mediums.findBy(Condition.text("Выбранное время:")).shouldHave(text("Прямо сейчас"));
            });
            System.out.println("Время заказа: " + mediums.findBy(Condition.text("Время заказа:")).getText());
        }

        public void checkPriceOrder(String priceOrder) {
            stepWithRole("Убедиться, что в компоненте Детали заказа отображается цена заказа: "  + mediums.findBy(Condition.text("Цена заказа:")).getText(), () -> {
                mediums.findBy(Condition.text("Стоимость:")).shouldHave(text(priceOrder));
            });
            System.out.println("Цена заказа: " + mediums.findBy(Condition.text("Цена заказа:")).getText());
        }

    public void checkOrderType(OrderType orderType) {
        stepWithRole("Убедиться, что в компоненте  тип заказа  - Видеоконсультация", () -> {
            mediums.findBy(Condition.text("Тип заказа:")).shouldHave(text(orderType.toString()));
            System.out.println("Тип заказа:" + mediums.findBy(Condition.text("Тип заказа:")).text());
        });
        System.out.println("Тип заказа:" + mediums.findBy(Condition.text("Тип заказа:")).text());
    }

    public void checkEquipment(String equipment) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается оборудование: "  + mediums.findBy(Condition.text("Оборудование:")).getText(), () -> {
            mediums.findBy(Condition.text("Оборудование:")).shouldHave(text(equipment));
        });
        System.out.println("Оборудование: " + mediums.findBy(Condition.text("Оборудование:")).getText());
    }

    public void checkClientAddress(String clientAddress) {
        stepWithRole("Убедиться, что в компоненте Детали заказа отображается адрес клиента: "  + mediums.findBy(Condition.text("Личные данные:")).getText(), () -> {
            mediums.findBy(Condition.text("Личные данные:")).shouldHave(text(clientAddress));
        });
        System.out.println("Адрес клиента: " + mediums.findBy(Condition.text("Личные данные:")).getText());
    }





}
