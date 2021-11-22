package gr.codelearn.showcase.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThreadExample implements Runnable{
    private final String name;
    private final long sleepTime = 100;

    public SimpleThreadExample(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(name + " is starting");
        for (int i = 0;i < 10;i++){
            System.out.println(name + " iterates at " + i);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " finished!");
    }
}

class SimpleThreadWithStop implements Runnable{

    private final String name;
    private AtomicBoolean exit = new AtomicBoolean(false);

    public SimpleThreadWithStop(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is starting");
        while (!exit.get()){
            try {
                Thread.sleep(500);
                System.out.println("You cannot stop me!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ok, you can stop me!");
    }

    public  void stop(){
        exit.set(true);
    }
}

class SimpleThreadMain{
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SimpleThreadExample("Thread 1"));
        Thread thread2 = new Thread(new SimpleThreadExample("Thread 2"));
        Thread thread3 = new Thread(new SimpleThreadExample("Thread 3"));

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class SimpleThreadMainWithStop{
    public static void main(String[] args) {
        SimpleThreadWithStop ts = new SimpleThreadWithStop("Tread 1");
        Thread thread1 = new Thread(ts);

        thread1.start();

        try {
            Thread.sleep(5000);
            ts.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SimpleThreadMainWithJoin{
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SimpleThreadExample("Thread 1"));
        Thread thread2 = new Thread(new SimpleThreadExample("Thread 2"));
        Thread thread3 = new Thread(new SimpleThreadExample("Thread 3"));

        thread1.start();
        System.out.println("Current thread "+Thread.currentThread().getName()+" will start waiting");
        thread1.join();
        System.out.println("Join invoked by thread "+Thread.currentThread().getName());
        thread2.start();
        System.out.println("Current thread "+Thread.currentThread().getName()+" will start waiting for 1 sec");
        thread2.join(1000);
        System.out.println("Join invoked by thread "+Thread.currentThread().getName() + " after 1 sec passed");
        thread3.start();
    }
}
