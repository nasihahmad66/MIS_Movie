package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.model.Result;

/**
 * Created by Samudra~ on 08/05/2017.
 */

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    public static final String
            IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list;
    NowPlayingAdapter.INowPlayingAdapter mINowPlayingAdapter;
    Context context;

    public NowPlayingAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mINowPlayingAdapter = (NowPlayingAdapter.INowPlayingAdapter) context;
    }

    @Override
    public NowPlayingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_now_playing, parent, false);
        NowPlayingAdapter.ViewHolder vh = new NowPlayingAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NowPlayingAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.now_tvTitle.setText(result.title);
        holder.now_tvOverview.setText(result.overview);
        holder.now_tvRating.setText((result.vote_average).toString());
        holder.now_tvDate.setText(result.release_date);
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + result.poster_path)
                .into(holder.now_ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface INowPlayingAdapter {
        void showNowPlaying(int id, String poster_path, String title, String overview, Double vote_average, String release_date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView now_ivPoster;
        TextView now_tvTitle;
        TextView now_tvOverview;
        TextView now_tvRating;
        TextView now_tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            now_ivPoster = (ImageView) itemView.findViewById(R.id.now_imageViewPoster);
            now_tvTitle = (TextView) itemView.findViewById(R.id.now_textViewTitle);
            now_tvOverview = (TextView) itemView.findViewById(R.id.now_textViewOverview);
            now_tvRating = (TextView) itemView.findViewById(R.id.now_textViewRating);
            now_tvDate = (TextView) itemView.findViewById(R.id.now_textViewDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = list.get(getAdapterPosition());
                    mINowPlayingAdapter.showNowPlaying(result.id, result.poster_path, result.title, result.overview, result.vote_average, result.release_date);
                }
            });
        }
    }
}
