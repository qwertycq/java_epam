// Вариант: 8 Очиров Б. Б762-2

package chapter6;

public class Main {
    public static void main(String[] args) {
        // Создаем действующее вещество
        ActiveSubstance paracetamol = new ActiveSubstance("Парацетамол", 500);
        paracetamol.updateSubstanceInfo("Используется для снятия боли и температуры.");
        paracetamol.changeStatus("По рецепту");

        // Создаем лекарство
        Tablet paracetamolTablet = new Tablet("Таблетка Парацетамола");
        paracetamolTablet.addActiveSubstance(paracetamol);
        paracetamolTablet.updateDrugInfo("Эффективно при слабой боли и температуре.");

        // Вывод информации
        System.out.println(paracetamolTablet.getDrugInfo());
        paracetamolTablet.specificFeature();
    }
}
