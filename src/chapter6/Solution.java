package chapter6;

public class Solution extends GenericDrug implements DrugForms {
    public Solution(String name) {
        super(name);
    }

    @Override
    public void specificFeature() {
        System.out.println("Особенность раствора: Использовать для инъекций или питья.");
    }
}
