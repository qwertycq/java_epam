package chapter10.var2;

import chapter10.var2.transport.*;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TrainDataConnectorTest {

    private static final String TEST_FILE_PATH = "test_train_data.ser";
    private Train train;

    @BeforeEach
    void setUp() {
        Locomotive locomotive = new Locomotive("Дизель", 5000, 20000);
        train = new Train(locomotive);
        train.addCarriage(new PassengerCarriage(3, 100, 200));
        train.addCarriage(new PassengerCarriage(2, 80, 150));
        train.addCarriage(new BaggageCarriage(500));
    }

    @Test
    @DisplayName("Тест сохранения и загрузки объекта Train")
    void testSaveAndLoadTrain() throws Exception {
        TrainDataConnector.saveTrainToFile(train, TEST_FILE_PATH);

        // Загружаем объект Train из файла
        Train loadedTrain = TrainDataConnector.loadTrainFromFile(TEST_FILE_PATH);

        assertNotNull(loadedTrain, "Загруженный объект Train не должен быть null");
        assertEquals(train.getCarriages().size(), loadedTrain.getCarriages().size(),
                "Количество вагонов должно совпадать");
        assertEquals(train.getLocomotive().toString(), loadedTrain.getLocomotive().toString(),
                "Информация о локомотиве должна совпадать");

        Carriage originalCarriage = train.getCarriages().get(0);
        Carriage loadedCarriage = loadedTrain.getCarriages().get(0);
        assertEquals(originalCarriage.getPassengerCapacity(), loadedCarriage.getPassengerCapacity(),
                "Пассажирская вместимость должна совпадать");
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
