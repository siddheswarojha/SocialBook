package com.siddheswar.socialbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;




public class Register_Activity extends AppCompatActivity {
   ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        final TextView login = findViewById(R.id.txtViewLogin);
        final TextView signup = findViewById(R.id.txtViewSignup);
        imgBack = findViewById(R.id.imgBack);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                imgBack.setVisibility(View.INVISIBLE);

                   // Logic for  colour change in the TextViews
                    signup.setTextColor(getResources().getColor(R.color.colorBlack));
                    login.setTextColor(getResources().getColor(R.color.colorAccent));

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.RegisterLayout, new login()).commit();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBack.setVisibility(View.INVISIBLE);

//Logic for colour change in the TextViews
                    login.setTextColor(getResources().getColor(R.color.colorBlack));
                    signup.setTextColor(getResources().getColor(R.color.colorAccent));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.RegisterLayout, new SignUp()).commit();
            }
        });
    }
}