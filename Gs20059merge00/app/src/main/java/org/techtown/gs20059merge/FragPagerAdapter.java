package org.techtown.gs20059merge;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;


public class FragPagerAdapter extends FragmentStateAdapter { //뷰페이저2에서는 FragmentStateAdapter를 사용한다.
    // Real Fragment Total Count
    private final int mSetItemCount = 3; //프래그먼트 갯수 지정

    public FragPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int iViewIdx = getRealPosition(position);

        switch( iViewIdx ) {
            case 0    : { return new Frag1(); } //프래그먼트 순서에 맞게 넣어주세요.
            case 1    : { return new Frag2(); }
            case 2    : { return new Frag3(); }
            default   : { return new Frag1(); } //기본으로 나와있는 프래그먼트
        }

    }

    public int getRealPosition(int _iPosition){
        return _iPosition % mSetItemCount;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mSetItemCount;
    }
}