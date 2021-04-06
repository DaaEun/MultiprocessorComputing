public class Main {
    public static void main(String[] args) {
        Data data = new Data(10);
        
        new Reader(data).start();
        new Reader(data).start();
        new Reader(data).start();
        
        String[] weeks = {"월", "화", "수", "목", "금", "토", "일"};
        new Writer(data, weeks).start();
        
        String[] numbers = {"ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
        new Writer(data, numbers).start();
        
        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        new Writer(data, digits).start();
    }
}