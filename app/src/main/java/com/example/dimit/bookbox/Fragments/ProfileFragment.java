package com.example.dimit.bookbox.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dimit.bookbox.MainActivity;
import com.example.dimit.bookbox.R;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        if (getContext()!=null&&((MainActivity)getContext()).getCurrentUser()!=null){
            Toast.makeText(getContext(), ((MainActivity)getContext()).getCurrentUser().getEmail().toString(), Toast.LENGTH_LONG).show();
        }

        return rootView;
    }
}
