import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 20;
    private static final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT);
    private static final CyclicBarrier finishBarrier = new CyclicBarrier(CARS_COUNT + 1);
    private static final Semaphore tunnelSemaphore = new Semaphore(4);
    public static volatile int finishPosition = 0;
    public static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(tunnelSemaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 2 + (int) (Math.random() * 80), startBarrier, finishBarrier);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            finishBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}









