package chapter6;

public class Tablet extends GenericDrug implements DrugForms {
    public Tablet(String name) {
        super(name);
    }

    @Override
    public void specificFeature() {
        System.out.println("Особенность таблетки: Принимать внутрь с водой.");
    }
}
