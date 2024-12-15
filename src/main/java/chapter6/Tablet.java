package chapter6;

public class Tablet extends AbstractMedication {
    public Tablet(String name) {
        super(name);
    }

    @Override
    public void calculateDosage(double weight, double age) {
        double dosage = (weight * 10) / age;
        System.out.println("Рекомендуемая дозировка: " + dosage + " мл в день.");
    }
}