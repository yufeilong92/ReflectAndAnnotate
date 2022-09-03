package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.annotate.AnnotateFindView;

public class TestActivity extends AppCompatActivity {

    @AnnotateFindView(value = R.id.btn_test)
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}