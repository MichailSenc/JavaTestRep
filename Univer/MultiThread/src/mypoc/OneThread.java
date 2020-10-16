package mypoc;

public class OneThread {
    public static void main(String[] args) {
        Thread thread;

        thread = Thread.currentThread();
        System.out.println("Имя основного потока: " + thread.getName());
        System.out.println("Приоритет: " + thread.getPriority());
        System.out.println();
        System.out.println("Установка имени и приоритета\n");
        thread.setName("Thread #1");
        thread.setPriority(Thread.NORM_PRIORITY + 3);
        System.out.println("Новое имя основного потока: " + thread.getName());
        System.out.println("Новый приоритет: " + thread.getPriority());
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
