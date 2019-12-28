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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dimit.bookbox.MainActivity;
import com.example.dimit.bookbox.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class LoginFragment extends Fragment {
    private Button buttonSignin;
    private Button btnLogOut;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_login, container, false);
        init(rootView);

        return rootView;
    }

    public void init(View rootView) {
        btnLogOut=(Button)rootView.findViewById(R.id.btnLogOut);
        buttonSignin=(Button)rootView.findViewById(R.id.buttonLogin);
        editTextEmail=(EditText)rootView.findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)rootView.findViewById(R.id.editTextPassword);
        textViewSignUp=(TextView)rootView.findViewById(R.id.textViewSignUp);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext()!=null && ((MainActivity)getContext()).getmAuth()!=null){
                    ((MainActivity)getContext()).getmAuth().signOut();

                }
            }
        });

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser(editTextEmail.getText().toString(),editTextPassword.getText().toString());

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new ProfileFragment()).commit();
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new SignUpFragment()).commit();
            }
        });
    }

    private void loginUser(String email, String password) {

        if (getContext() != null) {
            if (((MainActivity) getContext()).getmAuth() != null) {
                ((MainActivity) getContext()).getmAuth().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Najaven", Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new ProfileFragment()).commit();
                                } else {
                                    Toast.makeText(getContext(), "Ne uspesno", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }

        }
    }


}
