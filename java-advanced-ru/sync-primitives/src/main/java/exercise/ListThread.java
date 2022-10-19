package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {

    SafetyList resource;

    ListThread(SafetyList resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Random random = new Random();
            int num = random.nextInt(1000);

            resource.add(num);
        }

    }
}
// END
