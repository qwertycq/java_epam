package org.example.chapter6;

public class ActiveSubstance implements Substance {
    private String name;
    private double dosage;
    private String status;
    private String info;

    public ActiveSubstance(String name, double dosage) {
        this.name = name;
        this.dosage = dosage;
        this.status = "Разрешено"; // Статус по умолчанию
        this.info = "Нет дополнительной информации";
    }

    @Override
    public void addSubstance(String name, double dosage) {
        this.name = name;
        this.dosage = dosage;
    }

    @Override
    public double calculateDosage(double weight, double factor) {
        return weight * factor * dosage;
    }

    @Override
    public void researchSubstance() {
        System.out.println("Исследование вещества: " + name);
    }

    @Override
    public void changeStatus(String status) {
        this.status = status;
    }

    @Override
    public String getSubstanceInfo() {
        return "Вещество: " + name + ", Дозировка: " + dosage + ", Статус: " + status;
    }

    @Override
    public void updateSubstanceInfo(String info) {
        this.info = info;
    }
}
