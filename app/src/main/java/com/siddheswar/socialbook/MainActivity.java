package com.siddheswar.socialbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton fabAddPost;
//    FirestoreRecyclerAdapter adapter;
    FirebaseFirestore fb;
    ArrayList<String> list;
    HomeRecylerAdapter adapter;


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


list = new ArrayList<String>();


        fb = FirebaseFirestore.getInstance();
        fb.collection("Post").get().addOnCompleteListener(MainActivity.this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                   for (DocumentSnapshot querySnapshot : task.getResult())
                   {
                       String PM = querySnapshot.getData().toString();
                       list.add(PM);
                   }
                  adapter = new HomeRecylerAdapter(MainActivity.this,list);
                    rv.setAdapter(adapter);

                }
            }
        });
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        Query query = fb.collection("Post");
//        FirestoreRecyclerOptions<productModel> options = new FirestoreRecyclerOptions.Builder<productModel>()
//                .setQuery(query, productModel.class)
//                .build();
//        adapter = new FirestoreRecyclerAdapter<productModel, ProductViewHolder>(options) {
//            @NonNull
//            @Override
//            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//                View view = inflater.inflate(R.layout.list_item_home, parent, false);
//                return new ProductViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull productModel model) {
//
//                holder.postDetails.setText(model.getPost());
//
//            }
//        };



    }
//
//    private class ProductViewHolder extends RecyclerView.ViewHolder {
//        TextView txtLikeCount, txtDislikeCount, postDetails;
////        ImageView imgIconLike, imgIconDislike;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//            postDetails = itemView.findViewById(R.id.Samplepost);
////            txtLikeCount = itemView.findViewById(R.id.txtLikeCount);
////            txtDislikeCount = itemView.findViewById(R.id.txtDislikeCount);
////            imgIconDislike = itemView.findViewById(R.id.iconDislike);
////            imgIconLike = itemView.findViewById(R.id.iconLike);
//        }
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        if (adapter != null) {
//            adapter.stopListening();
//        }
//    }
}
