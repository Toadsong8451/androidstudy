# Screen transition user interface 슬라이드 정리
**Screen transition**
- Activity1 내에서 start activity 로 Intent 전달 -> Activity 2로 이동
    ## 인플레이션
    XML 레이아웃 파일의 내용을 메모리상에 로드하여 화면에 보여주는 과정
    - 종류 : 전체 인플레이션과 부분 인플레이션
        ### 전체 인플레이션
        - `onCreate`의 `setContentView`를 사용한다. 
        - `setContentView(R.layout.xml);`
        - 순서 : xml 레이아웃이 메모리에 객체화 -> `setContentView()`로 그 안의 뷰들을 객체화 (`setContentView()` 이전에는 내부 뷰의 존재를 모른다)
        ### 부분 인플레이션
        - `LayoutInflater`를 이용한다.
        - `LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);`
        - `inflater.inflate(R.layout.xml, 객체명, true);`
        - 전체 인플레이션으로는 부분화면을 메모리에 객체화 불가
- Activity : 화면에 표시되는 UI 구성을 위한 기본 요소
    - MainActivity가 앱 실행 시 최초로 보여지는 Activity
    - Context : 애플리케이션(객체)의 현재 상태의 맥락
        - 생성된 객체의 현재 상태
        - 리소스, db, preference의 접근 제공 
        - Activity Context : 액티비티에서 사용 가능
            - `ContentProvider`의 `getContext()`를 사용한다. 
        - Application Context
           - `getApplicationContext()`를 사용한다. 
          - Context가 호출이 유지된다면 메모리 누수가 가능하다. (기존의 Activity는 가비지 콜렉터로 관리되지만 Application context로 호출을 해놓는다면 계속 참조가 유지되기 때문이다.)
    - 생성, 시작, 실행, 일시정지, resume, 중지, 소멸 (생명주기)
    - `startActivity`를 이용하여 소스에서 띄운다. 
    - 만일 Activity a에서 부른 b에서 다시 돌아오기 위해서는 `registerForActivityResult`를 이용한다. 
- Intent : 다른 앱의 구성요소, 작업을 요청하는 데 사용되는 객체
    -start activity, start service, deliver Broadcast 등의 기능 가능
*Fragment랑 상하 탭, 뷰페이저는 과제 열심히 했으면 됨*
