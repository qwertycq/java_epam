package chapter6;

// Класс для мазей
public class Ointment extends AbstractMedication {
    public Ointment(String name) {
        super(name);
    }

    @Override
    public void calculateDosage(double weight, double age) {
        System.out.println("Apply a thin layer to the affected area 2-3 times daily.");
    }
}
