package org.techtown.gs20059all;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import static com.google.android.material.tabs.TabLayout.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    ViewPager2 pager;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();


        fragmentManager.beginTransaction().replace(R.id.container, fragment1).commit();
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        pager = findViewById(R.id.pager);
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(fragmentManager, getLifecycle());
        pager.setAdapter(adapter);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.page_home: {
                        transaction.replace(R.id.container, fragment1).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.page_tv: {
                        transaction.replace(R.id.container, fragment2).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.page_calendar: {
                        transaction.replace(R.id.container, fragment3).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        int itemCount = 3;

        public MyPagerAdapter(FragmentManager fm, Lifecycle cycle) {
            super(fm, cycle);
        }

        @Override
        public int getItemCount() {
            return itemCount;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: {
                    return new Fragment1();
                }
                case 1: {
                    return new Fragment2();
                }
                case 2: {
                    return new Fragment3();
                }
            }
            return new Fragment1();
        }
    }
}
