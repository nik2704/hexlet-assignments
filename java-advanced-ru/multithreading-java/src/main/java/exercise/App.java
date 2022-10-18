package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        maxThread.start();

        MinThread minThread = new MinThread(numbers);
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

        return Map.of("min", minThread.getMinValue(), "max", maxThread.getMaxValue());
    }
    // END
}
