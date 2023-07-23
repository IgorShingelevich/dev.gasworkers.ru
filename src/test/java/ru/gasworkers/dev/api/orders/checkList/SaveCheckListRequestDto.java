package ru.gasworkers.dev.api.orders.checkList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCheckListRequestDto {
    @JsonProperty("order_id")
    public Integer orderId;
    @JsonProperty("check_list")
    public ArrayList<CheckList> checkList;

    public static SaveCheckListRequestDto successfulRequest(Integer orderId) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.builder().id(2).title("Фактически установленное оборудовании соответствует Проекту?").value(true).build());
        items.add(Item.builder().id(1).title("Документация на газоснабжение: Технические условия, Проект ВДГО в наличии?").value(true).build());
        items.add(Item.builder().id(3).title("Требуется ли замена котла/газовой плиты?").value(false).build());
        items.add(Item.builder().id(4).title("Надо ли легализовать самовольно установленное оборудование?").value(false).build());
        items.add(Item.builder().id(5).title("Опломбирован ли счетчик?").value(true).build());
        items.add(Item.builder().id(6).title("Требуется ли поверка счётчика и/или замена батарейки?").value(false).build());
        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(Item.builder().id(7).title("Есть ли протечки гидросистемы и потеря давления?").value(false).build());
        items2.add(Item.builder().id(8).title("Надо ли заменить/очистить фильтры (шламоуловитель) гидросистемы?").value(true).build());
        items2.add(Item.builder().id(9).title("Требуется ли подкачка/замена расширительных гидроаккумуляторов?").value(true).build());
        items2.add(Item.builder().id(10).title("Требуется ли перепроектирование и перемонтаж гидросистемы?").value(false).build());
        ArrayList<Item> items3 = new ArrayList<>();
        items3.add(Item.builder().id(11).title("Установлен ли стабилизатор напряжения ?").value(true).build());
        items3.add(Item.builder().id(12).title("Установлены ли датчики протечек и аварийных отсекателей водоснабжения").value(false).build());
        items3.add(Item.builder().id(13).title("Установлена ли погодозависимая автоматика отопления?").value(false).build());
        items3.add(Item.builder().id(14).title("Требуется ли настройка автоматики отопления").value(false).build());
        ArrayList<Item> items4 = new ArrayList<>();
        items4.add(Item.builder().id(15).title("Требуется ли обслуживание системы очистки воды и замена фильтров ?").value(false).build());
        items4.add(Item.builder().id(16).title("Требуется ли обслуживание/ (очистка, откачка) септика").value(false).build());
        ArrayList<Item> items5 = new ArrayList<>();
        items5.add(Item.builder().id(17).title("Есть ли в наличие свежие (менее года) Акты на обследование дымовых и вентканалов?").value(false).build());
        items5.add(Item.builder().id(18).title("Требуется ли обслуживание и очистка кондиционеров?").value(false).build());
        items5.add(Item.builder().id(19).title("Готов ли Заказчик  рассмотреть коммерческое предложение по любому из пунктов").value(true).build());
        ArrayList<Item> items6 = new ArrayList<>();
        items6.add(Item.builder().id(20).title("Электрик").value(false).build());
        items6.add(Item.builder().id(21).title("Сантехник").value(false).build());
        items6.add(Item.builder().id(22).title("Специалист по отоплению").value(false).build());
        items6.add(Item.builder().id(23).title("Кондиционерщик").value(false).build());
        items6.add(Item.builder().id(24).title("Проектировщик").value(false).build());
        ArrayList<CheckList> checkLists = new ArrayList<>();
        checkLists.add(CheckList.builder().id(1).title("ВДГО").items(items).build());
        checkLists.add(CheckList.builder().id(2).title("Гидросистема отопления").items(items2).build());
        checkLists.add(CheckList.builder().id(3).title("Электрика и автоматика").items(items3).build());
        checkLists.add(CheckList.builder().id(4).title("Водоснабжение и водоотведение").items(items4).build());
        checkLists.add(CheckList.builder().id(5).title("Кондиционирование и вентиляция").items(items5).build());
        checkLists.add(CheckList.builder().id(6).title("Каких бы Специалистов Заказчик хотел бы пригласить для выполнения работ").items(items6).build());
        return SaveCheckListRequestDto.builder()
                .orderId(orderId)
                .checkList(checkLists)
                .build();

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckList {
        public Integer id;
        public String title;
        public ArrayList<Item> items;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        public Integer id;
        public String title;
        public Boolean value;
    }
}
