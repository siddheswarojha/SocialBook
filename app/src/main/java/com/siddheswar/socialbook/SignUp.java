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


public class SignUp extends Fragment {
    Button btnSignUp;
    TextInputEditText txtUser,txtEMail, txtPassword;

    FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        txtUser = view.findViewById(R.id.ITLuserName);
        txtEMail = view.findViewById(R.id.ITLemail);
        txtPassword = view.findViewById(R.id.ITLPassword);


        fAuth = FirebaseAuth.getInstance();




        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txtUser.getText().toString();
                String mail = txtEMail.getText().toString();
                String password = txtPassword.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(mail)||TextUtils.isEmpty(password))
                {
                    Toast.makeText(getActivity(), "Fields Can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8)
                {
                    Toast.makeText(getActivity(), "Make Sure password is Strong", Toast.LENGTH_SHORT).show();
                }
                else{
                    RegisterUser(user,mail,password);

                }



            }
        });
        return view;


    }

    private void RegisterUser(String user, String mail, String password) {

        fAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Registered", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Error occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
