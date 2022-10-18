package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;

    private int minValue;

    public MinThread(int[] numbersValues) {
        numbers = Arrays.copyOf(numbersValues, numbersValues.length);
    }

    @Override
    public void run() {
        int minV = this.numbers[0];

        for (int i = 1; i < this.numbers.length; i++) {
            if (this.numbers[i] < minV) {
                minV = this.numbers[i];
            }
        }

        this.minValue = minV;
    }

    public int getMinValue() {
        return this.minValue;
    }
}
// END
