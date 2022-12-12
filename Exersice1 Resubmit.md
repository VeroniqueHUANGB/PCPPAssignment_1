### Exercise 1.2

1. ```
   // part of the output
   --|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-||--|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-||--||--|-|-|-|-|-|-
   ```

2. as you can see from the above, there can be some waving faults "--|", this happened when the first thread is sleeping, then the program switch to the second thread.

3. ```java
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
   ```

   This code is correct because I add lock before print "-" and release that lock after print "|", so these 2 actions become an atomic action, thus it is not possible for incorrect patterns.

### Exercise 1.3

1. ```java
   public class Turnstile extends Thread {
   	public void run() {
   	    for (int i = 0; i < PEOPLE; i++) {
   			//counter++;
   
   			//check count first
   			lock.lock();
   			try{
   				if(counter < MAX_PEOPLE_COVID) {
   					counter++;
   				}
   			} finally{
   				lock.unlock();
   			}
   	    }	    
   	}
       }
   
   // output in the terminal
   15000 people entered
   ```

2. The result will always be 15000, because we added lock before read and release the same lock after write counter. This makes sure that there won't have 2 threads reads the same value 14999, and add counter twice.

### Exercise 1.4

1. Included in Goetz but not in notes:Convenience

   Included in the notes but not in Goetz:Hidden

2. Inherent:Downloading a file. Starting a download in the background and coming back later to use the file.

   Exploitation: Using virtual machine on one's own computer.

   Hidden: Different transactions read data from one database.