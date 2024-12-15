package chapter10.var2.transport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {
    private static final long serialVersionUID = 1L;
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
