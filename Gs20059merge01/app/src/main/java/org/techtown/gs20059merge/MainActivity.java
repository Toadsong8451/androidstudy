package org.techtown.gs20059merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    HomeFragment fragment; //홈프래그먼트를 선언한다.
    Frag1 fragment1;
    Frag2 fragment2;
    Frag3 fragment3;
    int current_page;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new HomeFragment(); //초기화해준다.
        fragment1 = new Frag1();
        fragment2 = new Frag2();
        fragment3 = new Frag3();
        current_page = 0;

        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        ////프래그먼트를 여러개 사용할 수 있으므로, 비긴트랜잭션을 사용한다.
        //간단히 프래그먼트에서 사용되는 트랜잭션이란 어떤 대상에 대해 추가, 제거, 변경등의 작업들이
        //발생하는 것을 묶어서 이야기 하는 것이다.
        // 끝에 커밋을 해줘야지 작동한다.
/*
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                fragment.setPage(position);
                current_page= position;

                BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
                switch(current_page) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.page_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.page_tv);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.page_calendar);
                        tabs.getTabAt(1).select();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

*/



    }
}