# Database&Contents Provider Sensor
모바일 데이터 베이스의 이용  
SQLite : 임베이드 데이터베이스 (for 안드로이드)
- 파일의 수준
- 저장 시 파일로 저장된다
- 데이터 조회 속도가 빠르다
- 표준 SQL을 지원한다. 
## 사용법
- 데이터 베이스 생성 - `openOrCreateDatabase`
- 테이블 생성 - `execSQL` 
- 테이블에 레코드 추가 - `execSQL` 
- 데이터 조회하기 - `rawQuery`
- 데이터베이스 삭제 - `deleteDatabase`

## SQL (Structured Query Language)
- 관계형 데이터베이스 관리 시스템에서 데이터를 관리하기 위한 특수 목적 프로그래밍 언어
- 테이블 생성 `CREATE TABLE USERHGD(userId VARCHAR(20) NOT NULL, userPAssword VARCHAR(20) NOT NULL, userNAme VARCHAR(20) NOT NUL, userAge INT NOT NULL, PRIMMARY KEY(userID) );`
- 테이블 삭제 `DROP TABLE USERHGD;`
- 레코드 삽입 `INSERT INTO USERHGD(userId, userPassword, userNAme, userAge) VALUES('21001hgd', '1234!@#$', 'HGD', 18);`
- 데이터 조회하기 `SELECT * FROM USERHGD //USERHGD 테이블의 모든 칼럼 조회`, `SELECT userId, userAge FROM USERHGD //USERHGD의 userId, userAge 칼럼 조회`, `SELECT * FROM USERHGD WHERE userAge = 17 // userAge 가 17인 사람 조회`, `SELECT userId FROM USERHGD WHERE userAge = 17 //userAge가 17인 사람의 userId 조회`
- `database.execSQL`을 이용하여 시행한다.
## 헬퍼 클래스
    테이블의 정의가 바뀌어 스키마를 업그레이드 할 필요가 있을 때
### 스키마
- 데이터 베이스 구조, 조건 
- SQLiteOpenHelper 클래스를 사용
    - `getReadableDatabase()`, `getWritableDatabase()`을 호출
### 내용 제공자
응용 프로그램 사이에 데이터를 공유하기 위한 컴포넌트
- 자신의 데이터에는 자신만 접근 가능 -> 외부 공개를 위해서
- URI를 사용한다. 
    - URI : content://패키지명/아이디
- Create, Read, Update, Delete 기능 수행
- 다른 앱에게 데이터 접근 통로를 열어주기 가능
### Cursor
- rawQuery에서 반환되는 객체
- 각각의 레코드에 순서대로 접근 
### Sensor 
- 가속 센서(초기값 : 9.81)
- 방향 센서 (경사도, 방위, 좌우 회전)
    - 북 : 0, 동 : 90, 남 : 180, 서 : 270
    - 머리 부분의 높이가 낮아지면 증가
    - 좌측이 높아지면 증가
