public class SingleThreadEx2 implements Runnable {
    
    private int[] temp;

    public SingleThreadEx2(){
        temp = new int[10];
        for(int start = 0; start < temp.length; start++){
            temp[start] = start;
        }
    }

    @Override
    public void run(){
        for(int start : temp){
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("스레드 이름 : " + Thread.currentThread().getName());
            System.out.println("temp value : " + start);
        }
    }
    public static void main(String[] args) throws Exception {
        
        SingleThreadEx2 ct = new SingleThreadEx2();

        // Thread(Runnable target, String name)
        Thread t = new Thread(ct, "첫번째");

        t.start();
    }
}
