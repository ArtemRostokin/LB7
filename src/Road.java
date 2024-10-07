class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "������ " + length + " ������";
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
                MainClass.finishPosition++;
                if (MainClass.finishPosition == 1) {
                    System.out.println("����������: " + c.getName());
                } else if (MainClass.finishPosition == 2) {
                    System.out.println("������ �����: " + c.getName());
                } else if (MainClass.finishPosition == 3) {
                    System.out.println("������ �����: " + c.getName());
                }
            }
            MainClass.lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
