package chapter10.var2.transport;

public class BaggageCarriage extends Carriage {
    private static final long serialVersionUID = 1L;

    public BaggageCarriage(int baggageCapacity) {
        super(0, 0, baggageCapacity);
    }
}