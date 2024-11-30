package org.example.chapter4.var2.transport;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private List<Carriage> carriages;

    public Train() {
        this.carriages = new ArrayList<>();
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }
}
