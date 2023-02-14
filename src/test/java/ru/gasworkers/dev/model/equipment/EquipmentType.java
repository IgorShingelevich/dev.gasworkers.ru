package ru.gasworkers.dev.model.equipment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EquipmentType {
    GAS_BOILER("Газовый котел"),
    GAS_STOVE("Плита газовая"),
    OVEN("Духовой шкаф"),
    TANKLESS_GAS_WATER_HEATER("Проточный ёмкостный газовый водонагреватель"),
    GAS_CONVECTOR("Газовый конвектор"),
    INDIVIDUAL_GAS_CYLINDER_INSTALLATION_WO_GAS_STOVE("Индивидуальная газобаллонная установка (без газовой плиты)"),
    INDIVIDUAL_GAS_CYLINDER_INSTALLATION_W_CABINET_W_GAS_STOVE("Индивидуальная газобаллонная установка установленной в шкафу, с плитой газовой"),
    ELEMENT_OF_INDOOR_GAS_CONTROL_SYSTEM("Элемент системы контроля загазованности помещений"),
    GAS_HOLDER("Газгольдер"),
    GAS_METER("Счетчик газовый");

    private final String type;
    @Override
    public String toString() {
        return type;
    }
}