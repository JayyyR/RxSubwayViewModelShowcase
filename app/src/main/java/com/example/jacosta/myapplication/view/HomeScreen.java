package com.example.jacosta.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.jacosta.myapplication.R;
import com.joeracosta.library.Stack.ViewStack;
import com.joeracosta.library.Stack.ViewStackDelegate;

public class HomeScreen extends AppCompatActivity implements ViewStackDelegate {

    private ViewStack mStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

        mStack = ViewStack.create((ViewGroup)findViewById(R.id.activity_main), this);

    }

    @Override
    public void finishStack() {
        finish();
    }
}
