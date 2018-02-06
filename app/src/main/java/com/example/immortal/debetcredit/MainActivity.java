package com.example.immortal.debetcredit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public static final int DEBET_FRAGMENT = 0;
    public static final int CREDIT_FRAGMENT = 1;
    public static final int INFO_FRAGMENT = 2;
    public static final int FRAGMENTS = 3;

    private FragmentPagerAdapter _fragmentPagerAdapter;
    private final List<Fragment> _fragments = new ArrayList<Fragment>();

    private ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _fragments.add(DEBET_FRAGMENT, new DebetCreditFragment());
        _fragments.add(CREDIT_FRAGMENT, new DebetCreditFragment());
        _fragments.add(INFO_FRAGMENT, new InfoFragment());

        _fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = _fragments.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("type", position);
                fragment.setArguments(bundle);

                return fragment;
            }

            @Override
            public int getCount() {
                return FRAGMENTS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case DEBET_FRAGMENT:
                        return "Доходи";
                    case CREDIT_FRAGMENT:
                        return "Витрати";
                    case INFO_FRAGMENT:
                        return "Інформація";
                    default:
                        return null;
                }
            }
        };

        _viewPager = (ViewPager) findViewById(R.id.pager);
        _viewPager.setAdapter(_fragmentPagerAdapter);
        _viewPager.setCurrentItem(0);
    }
}
