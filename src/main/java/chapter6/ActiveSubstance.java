package chapter6;

// Интерфейс для работы с активным веществом
public interface ActiveSubstance {
    void addSubstance(String name, double concentration);
    String getSubstanceInfo();
    void updateSubstanceStatus(String status); // запрещенное, по рецепту, разрешенное
}

