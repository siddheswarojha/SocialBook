package com.siddheswar.socialbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class HomeRecylerAdapter extends RecyclerView.Adapter<HomeRecylerAdapter.HomeViewHolderRecyler> {


  ArrayList<String> list;



    public HomeRecylerAdapter(MainActivity mainActivity, ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeViewHolderRecyler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_home,parent,false);
        return new HomeViewHolderRecyler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolderRecyler holder, int position) {

holder.postDetails.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolderRecyler extends RecyclerView.ViewHolder{
        TextView txtLikeCount,txtDislikeCount,postDetails;
        ImageView imgIconLike, imgIconDislike;

//      CardView card;


        public HomeViewHolderRecyler(@NonNull View itemView) {
            super(itemView);
            postDetails = itemView.findViewById(R.id.Samplepost);
           txtLikeCount = itemView.findViewById(R.id.txtLikeCount);
           txtDislikeCount = itemView.findViewById(R.id.txtDislikeCount);
           imgIconDislike = itemView.findViewById(R.id.iconDislike);
           imgIconLike = itemView.findViewById(R.id.iconLike);
//            card = itemView.findViewById(R.id.card);
        }
    }

}
