package org.techtown.gs20059merge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private ViewGroup viewGroup; //뷰그룹 객체 선언
    private ViewGroup viewGroup_m; //뷰그룹 객체 선언

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        viewGroup_m = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);

        //뷰그룹 인플레이션 한 뒤 viewGroup에 리턴해 줍니다.



        //그리고 이 프래그먼트에 메서드를 하나 임의로 호출합니다.
        setInit(); //뷰페이저2 실행 메서드




        BottomNavigationView bottomNavigationView = viewGroup.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                TabLayout tabs = viewGroup.findViewById(R.id.tabs);
                switch (item.getItemId()) {
                    case R.id.page_home: {
                        setPage(0);
//                        tabs.setScrollPosition(0,0f,true);
                        break;
                    }
                    case R.id.page_tv: {
                        setPage(1);
 //                       tabs.setScrollPosition(1,0f,true);
                        break;
                    }
                    case R.id.page_calendar: {
                        setPage(2);
  //                      tabs.setScrollPosition(2,0f,true);
                        break;
                    }
                }
                return true;
            }
        });


        return viewGroup;
    }

    public void setPage(int position) {
        ViewPager2 viewPageSetUp = viewGroup.findViewById(R.id.viewPager2); //여기서 뷰페이저를 참조한다.
        viewPageSetUp.setCurrentItem(position);
    }

    private void setInit() { //뷰페이저2 실행 메서드

        /* setup infinity scroll viewpager */
        ViewPager2 viewPageSetUp = viewGroup.findViewById(R.id.viewPager2); //여기서 뷰페이저를 참조한다.
        FragPagerAdapter SetupPagerAdapter = new FragPagerAdapter(getActivity()); //프래그먼트에서는 getActivity로 참조하고, 액티비티에서는 this를 사용해주세요.
        viewPageSetUp.setAdapter(SetupPagerAdapter); //FragPagerAdapter를 파라머티로 받고 ViewPager2에 전달 받는다.
        viewPageSetUp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL); //방향은 가로로
        viewPageSetUp.setOffscreenPageLimit(3); //페이지 한계 지정 갯수
        // 무제한 스크롤 처럼 보이기 위해서는 0페이지 부터가 아니라 1000페이지 부터 시작해서 좌측으로 이동할 경우 999페이지로 이동하여 무제한 처럼 스크롤 되는 것 처럼 표현하기 위함.
        viewPageSetUp.setCurrentItem(0);

        final float pageMargin = (float) getResources().getDimensionPixelOffset(R.dimen.pageMargin); //페이지끼리 간격
        final float pageOffset = (float) getResources().getDimensionPixelOffset(R.dimen.offset); //페이지 보이는 정도

        viewPageSetUp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                BottomNavigationView bottomNavigationView = viewGroup.findViewById(R.id.bottomNavigationView);
                switch(position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.page_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.page_tv);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.page_calendar);
                        break;
                }

                super.onPageSelected(position);

            }
        });

        /*
        viewPageSetUp.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float offset = position * - (2 * pageOffset + pageMargin);
                if(-1 > position) {
                    page.setTranslationX(-offset);
                } else if(1 >= position) {
                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                    page.setTranslationX(offset);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                } else {
                    page.setAlpha(0f);
                    page.setTranslationX(offset);
                }
            }
        });
*/
    }



}