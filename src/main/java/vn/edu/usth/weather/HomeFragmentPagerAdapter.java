package vn.edu.usth.weather;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[]{"Hanoi, Vietnam", "Paris, France", "Toulouse, France"};

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT; // number of pages for a ViewPager
    }

    @Override
    public Fragment getItem(int page) {
// returns an instance of Fragment corresponding to the specified page
        switch (page) {
            case 0:
                WeatherAndForecastFragment Fragment1;
                return Fragment1 = new WeatherAndForecastFragment();
            case 1:
                WeatherAndForecastFragment Fragment2;
                return Fragment2 = new WeatherAndForecastFragment();
            case 2:
                WeatherAndForecastFragment Fragment3;
                return Fragment3 = new WeatherAndForecastFragment();
        }
        WeatherAndForecastFragment EmptyFragment;
        return EmptyFragment = new WeatherAndForecastFragment();
    }

    @Override
    public CharSequence getPageTitle(int page) {
// returns a tab title corresponding to the specified page
        return titles[page];
    }
}
