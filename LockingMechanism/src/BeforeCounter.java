public class BeforeCounter {
        
    private int count = 0;

    public int inc(){
        synchronized(this){
            return ++count; 
            // 이 시점에에 오직 한 쓰레드에 의해서만 수행될 수 있음을 보장한다.
        }
    }
}
