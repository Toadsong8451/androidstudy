# service & broadcast receiver&thread & handler 슬라이드 정리
## Service
백그라운드에서 실행되는 앱의 구성 요소
- 시스템에서 관리하며 백그라운드에서 다른 단말과 데이터 주고받음
- EX) 네트워크 트랜잭션, 음악 재생, db과 상호 작용 등의 방법이 존재
- unbounded : `startService()` 를 사용하면 스스로 멈출때까지 독립적으로 실행된다.
- bounded : `bindService()`를 사용하면 바인드된 서비스를 클라이언트 요청을 처리할때까지만 유지
- 차이 : 무한히 계속되는지 or 클라이언트 요청 처리 후 멈추는지
### TMI about this&getApplicationContext()
- `this` : activity context = `getBaseContext`
    - 액티비티와 연동된 것 -> 액티비티 다시 시작 시 change
- `getApplicationContext()` : applicationContext
    - 어플리케이션의 lifecycle과 동일하게 돈다.
- 둘다 같은 역할 but 앤티비티 존재 X or change 시 `getApplicationContext()`를 사용하자

