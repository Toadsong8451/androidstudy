package org.techtown.gs20059all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class HomeFragment extends Fragment {
    private ViewGroup viewGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_fragment,container,false);

        setInit();

        return viewGroup;
    }

    private void setInit() {
        ViewPager2 viewPageSetUp = viewGroup.findViewById(R.id.viewPager2);
        MainActivity.MyPagerAdapter SetupPagerAdapter = new MainActivity.MyPagerAdapter(getActivity());





    }
}