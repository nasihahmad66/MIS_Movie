package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {
    ArrayList<Result> now_mList = new ArrayList<>();
    NowPlayingAdapter now_mAdapter;


    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.now_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        now_mAdapter = new NowPlayingAdapter(this.getActivity(), now_mList);
        recyclerView.setAdapter(now_mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        downloadDataNowPlaying();
    }

    private void downloadDataNowPlaying() {
        String url = "http://api.themoviedb.org/3/movie/now_playing?api_key=5c678b911da0e615ec3f7e54a84bc4a5";

        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>
                (url, ResultResponse.class, null, new Response.Listener<ResultResponse>() {
                    @Override
                    public void onResponse(ResultResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        now_mList.addAll(response.results);
                        now_mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}