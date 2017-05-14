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

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {
    public static final String
            IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list;
    TopRatedAdapter.ITopRatedAdapter mITopRatedAdapter;
    Context context;

    public TopRatedAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mITopRatedAdapter = (TopRatedAdapter.ITopRatedAdapter) context;
    }

    @Override
    public TopRatedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_top_rated, parent, false);
        TopRatedAdapter.ViewHolder vh = new TopRatedAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TopRatedAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.top_tvTitle.setText(result.title);
        holder.top_tvOverview.setText(result.overview);
        holder.top_tvRating.setText((result.vote_average).toString());
        holder.top_tvDate.setText(result.release_date);
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + result.poster_path)
                .into(holder.top_ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface ITopRatedAdapter {
        void showTopRated(int id, String poster_path, String title, String overview, Double vote_average, String release_date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView top_ivPoster;
        TextView top_tvTitle;
        TextView top_tvOverview;
        TextView top_tvRating;
        TextView top_tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            top_ivPoster = (ImageView) itemView.findViewById(R.id.top_imageViewPoster);
            top_tvTitle = (TextView) itemView.findViewById(R.id.top_textViewTitle);
            top_tvOverview = (TextView) itemView.findViewById(R.id.top_textViewOverview);
            top_tvRating = (TextView) itemView.findViewById(R.id.top_textViewRating);
            top_tvDate = (TextView) itemView.findViewById(R.id.top_textViewDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result toprated = list.get(getAdapterPosition());
                    mITopRatedAdapter.showTopRated(toprated.id, toprated.poster_path, toprated.title, toprated.overview, toprated.vote_average, toprated.release_date);
                }
            });
        }
    }
}