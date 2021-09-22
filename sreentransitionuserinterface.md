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
    