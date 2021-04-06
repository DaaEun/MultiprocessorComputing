# DataBase 접근하기 예제

#### 출처 : _[Read-Write Lock 패턴](http://www.gisdeveloper.co.kr/?p=10312)_

*** 


### 문제상황

    Database에서 어떤 테이블이 있다고 하자. 이 테이블은 다수의 클라이언트에서 동시에 읽고 쓰이는 대상이다. 어떤 클라이언트는 이 테이블에 데이터를 쓰고, 어떤 또 다른 클라이언트는 이 테이블에서 데이터를 읽는다. 그런데 만약 2개의 클라이언트에서 특정 레코드 데이터를 동시에 각각 쓰고 읽기를 수행하면 읽는 쓰레드 쪽에서는 망가진 데이터를 읽을 수 있다. 또한 만약 2개의 클라이언트에서 동시에 특정 레코드 데이터를 쓰려고 할때 망가진 데이터가 저장될 수 있다. 문제가 없는 경우는 2개의 클라이언트가 동시에 특정 레코드의 데이터를 읽을 때 뿐이다. 이 패턴은 어떤 데이터에 대해 동시에 읽고 쓸때의 상황에서, 또는 동시에 데이터를 쓰는 상황에서 문제가 발생하지 않도록 해주는데 목적이 있다.

<img src="http://www.gisdeveloper.co.kr/wp-content/uploads/2020/10/read-write-lock.png" width="60%" height="auto" alt="read-write-lock.png"></img>


### 실행결과

    Thread-1 -> #empty
    Thread-0 -> #empty
    Thread-2 -> #empty
    Thread-1 -> 월
    Thread-2 -> 월
    Thread-0 -> 월
    Thread-2 -> ONE
    Thread-0 -> ONE
    Thread-1 -> ONE
    Thread-1 -> 1
    Thread-2 -> 1
    Thread-0 -> 1
    Thread-1 -> 1
    Thread-2 -> 1
    Thread-0 -> 1
    Thread-1 -> 1
    Thread-2 -> 1
    Thread-0 -> 1
    Thread-0 -> 2
    Thread-2 -> 2
    Thread-1 -> 2
    Thread-2 -> TWO
    Thread-1 -> TWO
    Thread-0 -> TWO
    Thread-2 -> 화
    Thread-0 -> 화
    Thread-1 -> 화
    Thread-2 -> 화
    Thread-0 -> 화
    Thread-1 -> 화
    Thread-1 -> 3
    Thread-0 -> 3
    Thread-2 -> 3
    Thread-0 -> 수
    Thread-1 -> 수
    Thread-2 -> 수
    Thread-1 -> 4
    Thread-2 -> 4
    Thread-0 -> 4
    Thread-2 -> THREE
    Thread-1 -> THREE
    Thread-0 -> THREE
    Thread-2 -> THREE
    Thread-1 -> THREE
    Thread-0 -> THREE
    Thread-2 -> THREE
    Thread-1 -> THREE
    Thread-0 -> THREE
    Thread-2 -> 5
    Thread-1 -> 5
    Thread-0 -> 5
    Thread-2 -> 목
    Thread-0 -> 목
    Thread-1 -> 목
    Thread-2 -> 목
    Thread-0 -> 목
    Thread-1 -> 목
    Thread-2 -> 금
    Thread-1 -> 금
    Thread-0 -> 금
    Thread-0 -> 6
    Thread-1 -> 6
    Thread-2 -> 6
    Thread-1 -> FOUR
    Thread-2 -> FOUR
    Thread-0 -> FOUR
    Thread-2 -> FOUR
    Thread-1 -> FOUR
    Thread-0 -> FOUR
    Thread-2 -> FOUR
    Thread-0 -> FOUR
    Thread-1 -> FOUR
    Thread-2 -> FOUR
    Thread-0 -> FOUR
    Thread-1 -> FOUR
    Thread-2 -> FOUR
    Thread-0 -> FOUR
    Thread-1 -> FOUR
    Thread-0 -> 토
    Thread-2 -> 토
    Thread-1 -> 토
    Thread-2 -> 7
    Thread-0 -> 7
    Thread-1 -> 7
    Thread-0 -> FIVE
    Thread-1 -> FIVE
    Thread-2 -> FIVE
    Thread-1 -> 8
    Thread-0 -> 8
    Thread-2 -> 8
    Thread-1 -> 8
    Thread-0 -> 8
    Thread-2 -> 8
    Thread-2 -> SIX
    Thread-1 -> SIX
    Thread-0 -> SIX
    Thread-1 -> 일
    Thread-2 -> 일
    Thread-0 -> 일
    Thread-2 -> 9
    Thread-0 -> 9
    Thread-1 -> 9
    Thread-2 -> 9
    Thread-0 -> 9
    Thread-1 -> 9
    Thread-0 -> 월
    Thread-1 -> 월
    Thread-2 -> 월
    Thread-1 -> 1
    Thread-0 -> 1
    Thread-2 -> 1
    Thread-0 -> SEVEN
    Thread-1 -> SEVEN
    Thread-2 -> SEVEN
    Thread-1 -> 2
    Thread-0 -> 2
    Thread-2 -> 2
    Thread-1 -> 2
    Thread-0 -> 2
    Thread-2 -> 2
    Thread-0 -> 화
    Thread-1 -> 화
    Thread-2 -> 화
    Thread-2 -> EIGHT
    Thread-1 -> EIGHT
    Thread-0 -> EIGHT
    Thread-2 -> EIGHT
    Thread-1 -> EIGHT
    Thread-0 -> EIGHT
    Thread-2 -> EIGHT
    Thread-1 -> EIGHT
    Thread-0 -> EIGHT
    Thread-2 -> EIGHT
    Thread-1 -> EIGHT
    Thread-0 -> EIGHT
    Thread-2 -> EIGHT
    Thread-1 -> EIGHT
    Thread-0 -> EIGHT
    Thread-0 -> 3
    Thread-2 -> 3
    Thread-1 -> 3
    Thread-0 -> NINE
    Thread-1 -> NINE
    Thread-2 -> NINE
    Thread-0 -> 4
    Thread-1 -> 4
    Thread-2 -> 4
    Thread-2 -> 수
    Thread-0 -> 수
    Thread-1 -> 수
    Thread-2 -> 수
    Thread-0 -> 수
    Thread-1 -> 수
    Thread-2 -> 수
    Thread-0 -> 수
    Thread-1 -> 수
    Thread-0 -> 수
    Thread-2 -> 수
    Thread-1 -> 수
    Thread-2 -> ONE
    Thread-0 -> ONE
    Thread-1 -> ONE
    Thread-1 -> 목
    Thread-0 -> 목
    Thread-2 -> 목
    .
    .
    .

