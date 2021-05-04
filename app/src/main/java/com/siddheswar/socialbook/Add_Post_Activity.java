package com.siddheswar.socialbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Add_Post_Activity extends AppCompatActivity {

    Button btnUploadPost;
    EditText etPost;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post_);

        btnUploadPost = findViewById(R.id.uploadPostButton);
        etPost = findViewById(R.id.etPost);
        db = FirebaseFirestore.getInstance();






        btnUploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = etPost.getText().toString();
                Map<String, Object> map = new HashMap<>();
                map.put("data",post);
    db.collection("Post").add(map).addOnCompleteListener(Add_Post_Activity.this, new OnCompleteListener<DocumentReference>() {
        @Override
        public void onComplete(@NonNull Task<DocumentReference> task) {
            if(task.isSuccessful())
            {
                Toast.makeText(Add_Post_Activity.this, "Posted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Add_Post_Activity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        }
    });

            }
        });






    }

}