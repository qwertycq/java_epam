// Вариант: 8 Очиров Б. Б762-2

package org.example.chapter6;

public class Main {
    public static void main(String[] args) {
        ActiveSubstance paracetamol = new ActiveSubstance("Парацетамол", 500);
        paracetamol.updateSubstanceInfo("Используется для снятия боли и температуры.");
        paracetamol.changeStatus("По рецепту");

        Tablet paracetamolTablet = new Tablet("Таблетка Парацетамола");
        paracetamolTablet.addActiveSubstance(paracetamol);
        paracetamolTablet.updateDrugInfo("Эффективно при слабой боли и температуре.");

        System.out.println(paracetamolTablet.getDrugInfo());
        paracetamolTablet.specificFeature();
    }
}
