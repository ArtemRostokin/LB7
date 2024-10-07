import java.util.concurrent.Semaphore;

class Tunnel extends Stage {
    private final Semaphore semaphore;

    public Tunnel(Semaphore semaphore) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = semaphore;
    }

    @Override
    public void go(Car c) {
        try {
            MainClass.lock.lock();
            System.out.println(c.getName() + " начал этап: " + description);
            MainClass.lock.unlock();
            Thread.sleep(length / c.getSpeed() * 1000);
            MainClass.lock.lock();
            System.out.println(c.getName() + " закончил этап: " + description);
            if (description.equals("Дорога 40 метров")) {
                synchronized (MainClass.class) {
                    MainClass.finishPosition++;
                    if (MainClass.finishPosition == 1) {
                        System.out.println("Победитель: " + c.getName());
                    } else if (MainClass.finishPosition == 2) {
                        System.out.println("Второе место: " + c.getName());
                    } else if (MainClass.finishPosition == 3) {
                        System.out.println("Третье место: " + c.getName());
                    }
                }
            }
            MainClass.lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}