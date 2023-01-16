package model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public enum ClientOrderType {
    MAINTENANCE("Заключение договора на ТО", "Инструкция"),
    REPAIR("Ремонт", "Инструкция по Ремонту"),
    VIDEO("Видеоконсультация", "Инструкция по заказу видеоконсультации");

    private final String description;
    @Getter
    private final String expectedTitle;

    public List<String> getExpectedStepSequence() {
        switch (this) {
            case MAINTENANCE:
                return List.of("Выберите объект или создайте новый",
                        "Выберите сервисную компанию — исполнителя",
                        "Выберите дату приезда мастера, оплатите выезд и дождитесь звонка от диспетчера",
                        "После проведения ТО подпишите акт и оплатите счёт в личном кабинете");
            case REPAIR:
                return List.of("Выберите объект или создайте новый",
                        "Укажите неисправное оборудование",
                        "Опишите поломку, приложите видео/фото неисправного оборудования",
                        "Разместите заказ, ожидайте предложения сервисных компаний");
            case VIDEO:
                return List.of("Укажите неисправное оборудование",
                        "Выберите мастера который проведет консультацию сразу же или в определенное время.",
                        "Опишите проблему или снимите видео",
                        "Оплатите консультацию. Помните, вы всегда можете вернуть деньги обратно.",
                        "В назначенное время вам придет смс со ссылкой на видеоконсультацию");
            default:
                throw new RuntimeException("Enum " + name() + " not supported");
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
