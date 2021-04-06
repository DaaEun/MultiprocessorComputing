// Runnable 인터페이스는 구현할 메소드가 run() 하나뿐인 함수형 인터페이스이다.
public class ThreadEX implements Runnable{

    int TestNum = 0;

    @Override
    public /* synchronized(동기화) 하나가 끝나야 실행됨 */ void run(){
        for(int i = 0; i < 10; i++){
            if(Thread.currentThread().getName().equals("A")){
                System.out.println("===============================");
                TestNum++;
            }

            System.out.println("ThreadName = " + Thread.currentThread().getName() + "\t TestNum = " + TestNum);

            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
