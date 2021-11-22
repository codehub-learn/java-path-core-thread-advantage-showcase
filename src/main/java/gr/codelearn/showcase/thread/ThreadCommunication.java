package gr.codelearn.showcase.thread;

public class ThreadCommunication {
    private int balance = 100;

    public synchronized void withdraw(int amount){
        if (balance < amount){
            System.out.println("You don't have money( "+balance +"). Waiting for deposit.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Balance has now sufficient amount of money for withdrawal.");
        balance -= amount;
        System.out.println("Current balance: "+ balance);
    }

    public synchronized void deposit(int amount){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance += amount;
        System.out.println("Successful deposit("+amount+"). Current balance is: " + balance);
        notifyAll();
    }
}

class ThreadCommunicationMain{
    public static void main(String[] args) {
        ThreadCommunication tc = new ThreadCommunication();
        new Thread(()-> tc.withdraw(150)).start();
        new Thread(()-> tc.deposit(60)).start();
    }
}
