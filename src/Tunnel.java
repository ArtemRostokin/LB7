import java.util.concurrent.Semaphore;

class Tunnel extends Stage {
    private final Semaphore semaphore;

    public Tunnel(Semaphore semaphore) {
        this.length = 80;
        this.description = "������� " + length + " ������";
        this.semaphore = semaphore;
    }

    @Override
    public void go(Car c) {
        try {
            MainClass.lock.lock();
            System.out.println(c.getName() + " ����� ����: " + description);
            MainClass.lock.unlock();
            Thread.sleep(length / c.getSpeed() * 1000);
            MainClass.lock.lock();
            System.out.println(c.getName() + " �������� ����: " + description);
            if (description.equals("������ 40 ������")) {
                synchronized (MainClass.class) {
                    MainClass.finishPosition++;
                    if (MainClass.finishPosition == 1) {
                        System.out.println("����������: " + c.getName());
                    } else if (MainClass.finishPosition == 2) {
                        System.out.println("������ �����: " + c.getName());
                    } else if (MainClass.finishPosition == 3) {
                        System.out.println("������ �����: " + c.getName());
                    }
                }
            }
            MainClass.lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}