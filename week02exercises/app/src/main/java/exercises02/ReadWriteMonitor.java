// For week 2
// raup@itu.dk * 01/09/2021
package exercises02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.*;

public class ReadWriteMonitor {
    private int readers         = 0;
    private boolean writer      = false;
    //private Lock lock           = new ReentrantLock();
    //private Condition condition = lock.newCondition();

    
    //////////////////////////
    // Read lock operations //
    //////////////////////////
    
    public void readLock() {
		synchronized(this){
			while(writer){
				try{wait();}
				catch(Exception e){}
			}
			readers++;
		}
    }

    public void readUnlock() {
		synchronized(this){
			readers--;
		}
    }

    
    ///////////////////////////
    // Write lock operations //
    ///////////////////////////

    public void writeLock() {
		synchronized(this) {
			writer = true;
			while(readers > 0 || writer){
				try{wait();}
				catch(Exception e){}
			}
			//writer = true;
		}
    }

    public void writeUnlock() {
		synchronized(this) {
			writer = false;
		}
    }
}
