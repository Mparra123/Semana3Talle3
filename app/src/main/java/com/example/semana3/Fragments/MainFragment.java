package com.example.semana3.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.semana3.FormActivity;
import com.example.semana3.R;

public class MainFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        setupUI(view);

        return view;


    }

    public void setupUI(View view){
        Button btnNext = (Button)view.findViewById(R.id.btnFrag);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //do something
                Intent intent = new Intent(getContext(), FormActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

    }

}
