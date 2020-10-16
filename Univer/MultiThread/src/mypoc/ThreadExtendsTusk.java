package mypoc;


public class ThreadExtendsTusk extends Thread {

    public ThreadExtendsTusk(String name) {
        super(name);
        start();
    }

    @Override
    public void run() {
        System.out.println(getName() + " - запуск");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + getName() + ", счетчик:" + count);
            }
        } catch (InterruptedException ехс) {
            System.out.println(getName() + " - прерван");
            System.out.println(getName() + " завершение");
        }
    }

    public static class UseThreadImproved {
        public static void main(String[] args) {
            System.out.println("Запуск основного потока");

            ThreadExtendsTusk mt = new ThreadExtendsTusk("Child #2");

            for (int i = 0; i < 50; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Прерывание основного потока");
                }
            }
            System.out.println("Завершение основного потока");
        }
    }

}
