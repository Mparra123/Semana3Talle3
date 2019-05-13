package com.example.semana3.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.semana3.R;


public class FormFragment extends Fragment {

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_form, container, false);

        setupUI(view);

        return view;


    }
    //instanciar andentro xk san errores los botones o edit texts

    public void setupUI(View view){
        Button fbtnNext = (Button)view.findViewById(R.id.btnSubmitFragment);
        final EditText ftxtName = (EditText)view.findViewById(R.id.editName);
        final EditText ftxtLastName = (EditText)view.findViewById(R.id.editLastName);
        final EditText ftxtPhone = (EditText)view.findViewById(R.id.editPhone);
        final EditText ftxtEmail = (EditText)view.findViewById(R.id.editEmail);

        fbtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //do something

                String name = ftxtName.getText().toString();
                String Lastname = ftxtLastName.getText().toString();
                String phone = ftxtPhone.getText().toString();
                String email = ftxtEmail.getText().toString();


            }
        });

    }

}
