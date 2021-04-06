import java.util.Random;

public class Writer extends Thread {
    private static final Random random = new Random();
    private final Data data;
    private final String[] inputs;
    private int index = 0;
    
    public Writer(Data data, String[] inputs) {
        this.data = data;
        this.inputs = inputs;
    }
    
    public void run() {
        try {
            while(true) {
                String input = inputs[index];
                index = (index + 1) % inputs.length;
                data.write(input);
                Thread.sleep(random.nextInt(1000));
            }
        } catch(InterruptedException e) {
            //.
        }
    }    
}