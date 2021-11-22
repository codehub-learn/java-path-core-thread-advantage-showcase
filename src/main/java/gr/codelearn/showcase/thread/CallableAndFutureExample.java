package gr.codelearn.showcase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFutureExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future1 = executorService.submit(new MyCallable("Callable 1"));

        while (!future1.isDone()){
            System.out.println("Waiting for heavy calculation to end!");
        }

        try {
            System.out.println(future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("This will be printed after the get()");

        executorService.shutdown();

        ExecutorService executorServiceFixedThreadPool = Executors.newFixedThreadPool(4);
        List<Future<String>> futureList = new ArrayList<>();
        futureList.add(executorServiceFixedThreadPool.submit(new MyCallable("Callable 2")));
        futureList.add(executorServiceFixedThreadPool.submit(new MyCallable("Callable 3")));
        futureList.add(executorServiceFixedThreadPool.submit(new MyCallable("Callable 4")));
        futureList.add(executorServiceFixedThreadPool.submit(new MyCallable("Callable 5")));

        futureList.get(1).cancel(true);
        for (Future<String> stringFuture : futureList) {
            if (!stringFuture.isCancelled()){
                try {
                    System.out.println(stringFuture.get(6, TimeUnit.SECONDS));
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }

        executorServiceFixedThreadPool.shutdown();

    }
}

class MyCallable implements Callable<String> {
    private final String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Callable is starting to make some heavy calculations");
        Thread.sleep(5000);
        return name + " called to make these heavy calculations";
    }
}
