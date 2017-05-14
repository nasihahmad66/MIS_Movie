package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.adapter.ComingSoonAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.adapter.TopRatedAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.fragment.ComingSoonFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.fragment.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.fragment.TopRatedFragment;

public class MainActivity extends AppCompatActivity implements ComingSoonAdapter.IComingSoonAdapter, TopRatedAdapter.ITopRatedAdapter, NowPlayingAdapter.INowPlayingAdapter {

    public static final String RESULTTITLE = "resultTitle";
    public static final String RESULTOVERVIEW = "resultOverview";
    public static final String RESULTBACKDROP = "resultBackdrop";
    public static final String RESULTPOSTER = "resultPoster";
    public static final String RESULTRELEASE = "resultRelease";
    public static final String RESULTAVERAGE = "resultAverage";
    public static final String RESULTCOUNT = "resultCount";
    public static final String RESULTPOPULARITY = "resultPopuarity";
    public static final String RESULTLANGUAGE = "resultLanguage";


    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_play_circle_filled_black_48px,
            R.drawable.ic_favorite_black_48px,
            R.drawable.ic_rotate_right_black_48px
    };

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("NOW PLAYING");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_play_circle_filled_black_48px, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("TOP RATED");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_black_48px, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("COMING SOON");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_rotate_right_black_48px, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTopRated(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTOVERVIEW, overview);
        intent.putExtra(RESULTBACKDROP, backdrop_path);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTAVERAGE, vote_average);
        intent.putExtra(RESULTCOUNT, vote_count);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTLANGUAGE, original_language);

        startActivity(intent);
    }

    @Override
    public void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTOVERVIEW, overview);
        intent.putExtra(RESULTBACKDROP, backdrop_path);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTAVERAGE, vote_average);
        intent.putExtra(RESULTCOUNT, vote_count);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTLANGUAGE, original_language);

        startActivity(intent);
    }

    @Override
    public void showComingSoon(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTOVERVIEW, overview);
        intent.putExtra(RESULTBACKDROP, backdrop_path);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTAVERAGE, vote_average);
        intent.putExtra(RESULTCOUNT, vote_count);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTLANGUAGE, original_language);

        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new NowPlayingFragment();
            else if (position == 1)
                return new TopRatedFragment();
            else if (position == 2)
                return new ComingSoonFragment();
            else
                return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}