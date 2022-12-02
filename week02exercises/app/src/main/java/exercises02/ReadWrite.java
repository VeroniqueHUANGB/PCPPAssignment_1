package exercises02;

public class ReadWrite {
    public ReadWrite() {
        ReadWriteMonitor m = new ReadWriteMonitor();
    
        final int numReadersWriters = 10;
    
        // start a reader
        for(int i = 0; i< numReadersWriters; i++){
        new Thread(() -> {
            		    
                m.readLock();
                System.out.println(" Reader " + Thread.currentThread().getId() + " started reading");
                // read
                System.out.println(" Reader " + Thread.currentThread().getId() + " stopped reading");
                m.readUnlock();
        }).start();
        if(i%3==0){
            new Thread(() -> {
                m.writeLock();
                System.out.println(" Writer " + Thread.currentThread().getId() + " started writing");
                // write
                System.out.println(" Writer " + Thread.currentThread().getId() + " stopped writing");
                m.writeUnlock();
            }).start();
        }
    }

        // start a writer
        new Thread(() -> {
            m.writeLock();
            System.out.println(" Writer " + Thread.currentThread().getId() + " started writing");
            // write
            System.out.println(" Writer " + Thread.currentThread().getId() + " stopped writing");
            m.writeUnlock();
        }).start();
        
    }
        
    
        public static void main(String[] args) {
        new ReadWrite();
        }
        
}
