package chapter4.var2.transport;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private List<Carriage> carriages;
    private Locomotive locomotive;

    public Train(Locomotive locomotive) {
        this.locomotive = locomotive;
        this.carriages = new ArrayList<>();
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }
}
