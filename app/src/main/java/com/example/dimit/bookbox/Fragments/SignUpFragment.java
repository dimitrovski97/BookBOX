package com.example.dimit.bookbox.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dimit.bookbox.MainActivity;
import com.example.dimit.bookbox.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class SignUpFragment extends Fragment {

    private EditText edtEmail;
    private EditText edtPass;
    private Button btnSignUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);


        initComponents(rootView);

        setComponents();

        return rootView;
    }

    private void setComponents() {

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check if email is correct
                if (!edtEmail.getText().toString().equals("") && !edtPass.getText().toString().equals("")) {
                    singUpUser(edtEmail.getText().toString(), edtPass.getText().toString());
                }
            }
        });
    }

    private void singUpUser(String email, String password) {

        if (getContext() != null) {
            if (((MainActivity) getContext()).getmAuth() != null) {
                ((MainActivity) getContext()).getmAuth().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Uspesno", Toast.LENGTH_SHORT).show();
                                    ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new LoginFragment()).commit();

                                } else {
                                    Toast.makeText(getContext(), "Ne uspesno", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }

        }


    }

    private void initComponents(View rootView) {
        edtEmail = rootView.findViewById(R.id.edtEmail);
        edtPass = rootView.findViewById(R.id.edtPass);
        btnSignUp = rootView.findViewById(R.id.btnSignUp);
    }


}
