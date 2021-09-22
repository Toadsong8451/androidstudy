# Screen transition user interface 슬라이드 정리
**Screen transition**
- Activity1 내에서 start activity 로 Intent 전달 -> Activity 2로 이동
## 인플레이션
XML 레이아웃 파일의 내용을 메모리상에 로드하여 화면에 보여주는 과정
- 종류 : 전체 인플레이션과 부분 인플레이션
    ### 전체 인플레이션
 - 'onCreate'의 'setContentView'를 사용한다. 
 - 'setContentView(R.layout.xml);'
    ### 부분 인플레이션