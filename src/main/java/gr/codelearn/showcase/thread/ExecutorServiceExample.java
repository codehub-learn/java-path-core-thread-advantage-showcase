package gr.codelearn.showcase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        Task task1 = new Task("Task 1");

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(task1);
//        executorService.execute(()->{
//            System.out.println("Doing something important!");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Finished that important thing");
//        });
//        executorService.shutdown();

        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");
        Task task4 = new Task("Task 4");

        ExecutorService executorServiceFixedThreadPool = Executors.newFixedThreadPool(2);
        executorServiceFixedThreadPool.execute(task2);
        executorServiceFixedThreadPool.execute(task3);
        executorServiceFixedThreadPool.execute(task4);
        executorServiceFixedThreadPool.shutdown();
    }
}

class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is starting");
        System.out.println("Initiating database connection!");

        try {
            Thread.sleep(2000);
            System.out.println("Retrieve information from the database!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Information was retrieved, closing connection!");
    }
}
