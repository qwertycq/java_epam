package chapter10.var2.service;

import chapter10.var2.transport.Carriage;
import chapter10.var2.transport.Train;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    public int calculateTotalPassengers(Train train) {
        return train.getCarriages().stream()
                .mapToInt(carriage -> {
                    int passengers = carriage.getPassengerCapacity();
                    System.out.println("Число пассажиров в вагоне: " + passengers);
                    return passengers;
                })
                .sum();
    }

    public int calculateTotalBaggage(Train train) {
        return train.getCarriages().stream()
                .mapToInt(carriage -> {
                    int baggage = carriage.getBaggageCapacity();
                    System.out.println("Число багажа в вагоне: " + baggage);
                    return baggage;
                })
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
