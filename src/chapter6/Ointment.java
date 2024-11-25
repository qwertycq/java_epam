package chapter6;

public class Ointment extends GenericDrug implements DrugForms {
    public Ointment(String name) {
        super(name);
    }

    @Override
    public void specificFeature() {
        System.out.println("Особенность мази: Наносить на кожу.");
    }
}
