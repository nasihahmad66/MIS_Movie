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

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ViewHolder> {
    public static final String
            IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list;
    ComingSoonAdapter.IComingSoonAdapter mIComingSoonAdapter;
    Context context;

    public ComingSoonAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mIComingSoonAdapter = (ComingSoonAdapter.IComingSoonAdapter) context;
    }

    @Override
    public ComingSoonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_coming_soon, parent, false);
        ComingSoonAdapter.ViewHolder vh = new ComingSoonAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.coming_tvTitle.setText(result.title);
        holder.coming_tvOverview.setText(result.overview);
        holder.coming_tvRating.setText((result.vote_average).toString());
        holder.coming_tvDate.setText(result.release_date);
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + result.poster_path)
                .into(holder.coming_ivPoster);
        holder.itemView.setTag(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface IComingSoonAdapter {
        void showComingSoon(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coming_ivPoster;
        TextView coming_tvTitle;
        TextView coming_tvOverview;
        TextView coming_tvRating;
        TextView coming_tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            coming_ivPoster = (ImageView) itemView.findViewById(R.id.coming_imageViewPoster);
            coming_tvTitle = (TextView) itemView.findViewById(R.id.coming_textViewTitle);
            coming_tvOverview = (TextView) itemView.findViewById(R.id.coming_textViewOverview);
            coming_tvRating = (TextView) itemView.findViewById(R.id.coming_textViewRating);
            coming_tvDate = (TextView) itemView.findViewById(R.id.coming_textViewDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = list.get(getAdapterPosition());
                    mIComingSoonAdapter.showComingSoon(result.poster_path, result.title, result.overview, result.vote_average, result.release_date, result.backdrop_path, result.vote_count, result.popularity, result.original_language);
                }
            });
        }
    }
}
