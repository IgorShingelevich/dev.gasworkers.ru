package ru.gasworkers.dev.pages.components.masterComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class CheckListMasterComponent extends BaseComponent {

        public CheckListMasterComponent(RoleBrowser browser) {
            super(browser);
        }


    ElementsCollection
    checkListSectionCollection = driver.$$("p.h4.w-100").as("Секции чек листа"),
    checklistItemCollection = driver.$$("ol.checklist li").as("Элементы чек листа"),
    checklistTitleCollection = driver.$$("ol.checklist li p").as("Заголовки элементов чек листа"),
    checklistRadioButtonItemCollection = driver.$$("input[type=radio]").as("Радио кнопки чек листа");

    public List<String> itemCollectionsTitleList() {
        List<String> titleList = new ArrayList<>();
        titleList.add("Фактически установленное оборудовании соответствует Проекту?");
        titleList.add("Документация на газоснабжение: Технические условия, Проект ВДГО в наличии");
        titleList.add("Требуется ли замена котла/газовой плиты?");
        titleList.add("Надо ли легализовать самовольно установленное оборудование?");
        titleList.add("Опломбирован ли счетчик?");
        titleList.add("Требуется ли поверка счётчика и/или замена батарейки?");
        titleList.add("Есть ли протечки гидросистемы и потеря давления?");
        titleList.add("Надо ли заменить/очистить фильтры (шламоуловитель) гидросистемы?");
        titleList.add("Требуется ли подкачка/замена расширительных гидроаккумуляторов?");
        titleList.add("Требуется ли перепроектирование и перемонтаж гидросистемы?");
        titleList.add("Установлен ли стабилизатор напряжения ?");
        titleList.add("Установлены ли датчики протечек и аварийных отсекателей водоснабжения");
        titleList.add("Установлена ли погодозависимая автоматика отопления?");
        titleList.add("Требуется ли настройка автоматики отопления");
        titleList.add("Требуется ли обслуживание системы очистки воды и замена фильтров ?");
        titleList.add("Требуется ли обслуживание/ (очистка, откачка) септика");
        titleList.add("Есть ли в наличие свежие (менее года) Акты на обследование дымовых и вентканалов?");
        titleList.add("Требуется ли обслуживание и очистка кондиционеров?");
        titleList.add("Готов ли Заказчик рассмотреть коммерческое предложение по любому из пунктов");
        titleList.add("Электрик");
        titleList.add("Сантехник");
        titleList.add("Специалист по отоплению");
        titleList.add("Кондиционерщик");
        titleList.add("Проектировщик");
        return titleList;
    }

    public void checkListItemsTitle() {
        stepWithRole("Убедиться, что все элементы чек листа соответствуют заголовкам", () -> {
            checklistTitleCollection.shouldHave(CollectionCondition.texts(itemCollectionsTitleList()));
            /*for (int i = 0; i < 23; i++) {
                switch (i) {
                    case 0:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 1:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 2:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 3:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 4:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 5:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 6:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 7:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 8:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 9:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 10:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 11:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 12:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 13:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 14:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 15:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 16:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 17:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                    case 18:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 19:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 20:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 21:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 22:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 23:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    case 24:
                        checklistTitleCollection.get(i).shouldHave(Condition.text(itemCollectionsTitleList().get(i)));
                        break;
                    }
                }*/
        });
    }

        public void checkFinishLoading() {
            stepWithRole("Убедиться, что компонент Чек-лист присутствует", () -> {
                stepWithRole("Убедиться, что чек лист загрузился", () -> {
                    checkListSectionCollection.should(CollectionCondition.size(6));
                    checklistRadioButtonItemCollection.should(CollectionCondition.size(48));
                    checkListItemsTitle();
                    //todo:default checklist state
                });
            });
        }

        public void isDisableRadioButtonsState(){
            stepWithRole("Убедиться, что все радио кнопки чек листа неактивны", () -> {
                checklistRadioButtonItemCollection.shouldHave(CollectionCondition.noneMatch("Неактивные радио кнопки", selenideElement -> driver.$(selenideElement).is(Condition.enabled)));
            });

        }

    public void isActiveRadioButtonsState() {
        stepWithRole("Убедиться, что все радио кнопки чек листа активны", () -> {
            checklistRadioButtonItemCollection.shouldHave(CollectionCondition.noneMatch("Активные радио кнопки", selenideElement -> driver.$(selenideElement).is(Condition.disabled)));
        });
    }
}
//todo: add test for checklist changes
