package ar.edu.itba.rutinapp_mobile_app.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import ar.edu.itba.rutinapp_mobile_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState ==null){

        }
    }
}