package chapter6;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMedication implements ActiveSubstance, Medication {
    protected String name;
    protected Map<String, Double> substances = new HashMap<>();
    protected String status = "по рецепту";

    public AbstractMedication(String name) {
        this.name = name;
    }

    @Override
    public void addSubstance(String name, double concentration) {
        substances.put(name, concentration);
    }

    @Override
    public String getSubstanceInfo() {
        StringBuilder info = new StringBuilder();
        substances.forEach((substance, concentration) ->
                info.append(substance).append(": ").append(concentration).append(" mg\n")
        );
        return info.toString();
    }

    @Override
    public void updateSubstanceStatus(String status) {
        this.status = status;
    }

    @Override
    public abstract void calculateDosage(double weight, double age);

    @Override
    @ResearchRequired(description = "Essential for new substances")
    public void conductResearch() {
        System.out.println("Conducting research on " + name);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nStatus: " + status + "\nSubstances: " + getSubstanceInfo();
    }
}
