package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tvOverview, tvReleaseDate, tvAverage, tvCount, tvPopularity, tvLanguage;
    ImageView ivBackdrop, ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvOverview = (TextView) findViewById(R.id.textViewOverview);
        tvReleaseDate = (TextView) findViewById(R.id.textViewReleaseDate);
        tvAverage = (TextView) findViewById(R.id.textViewVoteAverage);
        tvCount = (TextView) findViewById(R.id.textViewVoteCount);
        tvPopularity = (TextView) findViewById(R.id.textViewPopularity);
        tvLanguage = (TextView) findViewById(R.id.textViewOriginalLanguage);

        ivBackdrop = (ImageView) findViewById(R.id.imageViewBackdrop);
        ivPoster = (ImageView) findViewById(R.id.imageViewPoster);

        setTitle(getIntent().getStringExtra(MainActivity.RESULTTITLE));
        tvOverview.setText(getIntent().getStringExtra(MainActivity.RESULTOVERVIEW));
        tvReleaseDate.setText(getIntent().getStringExtra(MainActivity.RESULTRELEASE));
        tvAverage.setText(getIntent().getStringExtra(MainActivity.RESULTAVERAGE));
        tvCount.setText(getIntent().getStringExtra(MainActivity.RESULTCOUNT));
        tvPopularity.setText(getIntent().getStringExtra(MainActivity.RESULTPOPULARITY));
        tvLanguage.setText(getIntent().getStringExtra(MainActivity.RESULTLANGUAGE));

        Glide.with(DetailActivity.this)
                .load("http://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTBACKDROP))
                .into(ivBackdrop);
        Glide.with(DetailActivity.this)
                .load("http://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTPOSTER))
                .into(ivPoster);
    }
}
