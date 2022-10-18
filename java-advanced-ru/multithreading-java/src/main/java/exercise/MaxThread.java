package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;
    private int maxValue;

    public MaxThread(int[] numbersValues) {
        numbers = Arrays.copyOf(numbersValues, numbersValues.length);
    }

    @Override
    public void run() {
        int maxV = this.numbers[0];

        for (int i = 1; i < this.numbers.length; i++) {
            if (this.numbers[i] > maxV) {
                maxV = this.numbers[i];
            }
        }

        this.maxValue = maxV;
    }

    public int getMaxValue() {
        return this.maxValue;
    }
}
// END
