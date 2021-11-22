package gr.codelearn.showcase.thread;

public class RaceCondition implements Runnable{
    private int totalValue;

    public RaceCondition() {
        totalValue = 0;
    }

    public synchronized void increment(){
        try {
            Thread.sleep(10);
            totalValue++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " total value before increment is: " + totalValue);
        increment();
        System.out.println(Thread.currentThread().getName() + " total value after increment is: " + totalValue);
    }
}

class RaceConditionMain{
    public static void main(String[] args) {
        RaceCondition raceCondition = new RaceCondition();
        Thread thread1 = new Thread(raceCondition);
        Thread thread2 = new Thread(raceCondition);
        Thread thread3 = new Thread(raceCondition);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
