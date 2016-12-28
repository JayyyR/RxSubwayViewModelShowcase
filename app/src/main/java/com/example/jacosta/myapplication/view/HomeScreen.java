package com.example.jacosta.myapplication.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.PageHomeBinding;
import com.example.jacosta.myapplication.viewmodel.HomeScreenViewModel;
import com.joeracosta.library.Stack.ViewStack;
import com.joeracosta.library.Stack.ViewStackDelegate;

public class HomeScreen extends AppCompatActivity implements ViewStackDelegate {

    private ViewStack mStack;
    private PageHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.page_home, null, false);
        mBinding.setViewModel(new HomeScreenViewModel());

        setContentView(mBinding.appContent);

        mStack = ViewStack.create((ViewGroup)findViewById(R.id.app_content), this);

    }

    @Override
    public void finishStack() {
        finish();
    }
}
