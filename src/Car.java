import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;
    private final CyclicBarrier startBarrier;
    private final CyclicBarrier finishBarrier;

    static {
        CARS_COUNT = 0;
    }

    public Car(Race race, int speed, CyclicBarrier startBarrier, CyclicBarrier finishBarrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.startBarrier = startBarrier;
        this.finishBarrier = finishBarrier;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (Stage stage : race.getStages()) {
            stage.go(this);
        }
        try {
            finishBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
