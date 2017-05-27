package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.sugar.Place;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class DetailActivity extends AppCompatActivity {

    public byte[] gambar = new byte[2048];
    TextView tvOverview, tvReleaseDate, tvAverage, tvCount, tvPopularity, tvLanguage;
    ImageView ivBackdrop, ivPoster;
    Place place;
    boolean isPressed = true;

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

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

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Bitmap bitmap = Glide.
                            with(getApplicationContext()).
                            load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTPOSTER)).
                            asBitmap().
                            into(500, 500).get();
                    gambar = getBytes(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        new MaterialShowcaseView.Builder(this)
                .setTarget(fab)
                .setDismissText("OK")
                .setContentText("Click the love button to add to the favorite list !")
                .setDelay(200) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("Yes") // provide a unique ID used to ensure it is only shown once
                .show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed) {
                    doSimpan();
                    Snackbar.make(view, "You have successfully added to your favorite list", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Your favorite article", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void doSimpan() {
        String overview = getIntent().getStringExtra(MainActivity.RESULTOVERVIEW);
        String title = getIntent().getStringExtra(MainActivity.RESULTTITLE);
        String release_date = getIntent().getStringExtra(MainActivity.RESULTRELEASE);
        byte[] backdrop_path = gambar;

        place = new Place(overview, release_date, title, backdrop_path);
        place.save();
    }

}
