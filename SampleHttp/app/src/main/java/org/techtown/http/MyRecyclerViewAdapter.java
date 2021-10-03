package org.techtown.http;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerViewHolders>{

    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflate;
    private Context mContext;

    //constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<Movie> itemList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mMovieList = itemList;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.list_item, parent, false);
        RecyclerViewHolders viewHolder = new RecyclerViewHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, int position) {
        String url = "https://image.tmdb.org/t/p/w500" + mMovieList.get(position).getPoster_path();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.mMovieList.size();
    }

    public void setList(ArrayList<Movie> itemList) {
        this.mMovieList = itemList;
    }


    //뷰홀더 - 따로 클래스 파일로 만들어도 된다.
    public static class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}