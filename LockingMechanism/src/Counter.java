// synchronized block 대신 Lock을 이용하여 작성
public class Counter {

    private Lock lock = new Lock();
    private int count = 0;

    public int inc(){
        lock.lock();
        // # lock 메소드
        // Lock 인스턴스에 락을 걸어 lock()을 호출하는 모든 쓰레드가 
        // unlock() 메소드가 호출될 때까지 Block되도록 한다.
        int newCount = ++count;
        lock.unlock();
        return newCount; 
    }
}
