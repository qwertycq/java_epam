package chapter6;

import java.util.ArrayList;
import java.util.List;

public class GenericDrug implements Drug {
    private String name;
    private String info;
    private List<Substance> activeSubstances;

    public GenericDrug(String name) {
        this.name = name;
        this.info = "Нет дополнительной информации";
        this.activeSubstances = new ArrayList<>();
    }

    @Override
    public void addActiveSubstance(Substance substance) {
        activeSubstances.add(substance);
    }

    @Override
    public void updateDrugInfo(String info) {
        this.info = info;
    }

    @Override
    public String getDrugInfo() {
        StringBuilder sb = new StringBuilder("Лекарство: " + name + "\n");
        sb.append("Информация: ").append(info).append("\nДействующие вещества:\n");
        for (Substance s : activeSubstances) {
            sb.append(s.getSubstanceInfo()).append("\n");
        }
        return sb.toString();
    }
}