***


## 코드 설명

_스스로의 해석입니다. 잘못된 내용 발견시, 즉각적인 피드벡 부탁드립니다._

### Main 클래스

    1. 'data' 객체 참조 변수의 선언과 Data 인스턴스의 생성을 동시에 한다.
    
    2. data 객체가 Reader(데이터를 읽는 쓰레드)를 통해 읽기 접근을 요청한다. 총 3개의 쓰레드가 요청한다.
    
    3. weeks 배열과 하나의 data 객체가 Writer(데이터를 쓰는 쓰레드)를 통해 쓰기 접근을 요청한다.
    
    4. numbers 배열과 하나의 data 객체가 Writer를 통해 쓰기 접근을 요청한다.
    
    5. digits 배열과 하나의 data 객체가 Writer를 통해 쓰기 접근을 요청한다.
   
### Reader 클래스
어떤 데이터를 읽는 쓰레드

    1. Thread 클래스를 상속받는다. 부모의 메소드와 변수를 그대로 사용할 수 있으며, 오버라이딩 할 필요 없이 부모에 구현되어있는 것을 직접 사용 가능하다.
    
    2. while(true) 즉, 읽기 접근이 가능한 동안 루프문을 실행한다.
    
    3. 루프문은 Thread.currentThread().getName() + " -> " + data.read() 을 출력한다.
   
### writer 클래스
어떤 데이터를 쓰는 쓰레드

    1. Thread 클래스를 상속받는다.
   
    2. 난수를 만들어주는 클래스 Random의 기본 생성자를 생성한다.
    
    3. while(true) 즉, 쓰기 접근이 가능한 동안 루프문을 실행한다.
    
    4. 루프문은 inputs 배열을 차례대로 data.write(input) 실행한다. 또한 0 ~ 1000 ms 사이의 임의의 시간동안 sleep()에 의해 Block 상태가 된다. 지정된 시간이 지나면 Runnable 상태로 전환한다.
   
