// Вариант: 8 Очиров Б. Б762-2

package chapter6;

public class Main {
    public static void main(String[] args) {
        // Создаем мазь
        Ointment ointment = new Ointment("Healing Ointment");
        ointment.addSubstance("Hydrocortisone", 1.0);
        ointment.addSubstance("Petrolatum", 99.0);
        System.out.println(ointment);
        ointment.calculateDosage(70, 30);
        ointment.conductResearch();

        // Создаем таблетки
        Tablet tablet = new Tablet("Pain Relief Tablet");
        tablet.addSubstance("Ibuprofen", 200.0);
        System.out.println(tablet);
        tablet.calculateDosage(70, 30);
        tablet.conductResearch();

        // Создаем раствор
        Solution solution = new Solution("Antiseptic Solution");
        solution.addSubstance("Chlorhexidine", 0.5);
        System.out.println(solution);
        solution.calculateDosage(70, 30);
        solution.conductResearch();
    }
}
