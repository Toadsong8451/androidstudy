# Networking 슬라이드 정리
## 네트워킹
- 인터넷에 연결되어 있는 원격지의 서버, 원격지의 단말과 통신해서 데이터를 주고 받는 동작
- 연결 방식
    - 2-tier C/S 
        -클라이언트와 서버가 일대일로 연결
        -HTTP, FTP, POP3 프로토콜 등의 연결 방식
            -HTTP : 웹브라우저와 웹서버 간의 통신
            -FTP : 컴퓨터 파일을 전송하는 표준 통신 프로토콜
    - 3-tier C/S
        -응용 서버와 데이터 서버 분할 
        -DB server
    - Peer-to-Peer
        -단말 간의 통신 (서버 X)
### HTTP 프로토콜과 소켓
- 소켓으로 웹서버에 연결하여 요청을 전송하고 응답을 받아 연결을 끊는다. (Stateless)
- 소켓
    - TCP/IP 수준의 통신 방식 제공
    - IP 주고로 목적지 호스트를 찾아 포트로 통신 접속점을 찾는다.
    - 스레드, 핸들러, `java.net`을 사용한다. 
    - 스레드를 이용하여 네트워크 연결을 수행 + UI객체에 접근을 위해 Handler 사용
- HttpURLConnection
    - 표준 자바의 방식을 이용한다.
    - `openConnection`을 호출하여 `HttpURLConnection` 객체를 만든다.
    - URL 객체를 HTTP 연결을 위한 객체로 변경 가능
        -`openConnection` 메서드를 반환하는 `URLConnection` 객체를 `HttpURLConnection`로 형변환하여 사용
    - `public void setRequestMethod(String method)` : 요청 방식을 지정(GET or POST)
    - public void setRequestProperty(String field, String newValue) : 헤더에 들어가는 필드 값을 지정
    - GET : 주소줄 뒤의 데이터 -> 긴 데이터를 보내는 것은 불가능
    - POST : body 안에 보내진다 -> 많은 양을 보내는 것이 가능
### JSON
JavaScript Object Notation 
- JSON 포맷으로 된 데이터
- 자바스크립트 객 포맷을 데이터로 주고받기 위한 문자열 표현 방식
### Volley
- HTTP 연결의 다른 방법
- 웹 요청과 응답을 단순화하기 위한 라이브러리
- Volley를 사용하려면 요청 큐가 알아서 웹서버에 요청, 응답
- RequestQueue 가 중간에 존재
- 스레드를 사용할 필요가 없다. 
### PHP
PHP-Hypertext Preprocessor
- HTML 코드를 생성하는 역할을 한다. 
- 웹개발을 위해 사용된다. 
- db SERVER에 연동이 된다. 