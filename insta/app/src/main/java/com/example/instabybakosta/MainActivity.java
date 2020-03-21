package com.example.instabybakosta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.LocusId;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Button buttonLike, moreText, button_home, button_man;


    private NewsListAdapter adapter;

    private NewsListAdapter.ItemClickListener listener = null;
    public static Database db = new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout layout = (FrameLayout)findViewById(R.id.likedNews);
        layout.setVisibility(View.INVISIBLE);

        buttonLike = (Button) findViewById(R.id.postLike);
        button_home = (Button)findViewById(R.id.button_home);
        button_man  = (Button)findViewById(R.id.button_man);
//      moreText = (Button) findViewById(R.id.moreText);


        listener = new NewsListAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, News item) {
                Toast.makeText(getBaseContext(), "Your answer is correct!" , Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);//go to next activity

            }
            @Override
            public void onClick(View v, int position, News item) {
                if (v.getId() == R.id.postLike) {
                    if(item.isPostLiked()){
                        Toast.makeText(getBaseContext(), "post UNliked!!!", Toast.LENGTH_SHORT ).show();
                        item.setPostLiked(false);
                        RecyclerView.ViewHolder holder2 = recyclerView.findViewHolderForAdapterPosition(position);
                        holder2.itemView.findViewById(R.id.linearLayout2)
                                .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.menu2));

                        String likeCnt =item.getViewsCount();
                        likeCnt =  likeCnt.replaceAll("\\D+","");
                        int likes = Integer.parseInt(likeCnt);
                        likes--;
                        ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvViews)).setText(likes + " likes");

                        item.setViewsCount(likes + " likes");

                                Database.likedNews.remove(item);
                        Database.likedNews.remove(item);

                    }else{
                        Toast.makeText(getBaseContext(), "post liked!!!" , Toast.LENGTH_SHORT ).show();
                        item.setPostLiked(true);
                        RecyclerView.ViewHolder holder2 = recyclerView.findViewHolderForAdapterPosition(position);
                        holder2.itemView.findViewById(R.id.linearLayout2)
                                .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.menu3));

                        String likeCnt =item.getViewsCount();
                        likeCnt =  likeCnt.replaceAll("\\D+","");
                        int likes = Integer.parseInt(likeCnt);
                        likes++;
                        ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvViews)).setText(likes + " likes");
                        item.setViewsCount(likes + " likes");
                        Database.likedNews.add(item);


                    }

                }

//                    if(v.getId()== R.id.moreText){
//                        String text = item.getTitle();
//                        if(text.length()<15){
//                            String newText = text.substring(4);
//                            View changedv = recyclerView.getLayoutManager().findViewByPosition(position);
//                            TextView txtNew = changedv.findViewById(R.id.tvTitle);
//                            txtNew.setText(newText);//TODO
//                        }
//                    }


            }
        };

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter = new NewsListAdapter(Database.newsGenerator(), listener);
        recyclerView.setAdapter(adapter);



    }

    public void buttonLiked(View target) {
        Toast.makeText(getBaseContext(), "LIKED_POSTS!!!" , Toast.LENGTH_SHORT ).show();
        Fragment fragment = new LikedNews(Database.likedNews);
        findViewById(R.id.bottom)
                .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.menu4));
        getSupportFragmentManager()
                .beginTransaction()//
                .replace(R.id.likedNews, fragment)
                .commitAllowingStateLoss();
        FrameLayout layout = (FrameLayout)findViewById(R.id.likedNews);
        layout.setVisibility(View.VISIBLE);

        // Do stuff
    }
    public void buttonHome(View target) {
        Toast.makeText(getBaseContext(), "HOME!!!" , Toast.LENGTH_SHORT ).show();
        findViewById(R.id.bottom)
                .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.menu));
        FrameLayout layout = (FrameLayout)findViewById(R.id.likedNews);
        layout.setVisibility(View.INVISIBLE);
        // Do stuff
    }
}
