# service & broadcast receiver&thread & handler 슬라이드 정리
## Service
백그라운드에서 실행되는 앱의 구성 요소
- 시스템에서 관리하며 백그라운드에서 다른 단말과 데이터 주고받음
- EX) 네트워크 트랜잭션, 음악 재생, db과 상호 작용 등의 방법이 존재
- unbounded : `startService()` 를 사용하면 스스로 멈출때까지 독립적으로 실행된다.
- bounded : `bindService()`를 사용하면 바인드된 서비스를 클라이언트 요청을 처리할때까지만 유지
- 차이 : 무한히 계속되는지 or 클라이언트 요청 처리 후 멈추는지
    ### TMI about this&getApplicationContext()
    - `this` : activity context = `getBaseContext()`
         - 액티비티와 연동된 것 -> 액티비티 다시 시작 시 change
    - `getApplicationContext()` : applicationContext
        - 어플리케이션의 lifecycle과 동일하게 돈다.
    - 둘다 같은 역할 but 앤티비티 존재 X or change 시 `getApplicationContext()`를 사용하자

## Intent
- Explicit Intent : 새 액티비리를 실행하는 인텐트를 뜻한다.  
`Intent intent = new Intent(context context, ~~.class);`  
`startActivity(intent)`  
-  Implicit Intent : 다른 기능을 하는 앱을 호출할 수 있는 기능  
`Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url주소));`  
`startActivity(intent);`  
## Logcat
- View > Tool Windows > Logcat
    - Log.e(오류), Log.w(경고), Log.i(정보), Log.d(디버그), Log.v(상세)
## Broadcasting
- 다른 앱으로부터 특정 메시지를 받기 위해 만들 앱에 브로드캐스트 수신자를 등하여 앱에 등록
## Thread
- Process <-> Thread : thread는 process 내에서 실행되는 흐름의 단위
- 표준 JAVA therad와 동일하게 작동 (정과프 프린트 참고!)  
`BackgroundThread thread = new BAckgroundThread();`  
`thread.start();`  
## Handler 
Main Thread는 UI 작업을 진행한다  
임의의 thread에서 다른 thread의 UI를 다루는 것은 불가능 -> Handler 사용
Handler : 서로 다른 Thread 간의 참조를 위해 만들어짐
- 메시지 전송 `public void handleMessage(Message msg)`
- Runnable 객체 사용 
    -Runnable 객체를 Post 메서드로 전달 -> run 메서드 실행

## Thread와 Service의 차이점
- Service 
    - 백그라운드에서 실행되는 구성요소
    - 상호작용을 안해도 사용이 가능하다. 
- Thread
    - 사용자가 앱과 상호작용하는 동안 메인 스레드 밖에서 작업 수행 시