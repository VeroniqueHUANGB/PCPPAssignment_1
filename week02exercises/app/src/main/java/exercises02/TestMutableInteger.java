// For week 2
// sestoft@itu.dk * 2014-08-25
// raup@itu.dk * 2021-09-03
package exercises02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMutableInteger {
    public static void main(String[] args) {
        final MutableInteger mi = new MutableInteger();
	Thread t = new Thread(() -> {
		while (mi.get() == 0)        // Loop while zero
		    {/* Do nothing*/ }
		System.out.println("I completed, mi = " + mi.get());
	});
	t.start();
	try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	mi.set(42);
	System.out.println("mi set to 42, waiting for thread ...");
	try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
	System.out.println("Thread t completed, and so does main");
    }
}

class MutableInteger {
    // WARNING: Not ready for usage by concurrent programs
    volatile private int value = 0;
	private Lock lock = new ReentrantLock();;
    public void set(int value) {
		// synchronized(this){
		// 	this.value = value;
		// }
		this.value = value;
    }
    public int get() {
		// lock.lock();
		// int res = value;
		// lock.unlock();
		// return res;
		// int res;
		// synchronized(this){
		// 	res = value;
		// }
		// return res;
		return value;
    }
}
