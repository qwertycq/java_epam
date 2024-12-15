package chapter6;

public class Ointment extends AbstractMedication {
    public Ointment(String name) {
        super(name);
    }

    @Override
    public void calculateDosage(double weight, double age) {
        System.out.println("Наносите тонким слоем на пораженную область 2-3 раза в день.");
    }
}
