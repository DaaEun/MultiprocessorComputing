public class Lock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException{
        
        /*
            - while(isLocked) 루프는 '스핀 락'이라 부른다. 
            
            - isLocked가 true이면, lock()을 호출하는 쓰레드는 wait() 메소드 호출을 통해 대기 상태가 된다.

            - 쓰레드가 notify()가 호출되지 않았음에도 예상치 못하게 깨어나는 경우, isLocked의 값으로
            쓰레드가 다시 대기 상태로 들어가야 할지, 다음 동작을 수행해야 할지를 결정한다.

            - isLocked가 false이면, 쓰레드는 루프를 벗어나 isLocked를 true로 변경되어 Lock 인스턴스에 락이 걸렸음을 표시한다.
        */
        while(isLocked){
            wait();
        }
        isLocked = true;
    }

    /*
        - 크리티컬 섹션(lock()과 unlock() 사이)의 코드 실행을 완료한 쓰레드는 unlock()을 호출하여
        isLocked를 false로 변경하고, lock() 메소드에서 대기중인 쓰레드를 Runnable로 바꾼다.
    */
    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}

