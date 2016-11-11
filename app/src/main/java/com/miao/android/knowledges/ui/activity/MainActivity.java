package com.miao.android.knowledges.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.miao.android.knowledges.R;
import com.miao.android.knowledges.adapter.MainPagerAdapter;
import com.miao.android.knowledges.ui.fragment.HotNews;
import com.miao.android.knowledges.ui.fragment.RibaoNews;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PAGE_NUM = 2;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        List<Fragment> fragmentList = new ArrayList<>();
        RibaoNews ribaoFragment = new RibaoNews();
        HotNews hotFragment = new HotNews();
        fragmentList.add(ribaoFragment);
        fragmentList.add(hotFragment);

        List<String> titleList = new ArrayList<>();
        titleList.add("今日");
        titleList.add("热门");

        //viewPager.setOffscreenPageLimit(PAGE_NUM);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }
}
