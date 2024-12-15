package chapter6;

public class Solution extends AbstractMedication {
    public Solution(String name) {
        super(name);
    }

    @Override
    public void calculateDosage(double weight, double age) {
        double dosage = (weight * 5) / age;
        System.out.println("Рекомендуемая дозировка: " + dosage + " мл в день.");
    }

}