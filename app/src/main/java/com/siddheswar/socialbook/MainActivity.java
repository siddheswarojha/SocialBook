package com.siddheswar.socialbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton fabAddPost;
        FirestoreRecyclerAdapter adapter;
    FirebaseFirestore fb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.RecyclerHome);

        fabAddPost = findViewById(R.id.FAB_AddPost);

        fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Add_Post_Activity.class);
                startActivity(i);
            }
        });





        fb = FirebaseFirestore.getInstance();


        Query query = fb.collection("Post");
        FirestoreRecyclerOptions<productModel> options = new FirestoreRecyclerOptions.Builder<productModel>()
                .setQuery(query, productModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<productModel, ProductViewHolder>(options) {
            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.list_item_home, parent, false);
                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ProductViewHolder holder, int position, @NonNull productModel model) {

                holder.postDetails.setText(model.getData());
                holder.txtLikeCount.setText(String.valueOf(model.getLike()));
                holder.imgIconLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.imgIconLike.setImageResource(R.drawable.ic_baseline_thumb_up_24);



                    }
                });


            }
        };
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);



    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtLikeCount,  postDetails,id;
        ImageView imgIconLike,iconComment;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            postDetails = itemView.findViewById(R.id.Samplepost);
            txtLikeCount = itemView.findViewById(R.id.txtLikeCount);
            imgIconLike = itemView.findViewById(R.id.iconLike);
            iconComment = itemView.findViewById(R.id.iconComment);
            id = itemView.findViewById(R.id.id);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }
}