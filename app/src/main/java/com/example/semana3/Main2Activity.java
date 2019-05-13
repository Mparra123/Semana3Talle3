package com.example.semana3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.semana3.Fragments.MainFragment;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new MainFragment()).commit();

    }
}
