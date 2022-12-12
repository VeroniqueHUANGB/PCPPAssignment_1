package exercises01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class exercise1_2 {
    Lock lock = new ReentrantLock();
    private class Printer extends Thread{
        public void print() {
            // original code
            // System.out.print("-");
            // try { Thread.sleep(50); } catch (InterruptedException e) { }
            // System.out.print("|");

            //ReentrantLock
            lock.lock();
            try {
                System.out.print("-");
                try { Thread.sleep(50); } catch (InterruptedException e) { }
                System.out.print("|");
            } finally {
                lock.unlock();
            }
        }

        public Printer() { }
    }

    public static void main(String[] args) {
        exercise1_2 e = new exercise1_2();
        exercise1_2.Printer p = e.new Printer();


        Thread t1 = new Thread( () -> {
            while(true) {
                p.print();
            }
        });

        Thread t2 = new Thread( () -> {
            while(true) {
                p.print();
            }
        });

        t1.start(); t2.start();

        try { t1.join(); t2.join(); }
	    catch (InterruptedException exn) { 
	    System.out.println("Some thread was interrupted");
	    }


    }


}
