// Вариант: 8 Очиров Б. Б762-2

package chapter6;

public class Main {
    public static void main(String[] args) {
        Ointment ointment = new Ointment("Лечебная мазь");
        ointment.addSubstance("Гидрокортизон", 1.0);
        ointment.addSubstance("Петролатум", 99.0);
        System.out.println(ointment);
        ointment.calculateDosage(70, 30);
        ointment.conductResearch();

        Tablet tablet = new Tablet("Таблетка от боли");
        tablet.addSubstance("Ибупрофен", 200.0);
        System.out.println(tablet);
        tablet.calculateDosage(70, 30);
        tablet.conductResearch();

        Solution solution = new Solution("Антисептический раствор");
        solution.addSubstance("Хлоргексидин", 0.5);
        System.out.println(solution);
        solution.calculateDosage(70, 30);
        solution.conductResearch();
    }
}
