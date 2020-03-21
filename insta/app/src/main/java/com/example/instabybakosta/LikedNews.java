package com.example.instabybakosta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class LikedNews extends Fragment {
    private RecyclerView recyclerView2;
    private NewsListAdapter adapter2;
    private Database db2 = new Database();
    private FragmentActivity myContext;
    private NewsListAdapter.ItemClickListener listener2 = null;
    public static List<News> likedItems = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public LikedNews() {
        // Required empty public constructor
    }
    public LikedNews(List<News> liked) {
        // Required empty public constructor
        this.likedItems = liked;
    }

    // TODO: Rename and change types and number of parameters
//    public static LikedNews newInstance(String param1, String param2) {
//        LikedNews fragment = new LikedNews();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//List<News> liked = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener2 = new NewsListAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, News item) {

                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);//go to next activity
            }

            @Override
            public void onClick(View v, int position, News item) {
                if (v.getId() == R.id.postLike) {
                    if(item.isPostLiked()){
                        Toast.makeText(getContext(), "post UNliked!!!" , Toast.LENGTH_SHORT ).show();
                        item.setPostLiked(false);

                        String likeCnt =item.getViewsCount();
                        likeCnt =  likeCnt.replaceAll("\\D+","");
                        int likes = Integer.parseInt(likeCnt);
                        likes--;



                        item.setViewsCount(likes + " likes");
                        Database.likedNews.remove(item);
                        Fragment fragment = new LikedNews(Database.likedNews);
                        myContext.getSupportFragmentManager()
                                .beginTransaction()//
                                .replace(R.id.likedNews, fragment)
                                .commitAllowingStateLoss();

                    }

                }
            }
        };

        recyclerView2 =(RecyclerView) getView().findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter2 = new NewsListAdapter(this.likedItems, listener2);
        recyclerView2.setAdapter(adapter2);


//        for (int i=0; i<adapter2.getItemCount(); i++){
//
//            RecyclerView.ViewHolder holder2 = recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i));
//             holder2.itemView.findViewById(R.id.linearLayout2)
//                    .setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu3));
//
//
////            recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i))
////            .itemView.findViewById(R.id.linearLayout2).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu3));
////            recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i))
//////            recyclerView2.findViewHolderForAdapterPosition(i)
////                    .itemView.findViewById(R.id.linearLayout2)
////                    .setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu3));
////            recyclerView2 holder = (AttendanceViewHolder) rv.findViewHolderForAdapterPosition(i);
////            holder.sStatus.getSelectedItemId();
//            //TODO
//        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,   @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_liked_news, container, false);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
