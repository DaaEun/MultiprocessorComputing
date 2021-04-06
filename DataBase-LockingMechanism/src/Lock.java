public class Lock {
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private boolean preferReader = false;
    
    private int writingWriters = 0;
    private int waitingWriters = 0;
    private boolean preferWriter = true;
    
    public synchronized void readLock() throws InterruptedException {
        waitingReaders++;
        try {
            while(writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                wait();
            }
        } finally {
            waitingReaders--;
        }
        
        readingReaders++;
    }
    
    public synchronized void readUnlock() {
        readingReaders--;
        preferWriter = true;
        preferReader = false;
        notifyAll();
    }
    
    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while(readingReaders > 0 || writingWriters > 0 || (preferReader && waitingReaders > 0)) {
                wait();
            }
            
        } finally {
            waitingWriters--;
        }
        
        writingWriters++;
    }
    
    public synchronized void writeUnlock() {
        writingWriters--;
        preferWriter = false;
        preferReader = true;
        notifyAll();
    }
}
