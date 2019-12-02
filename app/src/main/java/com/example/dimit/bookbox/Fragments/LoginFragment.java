package com.example.dimit.bookbox.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dimit.bookbox.R;


public class LoginFragment extends Fragment {
    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_login, container, false);
        //init();

        return rootView;
    }

    public void init() {
        buttonSignin=(Button)getView().findViewById(R.id.buttonLogin);
        editTextEmail=(EditText)getView().findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)getView().findViewById(R.id.editTextPassword);
        textViewSignUp=(TextView)getView().findViewById(R.id.textViewSignUp);


        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new ProfileFragment()).commit();
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new SignUpFragment()).commit();
            }
        });
    }


}
