public class Data {
    private final StringBuilder buffer;
    private final Lock lock = new Lock();
    
    public Data(int size) {
        this.buffer = new StringBuilder("#empty");
    }
    
    public String read() throws InterruptedException {
        lock.readLock();
        try {
            Thread.sleep(100);        
            return buffer.toString();
        } finally {
            lock.readUnlock();
        }
    }
    
    public void write(String v) throws InterruptedException {
        lock.writeLock();
        try {
            Thread.sleep(100);
            buffer.setLength(0);
            buffer.append(v);
        } finally {
            lock.writeUnlock();
        }
    }
}