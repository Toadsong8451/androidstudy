# Image Graphic 슬라이드 정리
View를 이용한 그리기 
- Canvas(직접 그릴 수 있다), Paint(색깔), Bitmap(픽셀 이미지), Drawable(그래픽 요소 사각형, 이미지 등)
## Canvas
- 점 그리기 `Void drawPoing(float x, float y, Paint paint)`
- 선 그리기 `Void drawLine(float startx, float starty, float stopx, float stopy, Paint paint)`
- 사각형 그리기 `Void drawRect(float left, float top, float right, float bottom, Paint paint)`
- 등등 존재한다. 
  
- 페인트 보드를 이용하면 그리기 가능 (`onTouchEvent` 메서드를 이용한다)
- 멀티터치를 위해서는 getX, getY, getAction, pointerIndex를 이용한다. 

## Animation
- Tweened Animation을 가장 많이 이용한다. 
- Animation 객체로 로딩 -> `startAnimation()`
## MediaPlayer
- 음악 파일, 동영상의 재생이 가능하다. 
- `setDataSource()`->`prepare()`->`start`
