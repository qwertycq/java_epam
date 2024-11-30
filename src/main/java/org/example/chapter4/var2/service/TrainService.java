package org.example.chapter4.var2.service;

import org.example.chapter4.var2.transport.Carriage;
import org.example.chapter4.var2.transport.Train;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    public int calculateTotalPassengers(Train train) {
        return train.getCarriages().stream()
                .mapToInt(Carriage::getPassengerCapacity)
                .sum();
    }

    public int calculateTotalBaggage(Train train) {
        return train.getCarriages().stream()
                .mapToInt(Carriage::getBaggageCapacity)
                .sum();
    }

    public void sortCarriagesByComfort(Train train) {
        train.getCarriages().sort(Comparator.comparingInt(Carriage::getComfortLevel));
    }

    public List<Carriage> findCarriagesByPassengerRange(Train train, int min, int max) {
        return train.getCarriages().stream()
                .filter(c -> c.getPassengerCapacity() >= min && c.getPassengerCapacity() <= max)
                .collect(Collectors.toList());
    }
}
