class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
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
                MainClass.finishPosition++;
                if (MainClass.finishPosition == 1) {
                    System.out.println("Победитель: " + c.getName());
                } else if (MainClass.finishPosition == 2) {
                    System.out.println("Второе место: " + c.getName());
                } else if (MainClass.finishPosition == 3) {
                    System.out.println("Третье место: " + c.getName());
                }
            }
            MainClass.lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
