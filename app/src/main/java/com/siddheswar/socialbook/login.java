package com.siddheswar.socialbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends Fragment {
    Button btnLogin;
    TextInputEditText txtEMail, txtPassword;

    FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view =  inflater.inflate(R.layout.fragment_login, container, false);

        txtEMail = view.findViewById(R.id.ITLemailLogin);
        txtPassword = view.findViewById(R.id.ITLPasswordLogin);

        fAuth = FirebaseAuth.getInstance();
     btnLogin=  view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEMail.getText().toString();
                String password = txtPassword.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
                {
                    Toast.makeText(getActivity(), "Fields Can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Login(email,password);
                }
            }
        });
        return view;

    }

    private void Login(String email, String password) {
        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Welcome Back!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}