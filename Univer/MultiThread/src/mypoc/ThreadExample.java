package mypoc;

public class ThreadExample implements Runnable {
    Thread thread;

    public ThreadExample(String name) {
        this.thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " - запуск");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + thread.getName() + ", счетчик:" + count);
            }
        } catch (InterruptedException ехс) {
            System.out.println(thread.getName() + " - прерван");
            System.out.println(thread.getName() + " завершение");
        }
    }

    public static class UseThreadImproved {
        public static void main(String[] args) {
            System.out.println("Запуск основного потока");

            ThreadExample mt1 = new ThreadExample("Child #1");
            ThreadExample mt2 = new ThreadExample("Child #2");
            ThreadExample mt3 = new ThreadExample("Child #3");

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
