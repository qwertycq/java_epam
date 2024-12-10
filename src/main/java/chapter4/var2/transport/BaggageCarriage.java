package chapter4.var2.transport;

public class BaggageCarriage extends Carriage {
    public BaggageCarriage(int baggageCapacity) {
        super(0, 0, baggageCapacity); // Багажный вагон не имеет пассажиров
    }
}
