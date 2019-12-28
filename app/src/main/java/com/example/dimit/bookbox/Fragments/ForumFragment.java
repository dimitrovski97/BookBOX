package com.example.dimit.bookbox.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimit.bookbox.MainActivity;
import com.example.dimit.bookbox.R;


public class ForumFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_forum, container, false);


        if (getContext() != null){
            if (((MainActivity)getContext()).getCurrentUser()!=null){
                //najaven
            }else{
                ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new LoginFragment()).commit();
            }
        }


        return rootView;
    }
}
