package com.example.semana3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.semana3.Fragments.FormFragment;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_form);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new FormFragment()).commit();
    }

}
