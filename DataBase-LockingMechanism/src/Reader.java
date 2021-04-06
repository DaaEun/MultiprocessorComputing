public class Reader extends Thread {
    private final Data data;
    
    public Reader(Data data) {
        this.data = data;
    }
    
    public void run() {
        try {
            while(true) {
                String v = data.read();
                System.out.println(Thread.currentThread().getName() + " -> " + v);
            }
        } catch(InterruptedException e) {
            //.
        }
    }
}