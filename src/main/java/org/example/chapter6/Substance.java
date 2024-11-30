package org.example.chapter6;

public interface Substance {
    void addSubstance(String name, double dosage);
    double calculateDosage(double weight, double factor);
    void researchSubstance();
    void changeStatus(String status);
    String getSubstanceInfo();
    void updateSubstanceInfo(String info);
}
