package com.ljl.vidanatural.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentos;

    public PageAdapter(final FragmentManager fm, List<Fragment> fragmentos) {
        super(fm);
        mFragmentos = fragmentos;
    }

    @Override
    public Fragment getItem(final int position) {
        return mFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentos.size();
    }
}
