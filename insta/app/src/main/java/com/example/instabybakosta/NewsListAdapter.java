package com.example.instabybakosta;
import android.content.Context;
import android.content.Intent;

import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<News> newsList;
    @Nullable
    private ItemClickListener listener;//to open news detail

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public NewsListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }
    public NewsListAdapter(List<News> newsList, @Nullable ItemClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.tvAuthor.setText(Html.fromHtml(news.getAuthor()));
        holder.tvTitle.setText(Html.fromHtml(news.getTitle()));
        holder.tvComments.setText(String.valueOf(news.getCommentsCount()));
        holder.tvViews.setText(news.getViewsCount());
        Picasso.get().load(news.getAva()).into(holder.ava);
        Picasso.get().load(news.getPhotoLink()).into(holder.postPhoto);
        holder.postData.setText(news.getPostData());

        holder.buttonLike.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v, position, news);
                }
            }
        });
//        holder.moreText.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(v, position, news);
//                }
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {

                    listener.itemClick(position, news);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvAuthor;
        TextView tvViews;
        TextView tvComments;
        ImageView ava;
        ImageView postPhoto;
        TextView postData;

        Button buttonLike;
        Button moreText;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            //ava = imageView3
            //postPhoto = imageButton
            //postData
            buttonLike = (Button)itemView.findViewById(R.id.postLike);
//            moreText = (Button)itemView.findViewById(R.id.moreText);


            ava = (ImageView)itemView.findViewById(R.id.imageView3);
            postPhoto = (ImageView) itemView.findViewById(R.id.imageButton);

            postData = itemView.findViewById(R.id.postData);

            tvTitle = itemView.findViewById(R.id.tvTitle);//post title
            tvAuthor = itemView.findViewById(R.id.tvDate);// user login

            tvViews = itemView.findViewById(R.id.tvViews);//likes
            tvComments = itemView.findViewById(R.id.tvComments);//comments
        }


    }

    public interface ItemClickListener {
        public void itemClick(int position, News item);
        public void onClick(View v, int position, News item);
    }
}