### Data 클래스
읽거나 쓰는 대상이 되는 데이터를 담고 있는 클래스

    1. 문자열 연산이 많을 때 두 클래스를 사용하지만 멀티 쓰레드 환경에서는 StringBuffer를 사용하면 좋다. 따라서 문자열을 저장하고 관리하는 클래스 중 StringBuffer을 이용하여 buffer을 선언한다.
    
    2. 'lock' 객체 참조 변수의 선언과 Lock 인스턴스의 생성을 동시에 한다.
   
    3. Data() 인스턴스가 선언되면, buffer 에 "#empty" 문자열을 대입한다. 

    4. read() 메소드에서 lock.readLock() 호출하여 읽기 접근을 요청한 쓰레드가 readUnlock() 메소드가 호출될때 까지 Block 상태가 된다. 
    (읽기 접근이 허용되면 - 잘 모르겠음) 쓰레드는 sleep()에 의해 100ms 동안 Block 상태가 된다. 
    시간이 지나면, buffer를 return한다. 
    마지막에 lock.readUnlock() 호출하여 읽기 접근을 허용한다.

    5. write(String v) 메소드에서 lock.writeLock() 호출하여 쓰기 접근을 요청한 쓰레드가 writeUnlock() 메소드가 호출될때 까지 Block 상태가 된다. 
    (쓰기 접근이 허용되면 - 잘 모르겠음) 쓰레드는 sleep()에 의해 100ms 동안 Block 상태가 된다. 
    시간이 지나면, buffer를 초기화한 다음, v 문자열을 추가한다. 
    마지막에 lock.writeUnlock() 호출하여 쓰기 접근을 허용한다.
   
### Lock 클래스
읽기와 쓰기에 대한 쓰레드 제어를 위한 클래스

    1. 읽기 접근 요청 수 readingReaders, 읽기 접근 대기 수 waitingReaders, preferReader (= false) 를 선언한다.
    (preferReader는 안전한 lock/ unlock 을 위해 적용된 읽기 접근 상태이다.)

    1. 쓰기 접근 요청 수 writingWriters, 쓰기 접근 대기 수 waitingWriters, preferWriter (= true) 를 선언한다.(preferWriter는 안전한 lock/ unlock 을 위해 적용된 쓰기 접근 상태이다.)
    
    2. readLock() 메소드에서 읽기 접근 대기 수를 1 증가한다. 
    쓰기 접근 요청이 있거나 쓰기 접근 대기가 있으면 wait() 의해 쓰레드는 Block 상태가 된다.
    읽기 접근 대기 수를 1 감소하고, 읽기 요청 대기 수를 1 증가한다.
    즉, 쓰기 접근 중이거나 쓰기 접근을 요청한 쓰레드가 존재하지 않는다면 읽기 접근을 요청하는 모든 쓰레드들에게 접근이 허용된다.

    3. readUnlock() 메소드에서 읽기 접근 요청 수를 1 감소한다.
    notifyAll() 메소드 호출로 모든 대기 중인 쓰레드를 깨워, 자신이 요청한 접근을 획득할 수 있는지 확인한다. 

    4. writeLock() 메소드에서 쓰기 접근 대기 수를 1 증가한다. 
    읽기 접근 요청이 있거나 쓰기 접근 요청이 있거나 읽기 접근 대기가 있으면 wait() 의해 쓰레드는 Block 상태가 된다.
    쓰기 접근 대기 수를 1 감소하고, 쓰기 요청 대기 수를 1 증가한다.
    즉, 읽기/쓰기 접근 중인  쓰레드가 존재하지 않는 다면 쓰기 접근은 허용된다.

    3. writeUnlock() 메소드에서 쓰기 접근 요청 수를 1 감소한다.
    notifyAll() 메소드 호출로 모든 대기 중인 쓰레드를 깨워, 자신이 요청한 접근을 획득할 수 있는지 확인한다.


## 컴파일 과정

_스스로의 해석입니다. 잘못된 내용 발견시, 즉각적인 피드벡 부탁드립니다._

>1. Main 클래스에서 'data' 객체 참조 변수의 선언과 Data 인스턴스의 생성을 동시에 한다. 
Date 클래스에서 Data() 인스턴스가 선언되면, buffer 에 "#empty" 문자열을 대열을 대입한다.
>
>2. Reader를 통해 읽기 접근을 요청한다. 총 3개의 쓰레드가 요청한다.
>
>3. Thread-1 -> #empty   
Thread-0 -> #empty   
Thread-2 -> #empty 
출력한다.
>
>4. weeks 배열, numbers 배열, digits 배열 각각은  하나의 data 객체와 Writer를 통해 쓰기 접근을 요청한다. 
> 출력값의 순서가 월(weeks 인덱스 0의 값), ONE(numbers 인덱스 0의 값), 1(digits 인덱스 0의 값)이다.
>
>5. Lock 클래스에서 읽고 쓰는 접근 권한을 조절하여 값을 출력한다.   
Thread-1 -> 월
Thread-2 -> 월   
Thread-0 -> 월   
Thread-2 -> ONE    
Thread-0 -> ONE    
Thread-1 -> ONE    
Thread-1 -> 1    
Thread-2 -> 1    
Thread-0 -> 1   
Thread-1 -> 1   
Thread-2 -> 1    
Thread-0 -> 1    
.   
.   
.  
>
>6. 종종 같은 값이 여러번 출력되는데, Writer 클래스에서 랜덤한 시간동안 값을 넣기 때문에 쓰레드가 동일한 값을 지니고  Blocking-Runnable-Running 상태를 반복한다.
