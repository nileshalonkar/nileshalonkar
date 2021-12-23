package com.example.missionk3.Adapters;

import android.content.Context;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.missionk3.Fragments.FreeMeditationFragment;
import com.example.missionk3.Fragments.PaidMeditationFragment;

public class TabAdapter extends FragmentPagerAdapter {
    int tabCount;

    public TabAdapter(Context context, FragmentManager childFragmentManager, int tabCount) {
        super(childFragmentManager);
        context = context;
        this.tabCount = tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FreeMeditationFragment();
                break;

            case 1:
                fragment = new PaidMeditationFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
