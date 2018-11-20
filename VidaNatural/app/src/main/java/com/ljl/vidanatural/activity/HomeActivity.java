package com.ljl.vidanatural.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.adapters.PageAdapter;
import com.ljl.vidanatural.fragments.TelaUsuario;
import com.ljl.vidanatural.fragments.TelaPIC;
import com.ljl.vidanatural.fragments.TelaMapa;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TelaUsuario mTela1;
    private TelaPIC mTela2;
    private TelaMapa mTela3;
    private PageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTela1 = new TelaUsuario();
        mTela2 = new TelaPIC();
        mTela3 = new TelaMapa();

        List<Fragment> fragmentos = new ArrayList<>();
        fragmentos.add(mTela1);
        fragmentos.add(mTela2);
        fragmentos.add(mTela3);

        TabLayout tabs = findViewById(R.id.viewpager_tabs);

        mPageAdapter = new PageAdapter(getSupportFragmentManager(), fragmentos);

        ViewPager viewPager = findViewById(R.id.viewpager_viewpager);
        viewPager.setAdapter(mPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        TabLayout.Tab tab = tabs.getTabAt(1);
        tab.select();
    }

}
