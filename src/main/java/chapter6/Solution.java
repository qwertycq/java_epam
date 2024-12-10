package chapter6;

// Класс для растворов
public class Solution extends AbstractMedication {
    public Solution(String name) {
        super(name);
    }

    @Override
    public void calculateDosage(double weight, double age) {
        double dosage = (weight * 5) / age;
        System.out.println("Recommended dosage: " + dosage + " ml per day.");
    }
}