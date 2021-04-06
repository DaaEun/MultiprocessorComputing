// 	https://coding-factory.tistory.com/279 에서 참고한 예제이다.
class ATM implements Runnable {
	
    private long money = 10000;

    public void run() {
		synchronized (this) {
				
			for (int i = 0; i < 10; i++) {
				notify();
				try {
					wait();

					Thread.sleep(1000);
					// System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (getDepositeMoney() <= 0)	break;
				withDraw(1000);
			}
		}
	}

	public void withDraw(long howMuch) {
		if (getDepositeMoney() > 0) {
			money -= howMuch;
			System.out.print(Thread.currentThread().getName() + "'s ");
			System.out.printf("잔액 : %,d 원 %n", getDepositeMoney());
		} 
		else {
			System.out.print(Thread.currentThread().getName() + "'s ");
			System.out.println("잔액이 부족합니다.");
		}
	}

	public long getDepositeMoney() {
		return money;
	}
}